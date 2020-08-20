package com.bosssoft.ecds.security.auth;

import com.bosssoft.ecds.security.service.impl.SecurityUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @ClassName CustmoAuthenticationManager
 * @Author AloneH
 * @Date 2020/7/28 16:56
 * @Description
 * AuthenticationManager 负责校验 Authentication 对象。
 * 在 AuthenticationManager 的 authenticate 函数中，开发人员实现对 Authentication 的校验逻辑。
 *
 * 此处调用 UserServiceImpl 中的 loadByUsername 接口 得到一个数据库对象
 *
 *
 **/

@Component
public class CustomReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private final SecurityUserServiceImpl userDetailsService;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    private ReactiveUserDetailsPasswordService userDetailsPasswordService;

    private Scheduler scheduler = Schedulers.parallel();

    public CustomReactiveAuthenticationManager(SecurityUserServiceImpl userDetailsService) {
        Assert.notNull(userDetailsService, "userDetailsService cannot be null");
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        final String username = authentication.getName();
        final String presentedPassword = (String) authentication.getCredentials();
        return this.userDetailsService.findByUsername(username)
                .publishOn(this.scheduler)
                .filter(u -> this.passwordEncoder.matches(presentedPassword, u.getPassword()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BadCredentialsException("Invalid Credentials"))))
                .flatMap(u -> {
                    boolean upgradeEncoding = this.userDetailsPasswordService != null
                            && this.passwordEncoder.upgradeEncoding(u.getPassword());
                    if (upgradeEncoding) {
                        String newPassword = this.passwordEncoder.encode(presentedPassword);
                        return this.userDetailsPasswordService.updatePassword(u, newPassword);
                    }
                    return Mono.just(u);
                })
                .map(u -> new UsernamePasswordAuthenticationToken(u, presentedPassword, u.getAuthorities()));
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
    }

    public void setScheduler(Scheduler scheduler) {
        Assert.notNull(scheduler, "scheduler cannot be null");
        this.scheduler = scheduler;
    }

    public void setUserDetailsPasswordService(
            ReactiveUserDetailsPasswordService userDetailsPasswordService) {
        this.userDetailsPasswordService = userDetailsPasswordService;
    }
}

