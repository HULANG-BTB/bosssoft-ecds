package com.bosssoft.ecds.security.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.bosssoft.ecds.security.config.RsaKeyProperties;
import com.bosssoft.ecds.security.entity.domain.AuthUserDetails;
import com.bosssoft.ecds.security.entity.vo.RoleVO;
import com.bosssoft.ecds.security.entity.vo.UserVO;
import com.bosssoft.ecds.security.service.impl.SecurityUserServiceImpl;
import com.bosssoft.ecds.security.utils.BeanUtils;
import com.bosssoft.ecds.security.utils.JwtUtils;
import com.bosssoft.ecds.security.utils.RedisUtils;
import com.bosssoft.ecds.security.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @ClassName CustomAuthenticationSuccessHandler
 * @Author AloneH
 * @Date 2020/7/27 17:13
 * @Description
 *              自定义认证成功处理逻辑
 *                  1、 设置响应头
 *                  2、 根据用户名和密码生成token
 *                  3、 设置返回信息
 *                  4、 写入响应信息
 **/

@Component
public class CustomAuthenticationSuccessHandler extends WebFilterChainServerAuthenticationSuccessHandler {

    @Autowired
    SecurityUserServiceImpl userService;

    @Autowired
    RsaKeyProperties rsaKeyProperties;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        // 设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        // Todo 上线删除本行
        httpHeaders.setAccessControlAllowOrigin("http://localhost:9528");
        // 设置body
        byte[] dataBytes = {};
        try {
            // 获取验证对象
            AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
            userDetails.setPassword((String) authentication.getCredentials());

            // 权限信息 存入redis
            Map<String, Object> stringObjectMap = BeanUtil.beanToMap(userDetails);
            redisUtils.set(userDetails.getUsername(), stringObjectMap);

            // 设置用户名 昵称 id 等信息
            UserVO userVO = new UserVO();
            userVO.setId(userDetails.getId());
            userVO.setUsername(userDetails.getUsername());
            userVO.setNickname(userDetails.getNickname());
            userVO.setRoles(BeanUtils.copyListProperties(userDetails.getAuthorities(), RoleVO.class));

            // 清除赘余信息 加密token
            String privateKey = rsaKeyProperties.getPrivateKey();
            RSA rsa = new RSA(privateKey, null);
            userDetails.setAuthorities(null);
            userDetails.setPassword(null);
            String token = JwtUtils.generateTokenExpireInMinutes(userDetails, rsa.getPrivateKey(), 60 * 24 * 30);
            userVO.setToken(token);
            dataBytes = ResponseUtils.getResponse(userVO, ResponseUtils.ResultType.OK).getBytes(StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
            dataBytes = ResponseUtils.getResponse(ex.getMessage(), ResponseUtils.ResultType.UNAUTHORIZED).getBytes();
        }
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

}

