package com.bosssoft.ecds.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @ClassName CustomRedirectServerLogoutSuccessHandler
 * @Author AloneH
 * @Date 2020/8/18 10:11
 * @Description TODO
 **/

@Component
public class CustomRedirectServerLogoutSuccessHandler extends RedirectServerLogoutSuccessHandler {
    public static final String DEFAULT_LOGOUT_SUCCESS_URL = "/";

    private URI logoutSuccessUrl = URI.create(DEFAULT_LOGOUT_SUCCESS_URL);

    private ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();

    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        return this.redirectStrategy
                .sendRedirect(exchange.getExchange(), this.logoutSuccessUrl);
    }

}
