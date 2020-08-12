package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PermissionDTO;
import com.bosssoft.ecds.entity.po.PermissionPO;
import java.util.List;

/**
 * @InterfaceName PermissionService
 * @Author AloneH
 * @Date 2020/7/29 20:53
 * @Description 权限模块 Service
 **/
public interface PermissionService extends IService<PermissionPO> {

    /**
     * 保存接口
     *
     * @param entity
     * @return
     */
    PermissionDTO save(PermissionDTO entity);

    /**
     * 删除数据
     *
     * @param entity
     * @return
     */
    Boolean remove(PermissionDTO entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    Boolean update(PermissionDTO entity);

    /**
     * 根据角色ID 读取权限
     *
     * @param entity
     * @return
     */
    List<PermissionDTO> getByRid(PermissionDTO entity);

    /**
     * 查询全部
     *
     * @return
     */
    List<PermissionDTO> listAll();

    /**
     * 分页读取
     *
     * @param pageDTO
     * @return
     */
    PageDTO<PermissionDTO> listByPage(PageDTO<PermissionDTO> pageDTO);

    /**
     * 查询权限树
     *
     * @return
     */
    List<PermissionDTO> listByTree();

    /**
     * 批量删除权限
     *
     * @param permissionDTOList
     * @return
     */
    Boolean removeBatch(List<PermissionDTO> permissionDTOList);

}