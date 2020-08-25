package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.RolePO;

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

    /**
     * 通过UID读取
     * @param uid
     * @return
     */
    List<RolePO> selectByUid(Serializable uid);

}
