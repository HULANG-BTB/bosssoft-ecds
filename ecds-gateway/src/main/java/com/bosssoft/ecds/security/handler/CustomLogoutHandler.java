package com.bosssoft.ecds.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @ClassName CustomLogoutHandler
 * @Author AloneH
 * @Date 2020/8/17 20:49
 * @Description TODO
 **/
@Component
public class CustomLogoutHandler implements ServerLogoutHandler {
    @Override
    public Mono<Void> logout(WebFilterExchange exchange, Authentication authentication) {
        return Mono.empty();
    }
}
