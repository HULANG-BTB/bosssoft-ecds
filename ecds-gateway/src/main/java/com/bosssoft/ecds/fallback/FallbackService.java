package com.bosssoft.ecds.fallback;

import com.bosssoft.ecds.code.GatewayCode;
import com.bosssoft.ecds.exception.CustomException;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.ResponseResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import reactor.core.publisher.Flux;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;


/**
 * @ClassName :  FallbackService
 * @Description : 处理服务调用失败 ，决定是否发送邮件通知的策略
 * @Author : wuliming
 * @Date: 2020-08-18 11:00
 */
@Service
@Slf4j(topic = "kafka_logger")
public class FallbackService {


    @Autowired
    RouteLocator routeLocator;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    /**
     * 处理Hystrix调用异常
     *
     * @param exchange
     * @param throwable
     * @return
     */
    public ResponseResult manageFallBack(ServerWebExchange exchange, Throwable throwable) {
        Exception exception = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);
        //只处理找不到实例的异常  其他异常由sentinel处理
        if (exception instanceof NotFoundException) {
            return this.manageNotFound(exchange, throwable);
        } else if (exception instanceof HystrixTimeoutException) {
            throw new CustomException(CommonCode.SERVER_ERROR, "服务长时间未响应");
        } else if (exception instanceof HystrixBadRequestException) {
            throw new CustomException(GatewayCode.BAD_REQUEST, "错误请求");
        }
        throw new CustomException(GatewayCode.REQUEST_REMOTE_FAIL, null);
    }

    /**
     * 找不到有效服务时处理方法
     *
     * @param exchange
     * @param throwable
     * @return
     */
    public ResponseResult manageNotFound(ServerWebExchange exchange, Throwable throwable) {
        ServerWebExchange delegate = ((ServerWebExchangeDecorator) exchange).getDelegate();
        String path = delegate.getRequest().getPath().toString();
        String url = delegate.getRequest().getURI().toString();
        Flux<Route> routeFlux = routeLocator.getRoutes();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        Predicate<Route> predicate = temp -> {
            String tempurl = temp.getPredicate().toString();
            String pattern = tempurl.substring(tempurl.indexOf('[') + 1, tempurl.indexOf(']'));
            return antPathMatcher.match(pattern, path);
        };
        Optional<Route> optional = routeFlux.toStream().filter(predicate).findAny();
        String serviceId = "";
        if (optional.isPresent()) {
            serviceId = optional.get().getId();
        }
        String msg = "找不到运行的服务" + serviceId + "url: " + url;
        long keyTime = redisTemplate.getExpire(serviceId);
        if (keyTime > 0) {
            throw new CustomException(GatewayCode.NOT_FOUND_SERVICE, "多次" + msg);
        } else {
            redisTemplate.opsForValue().set(serviceId, url, 10L, TimeUnit.MINUTES);
            throw new CustomException(GatewayCode.EMAIL_NOTIFICATION, msg);
        }
    }
}
