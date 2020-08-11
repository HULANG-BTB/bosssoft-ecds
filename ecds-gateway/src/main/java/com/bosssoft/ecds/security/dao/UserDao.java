package com.bosssoft.ecds.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.security.entity.po.UserPO;

import java.io.Serializable;

/**
 * @InterfaceName UserDao
 * @Author AloneH
 * @Date 2020/8/9 16:57
 * @Description TODO
 **/
public interface UserDao extends BaseMapper<UserPO> {
    UserPO selectByUsername(Serializable username);
}
