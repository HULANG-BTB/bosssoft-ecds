package com.bosssoft.ecds.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.security.dao.UserDao;
import com.bosssoft.ecds.security.entity.domain.AuthRoleGrantedAuthority;
import com.bosssoft.ecds.security.entity.domain.AuthUserDetails;
import com.bosssoft.ecds.security.entity.po.RolePO;
import com.bosssoft.ecds.security.entity.po.UserPO;
import com.bosssoft.ecds.security.entity.vo.PermissionVO;
import com.bosssoft.ecds.security.service.SecurityUserService;
import com.bosssoft.ecds.security.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author AloneH
 * @Date 2020/8/9 16:56
 * @Description TODO
 **/

@Service
public class SecurityUserServiceImpl extends ServiceImpl<UserDao, UserPO> implements SecurityUserService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        // 查询用户
        UserPO userPO = super.getBaseMapper().selectByUsername(username);
        if (userPO == null) {
            return Mono.empty();
        }

        // 设置权限
        AuthUserDetails authUserDetails = new AuthUserDetails();
        List<AuthRoleGrantedAuthority> authRoleGrantedAuthorities = new ArrayList<>();
        List<RolePO> roles = userPO.getRoles();

        if (roles != null) {
            for (RolePO role : roles) {
                AuthRoleGrantedAuthority authRoleGrantedAuthority = new AuthRoleGrantedAuthority();
                authRoleGrantedAuthority.setId(role.getId());
                authRoleGrantedAuthority.setName(role.getName());
                authRoleGrantedAuthority.setRole(role.getRole());
                authRoleGrantedAuthority.setPermissions(BeanUtils.copyListProperties(role.getPermissions(), PermissionVO::new));
                authRoleGrantedAuthorities.add(authRoleGrantedAuthority);
            }
        }
        authUserDetails.setId(userPO.getId());
        authUserDetails.setUsername(userPO.getUsername());
        authUserDetails.setPassword(userPO.getPassword());
        authUserDetails.setNickname(userPO.getNickname());
        authUserDetails.setAuthorities(authRoleGrantedAuthorities);
        return Mono.just(authUserDetails);
    }
}
