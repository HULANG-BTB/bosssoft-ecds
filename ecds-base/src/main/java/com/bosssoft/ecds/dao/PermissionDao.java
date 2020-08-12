package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.PermissionPO;

import java.io.Serializable;
import java.util.List;


public interface PermissionDao extends BaseMapper<PermissionPO> {
    /**
     * 通过Rid读取权限列表
     * @param rid
     * @return
     */
    List<PermissionPO> getByRid(Serializable rid);

    /**
     * 根据用户Id查询权限列表
     * @param uid
     * @return
     */
    List<PermissionPO> getByUid(Serializable uid);

    /**
     * 读取权限树
     * @param parentId 父权限ID 默认0
     * @return
     */
    List<PermissionPO> queryTreeList(Serializable parentId);
}
