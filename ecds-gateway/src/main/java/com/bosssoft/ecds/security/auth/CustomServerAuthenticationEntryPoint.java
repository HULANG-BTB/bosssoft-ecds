package com.bosssoft.ecds.security.auth;

import cn.hutool.json.JSONUtil;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName CustomServerAuthenticationEntryPoint
 * @Author AloneH
 * @Date 2020/7/27 17:33
 * @Description
 *              自定义token验证失败处理逻辑
 *                  1、token验证失败的处理逻辑
 *                  2、设置响应头
 *                  3、写入响应内容
 **/

@Component
public class CustomServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private static final String WWW_AUTHENTICATE = "Authenticate";
    private static final String DEFAULT_REALM = "{Token}";
    private static String WWW_AUTHENTICATE_FORMAT = "Basic %s";

    private String headerValue = createHeaderValue(DEFAULT_REALM);

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        ServerHttpResponse response = exchange.getResponse();
        // 设置响应头
        // 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应
        response.getHeaders().set(WWW_AUTHENTICATE, this.headerValue);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        // 三设置 验证异常返回信息
        QueryResponseResult<String> responseResult = new QueryResponseResult<>(CommonCode.UNAUTHORIZED, e.getMessage());
        byte[] responseBodyBytes = JSONUtil.toJsonStr(responseResult).getBytes(StandardCharsets.UTF_8);
        // 写入信息
        return response.writeWith(Mono.just(response.bufferFactory().wrap(responseBodyBytes)));
    }

    /**
     * 创建错误响应头中的Authenticate
     * @param realm
     * @return
     */
    private static String createHeaderValue(String realm) {
        Assert.notNull(realm, "realm cannot be null");
        return String.format(WWW_AUTHENTICATE_FORMAT, realm);
    }

}
