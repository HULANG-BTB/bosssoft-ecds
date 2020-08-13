package com.bosssoft.ecds.security.handler;

import com.bosssoft.ecds.security.utils.ResponseUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName CustomAuthenticationFailureHandler
 * @Author AloneH
 * @Date 2020/7/27 17:31
 * @Description
 *              自定义认证失败处理逻辑
 *                  1、 设置响应头响应状态码
 *                  2、 生成响应内容
 *                  3、 写入响应内容
 **/

@Component
public class CustomAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    /**
     * 失败处理，返回错误信息
     * @param webFilterExchange
     * @param exception
     * @return
     */
    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        // 设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        // 设置body
        // 生成响应内容
        String res = ResponseUtils.getResponse(exception.getMessage(), ResponseUtils.ResultType.FORBIDDEN);
        DataBuffer bodyDataBuffer = null;
        try {
            bodyDataBuffer = response.bufferFactory().wrap(res.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 写入内容 返回
        return response.writeWith(Mono.just(bodyDataBuffer));
    }
}

