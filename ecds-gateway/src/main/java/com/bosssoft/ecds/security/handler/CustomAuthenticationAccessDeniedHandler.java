package com.bosssoft.ecds.security.handler;

import cn.hutool.json.JSONUtil;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;


/**
 * @ClassName CustomAuthenticationAccessDeniedHandler
 * @Author AloneH
 * @Date 2020/8/9 16:25
 * @Description
 *              自定义无权限访问的异常处理
 *                  1、设置响应头
 *                  2、返回无权限信息
 **/

@Component
public class CustomAuthenticationAccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        ServerHttpResponse response = exchange.getResponse();
        // 设置响应头
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        // 三设置 验证异常返回信息
        QueryResponseResult<String> responseResult = new QueryResponseResult<>(CommonCode.ACCESSDENIED, denied.getMessage());
        byte[] responseBodyBytes = JSONUtil.toJsonStr(responseResult).getBytes();
        // 写入信息
        return response.writeWith(Mono.just(response.bufferFactory().wrap(responseBodyBytes)));
    }
}
