package com.bosssoft.ecds.security.auth;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.bosssoft.ecds.security.config.RsaKeyProperties;
import com.bosssoft.ecds.security.entity.domain.AuthUserDetails;
import com.bosssoft.ecds.security.entity.domain.Payload;
import com.bosssoft.ecds.security.service.impl.SecurityUserServiceImpl;
import com.bosssoft.ecds.security.utils.JwtUtils;
import com.bosssoft.ecds.security.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.*;

/**
 * @ClassName CustmoServerSecurityContextRepository
 * @Author AloneH
 * @Date 2020/7/28 16:53
 * @Description
 *              自定义认证上下文存储器
 *                  1、 检查Authorization请求头是否存在，不存在返回空
 *                  2、 检查token格式是否正确，不正确返回空
 *                  3、 返回一个UsernamePasswordAuthenticationToken，第一个参数为用户名，第二个参数为密码
 **/

@Component
@Slf4j
public class CustomServerSecurityContextRepository implements ServerSecurityContextRepository {

    @Autowired
    CustomReactiveAuthenticationManager authenticationManager;

    @Autowired
    SecurityUserServiceImpl userService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    RsaKeyProperties rsaKeyProperties;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            try {
                RSA rsa = new RSA(null, rsaKeyProperties.getPublicKey());
                // 自定义token处理逻辑开始
                String authToken = authHeader.replace("Basic ", "");
                Payload<AuthUserDetails> pubey = JwtUtils.getInfoFromToken(authToken, rsa.getPublicKey(), AuthUserDetails.class);

                // 读取token信息
                AuthUserDetails authUserDetails = pubey.getInfo();
                Long id = authUserDetails.getId();
                String username = authUserDetails.getUsername();
                String nickname = authUserDetails.getNickname();

                // 从redis中获取具体信息
                Map<String, Object> info = (Map<String, Object>) redisUtils.get(username);
                if (null == info) {
                    throw new RuntimeException("从 redis 读取数据为null");
                }
                authUserDetails = BeanUtil.mapToBean(info, AuthUserDetails.class, true);

                // 设置请求头数据
                ServerHttpRequest serverHttpRequest = request.mutate().header("auth_id", String.valueOf(id)).header("auth_nickname", nickname).build();
                exchange = exchange.mutate().request(serverHttpRequest).build();

                Authentication auth = new UsernamePasswordAuthenticationToken(authUserDetails.getUsername(), authUserDetails.getPassword(), authUserDetails.getAuthorities());
                return Mono.just(auth).map(SecurityContextImpl::new);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return Mono.empty();
            }

        } else {
            return Mono.empty();
        }
    }
}

