package com.bosssoft.ecds.security.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.security.dao.UserDao;
import com.bosssoft.ecds.security.entity.domain.AuthRoleGrantedAuthority;
import com.bosssoft.ecds.security.entity.domain.AuthUserDetails;
import com.bosssoft.ecds.security.entity.po.PermissionPO;
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
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Author AloneH
 * @Date 2020/8/9 16:56
 * @Description Security service impl
 **/

@Service
public class SecurityUserServiceImpl extends ServiceImpl<UserDao, UserPO> implements SecurityUserService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PermissionServiceImpl permissionService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        // 查询用户
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UserPO.F_USERNAME, username);
        UserPO user = super.getOne(queryWrapper);

        if (user == null) {
            return Mono.empty();
        }

        // 读取角色列表
        List<RolePO> rolePOList = roleService.getBaseMapper().listByUid(user.getId());
        // 读取权限列表
        List<PermissionPO> permissionPOList = permissionService.getBaseMapper().listByUid(user.getId());

        // 创建 AuthUserDetails 对象
        AuthUserDetails authUserDetails = new AuthUserDetails();
        authUserDetails.setId(user.getId());
        authUserDetails.setUsername(user.getUsername());
        authUserDetails.setPassword(user.getPassword());
        authUserDetails.setNickname(user.getNickname());
        if (rolePOList != null) {
            List<AuthRoleGrantedAuthority> authRoleGrantedAuthorities = new ArrayList<>();
            for (RolePO rolePO : rolePOList) {
                AuthRoleGrantedAuthority authRoleGrantedAuthority = new AuthRoleGrantedAuthority();
                authRoleGrantedAuthority.setId(rolePO.getId());
                authRoleGrantedAuthority.setName(rolePO.getName());
                authRoleGrantedAuthority.setRole(rolePO.getRole());
                authRoleGrantedAuthorities.add(authRoleGrantedAuthority);
            }
            authUserDetails.setAuthorities(authRoleGrantedAuthorities);
        }

        if (permissionPOList != null) {
            List<PermissionVO> permissionVOS = BeanUtils.copyListProperties(permissionPOList, PermissionVO::new);
            authUserDetails.setPermissions(permissionVOS);
        }

        return Mono.just(authUserDetails);
    }
}
