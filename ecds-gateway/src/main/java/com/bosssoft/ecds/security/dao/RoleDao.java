package com.bosssoft.ecds.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.security.entity.po.RolePO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author AloneH
 * @since 2020-07-25
 */
public interface RoleDao extends BaseMapper<RolePO> {

    List<RolePO> listByUid(Serializable id);

}
