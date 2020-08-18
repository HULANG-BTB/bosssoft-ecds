package com.bosssoft.ecds.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.bosssoft.ecds.code.GatewayCode;
import com.bosssoft.ecds.exception.ExceptionDetail;
import com.bosssoft.ecds.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 对限流异常进行处理并返回响应数据的类
 * @author: lpb
 * @create: 2020-08-14 10:33
 */
@Component
@Slf4j
public class MyBlockRequestHandler implements BlockRequestHandler {

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {
        String massage = "Blocked by Sentinel: " + t.getClass().getSimpleName() + ",time:"+new Date();
        String tags = t.getClass().getSimpleName();
        ExceptionDetail detail = new ExceptionDetail(GatewayCode.TOO_MANY_REQUESTS,massage,tags);
        log.info("Blocked by Sentinel:{}",t.getClass().getSimpleName());
        return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromValue(GatewayCode.TOO_MANY_REQUESTS));
    }
}
