package com.bosssoft.ecds.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.UserDTO;
import com.bosssoft.ecds.entity.po.UserPO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AloneH
 * @since 2020-07-25
 */
public interface UserService extends IService<UserPO> {

    /**
     * 插入用户
     *
     * @param userDTO
     * @return
     */
    UserDTO save(UserDTO userDTO);

    /**
     * 删除用户
     *
     * @param entity
     * @return
     */
    Boolean remove(UserDTO entity);

    /**
     * 更新用户
     *
     * @param userDTO
     * @return
     */
    Boolean update(UserDTO userDTO);

    /**
     * 分页读取
     *
     * @param pageDTO
     * @return
     */
    PageDTO<UserDTO> listByPage(PageDTO<UserDTO> pageDTO);

    /**
     * 批量删除角色
     *
     * @param userDTOList
     * @return
     */
    Boolean removeBatch(List<UserDTO> userDTOList);

    /**
     * 重置密码
     * @param userDTO
     * @return
     */
    Boolean resetPassword(UserDTO userDTO);

}