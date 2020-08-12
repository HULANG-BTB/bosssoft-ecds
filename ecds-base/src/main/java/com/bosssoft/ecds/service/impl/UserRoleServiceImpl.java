package com.bosssoft.ecds.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.UserRoleDao;
import com.bosssoft.ecds.entity.po.UserRolePO;
import com.bosssoft.ecds.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AloneH
 * @since 2020-07-27
 */
@Service
@DS("master")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRolePO> implements UserRoleService {

}
