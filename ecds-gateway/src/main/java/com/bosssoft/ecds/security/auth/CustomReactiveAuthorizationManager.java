package com.bosssoft.ecds.security.auth;

import com.bosssoft.ecds.security.entity.domain.AuthUserDetails;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @ClassName CustomReactiveAuthorizationManager
 * @Author AloneH
 * @Date 2020/7/29 19:30
 * @Description 自定义授权管理
 * 1、组合所有拥有的权限
 * 2、遍历所有权限，判断当前访问路径是否具有权限
 * 3、权限 如果 A 具有访问 /user 的权限，那么 A自动具有访问 /user/* 的权限
 **/

@Component
public class CustomReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    /**
     * 请求路径
     */
    private String path;

    /**
     * 请求方法
     */
    private String method;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
        this.path = object.getExchange().getRequest().getPath().toString();
        this.path = this.path.substring(this.path.indexOf('/', 1));
        this.method = object.getExchange().getRequest().getMethod().toString();
        return authentication
                // 过滤未认证的
                .filter(Authentication::isAuthenticated)
                // 获得权限列表
                .flatMapIterable(auth -> ((AuthUserDetails) auth.getPrincipal()).getPermissions())
                // 遍历权限列表
                .any(permissionVO -> {
                    String reqUrl = permissionVO.getUrl();
                    String reqMethod = permissionVO.getMethod();
                    // 如果 A 具有访问 /user 的权限，那么 A自动具有访问 /user/* 的权限 此逻辑可以修改 地址和访问方式同时确定是否具有权限
                    if (reqUrl != null && this.path.startsWith(reqUrl) && method != null && reqMethod.equalsIgnoreCase(this.method)) {
                        return true;
                    }
                    return false;
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return null;
    }

}
