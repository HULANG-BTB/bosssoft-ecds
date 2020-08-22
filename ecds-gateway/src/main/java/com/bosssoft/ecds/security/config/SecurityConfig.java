package com.bosssoft.ecds.security.config;

import com.bosssoft.ecds.security.auth.CustomReactiveAuthenticationManager;
import com.bosssoft.ecds.security.auth.CustomServerSecurityContextRepository;
import com.bosssoft.ecds.security.auth.CustomReactiveAuthorizationManager;
import com.bosssoft.ecds.security.auth.CustomServerAuthenticationEntryPoint;
import com.bosssoft.ecds.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @ClassName SecurityConfig
 * @Author AloneH
 * @Date 2020/7/27 15:56
 * @Description
 *              认证链配置器
 *                  1、自定义认证上下文存储库----获取和更改上下文信息
 *                  2、自定义认证处理器
 *                  3、自定义失败处理器
 *                  4、自定义成功处理器
 *                  5、自定义token认证失败处理器
 *              配置连中的先后顺序尤其重要，认证链根据加入的先后顺序按顺序执行，而不是一次性全部载入系统。
 **/

@EnableWebFluxSecurity

public class SecurityConfig {


    @Autowired
    CustomServerAuthenticationEntryPoint customServerAuthenticationEntryPoint;

    @Autowired
    CustomServerSecurityContextRepository custmoServerSecurityContextRepository;

    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    CustomAuthenticationAccessDeniedHandler customAuthenticationAccessDeniedHandler;

    @Autowired
    CustomReactiveAuthenticationManager customReactiveAuthenticationManager;

    @Autowired
    CustomReactiveAuthorizationManager customReactiveAuthorizationManager;

    @Autowired
    CustomLogoutSuccessHandler CustomLogoutSuccessHandler;

    private static final String[] excludedAuthPages = {
            "/user/login",
            "/user/logout",
            "/pay/**"
    };

    /**
     * 配置认证链
     * @param http
     * @return
     */
    @Bean
    SecurityWebFilterChain webFilterChain(ServerHttpSecurity http) {

        http.authorizeExchange()
                .pathMatchers(excludedAuthPages).permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/**").access(customReactiveAuthorizationManager)
                .anyExchange().authenticated()
                .and()
                .securityContextRepository(custmoServerSecurityContextRepository)
                .authenticationManager(customReactiveAuthenticationManager)
                .formLogin().loginPage("/user/login")
//                .authenticationEntryPoint()
                .authenticationSuccessHandler(customAuthenticationSuccessHandler)
                .authenticationFailureHandler(customAuthenticationFailureHandler)
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .logout().disable();

        http.exceptionHandling()
                .accessDeniedHandler(customAuthenticationAccessDeniedHandler)
                .authenticationEntryPoint(customServerAuthenticationEntryPoint);

        http.logout().logoutUrl("/user/logout").logoutSuccessHandler(CustomLogoutSuccessHandler);

        return http.build();
    }

    /**
     * 配置密码加密方式 返回Bean
     * @return
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
