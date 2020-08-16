package com.bosssoft.ecds.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.RolePermissionDao;
import com.bosssoft.ecds.entity.po.RolePermissionPO;
import com.bosssoft.ecds.service.RolePermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AloneH
 * @since 2020-08-01
 */
@Service
@DS("master")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermissionPO> implements RolePermissionService {

}
