package com.bosssoft.ecds.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.security.entity.po.UserPO;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @InterfaceName UserService
 * @Author AloneH
 * @Date 2020/8/9 16:55
 * @Description
 *      security service
 **/
public interface SecurityUserService extends ReactiveUserDetailsService, IService<UserPO>  {

}
