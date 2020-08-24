package com.bosssoft.ecds.security.handler;

import cn.hutool.json.JSONUtil;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @ClassName CustomRedirectServerLogoutSuccessHandler
 * @Author AloneH
 * @Date 2020/8/18 10:11
 * @Description TODO
 **/

@Component
public class CustomLogoutSuccessHandler extends RedirectServerLogoutSuccessHandler {
    
    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        ServerHttpResponse response = exchange.getExchange().getResponse();
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", CommonCode.SUCCESS.success());
        map.put("code", CommonCode.SUCCESS.code());
        map.put("message", CommonCode.SUCCESS.message());
        map.put("data", "{}");
        byte[] dataBytes = JSONUtil.toJsonStr(map).getBytes(StandardCharsets.UTF_8);
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

}
