package com.bosssoft.ecds.security.entity.domain;

import com.bosssoft.ecds.security.utils.BeanUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName AuthUserDetails
 * @Author AloneH
 * @Date 2020/7/27 20:30
 * @Description
 * UserDetail
 *            实体的继承类
 *              1、添加自定义部分内容
 *              2、确保用户名和密钥字段为username、password并且具有setter和getter
 **/

@Data
@Accessors(chain = true)
public class AuthUserDetails implements UserDetails {

    private Long id;

    private String username;

    private String nickname;

    private String password;

    private List<GrantedAuthority> authorities;

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(List<? extends GrantedAuthority> authorities) {
        if (null != authorities) {
            this.authorities = new ArrayList<>();
            for (GrantedAuthority authority : authorities) {
                AuthRoleGrantedAuthority authRoleGrantedAuthority = new AuthRoleGrantedAuthority();
                BeanUtils.copyProperties(authority, authRoleGrantedAuthority);
                this.authorities.add(authRoleGrantedAuthority);
            }
        } else {
            this.authorities = null;
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
