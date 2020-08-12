package com.bosssoft.ecds.security.entity.domain;

import com.bosssoft.ecds.security.entity.vo.PermissionVO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @ClassName AuthRoleGrantedAuthority
 * @Author AloneH
 * @Date 2020/7/28 8:30
 * @Description
 *              Security授权角色信息的继承类
 *                  1、 增加自定义的部分字段
 *                  2、 getAuthority方法返回角色
 *              将角色具有的权限定义再列表中
 **/

@Data
public class AuthRoleGrantedAuthority implements GrantedAuthority {

    private Long id;

    private String role;

    private String name;

    private List<PermissionVO> permissions;

    @Override
    public String getAuthority() {
        return this.role;
    }

    public void setAuthority(String role) {
        this.role = role;
    }
}
