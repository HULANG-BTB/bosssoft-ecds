package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.RoleDTO;
import com.bosssoft.ecds.entity.po.RolePO;
import java.util.List;

/**
 * @InterfaceName RoleService
 * @Author AloneH
 * @Date 2020/7/29 20:52
 * @Description TODO
 **/

public interface RoleService extends IService<RolePO> {

    /**
     * 插入角色
     *
     * @param roleDTO
     * @return
     */
    RoleDTO save(RoleDTO roleDTO);

    /**
     * 删除角色
     *
     * @param entity
     * @return
     */
    Boolean remove(RoleDTO entity);

    /**
     * 更新角色信息
     *
     * @param roleDTO
     * @return Boolean true 更新成功 false 更新失败
     */
    Boolean update(RoleDTO roleDTO);

    /**
     * 读取角色列表
     *
     * @return
     */
    List<RoleDTO> listAll();

    /**
     * 通过UID读取角色列表
     *
     * @return
     */
    List<RoleDTO> listByUserId(Long id);

    /**
     * 分页读取
     *
     * @param pageDTO
     * @return
     */
    PageDTO<RoleDTO> listByPage(PageDTO<RoleDTO> pageDTO);

    /**
     * 批量删除角色
     *
     * @param roleDTOList
     * @return
     */
    Boolean removeBatch(List<RoleDTO> roleDTOList);

}
