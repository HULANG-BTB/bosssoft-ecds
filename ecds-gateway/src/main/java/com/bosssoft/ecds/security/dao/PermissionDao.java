package com.bosssoft.ecds.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.security.entity.po.PermissionPO;

import java.io.Serializable;
import java.util.List;


public interface PermissionDao extends BaseMapper<PermissionPO> {

    List<PermissionPO> listByUid(Serializable id);

}
