package com.bosssoft.ecds.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.RoleDao;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.RoleDTO;
import com.bosssoft.ecds.entity.po.PermissionPO;
import com.bosssoft.ecds.entity.po.RolePO;
import com.bosssoft.ecds.entity.po.RolePermissionPO;
import com.bosssoft.ecds.service.RoleService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author AloneH
 * @since 2020-07-25
 */
@Service
@Slf4j
@DS("master")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RolePO> implements RoleService {

    @Autowired
    RolePermissionServiceImpl rolePermissionService;

    /**
     * 插入角色
     *
     * @param roleDTO
     * @return
     */
    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        // 转换为PO
        RolePO rolePO = MyBeanUtil.copyProperties(roleDTO, RolePO.class);
        List<PermissionPO> permissionPOList = MyBeanUtil.copyListProperties(roleDTO.getPermissions(), PermissionPO.class);
        // 插入角色信息
        boolean roleSaveResult = super.save(rolePO);
        // 转换为DTO
        roleDTO = MyBeanUtil.copyProperties(rolePO, RoleDTO.class);
        if (roleSaveResult) {
            // 持久层列表 角色权限列表
            List<RolePermissionPO> rolePermissionPOList = new ArrayList<>();
            for (PermissionPO permission : permissionPOList) {
                RolePermissionPO rolePermissionPO = new RolePermissionPO();
                rolePermissionPO.setPermissionId(permission.getId());
                rolePermissionPO.setRoleId(rolePO.getId());
                rolePermissionPOList.add(rolePermissionPO);
            }
            rolePermissionService.saveBatch(rolePermissionPOList);
        }
        return roleDTO;
    }

    /**
     * 删除角色
     *
     * @param entity
     * @return
     */
    @Override
    public Boolean remove(RoleDTO entity) {
        RolePO rolePO = MyBeanUtil.copyProperties(entity, RolePO.class);
        return super.removeById(rolePO.getId());
    }

    /**
     * 更新角色信息
     *
     * @param roleDTO
     * @return Boolean true 更新成功 false 更新失败
     */
    @Override
    public Boolean update(RoleDTO roleDTO) {
        // 读取角色信息
        RolePO rolePO = super.getById(roleDTO.getId());
        MyBeanUtil.copyProperties(roleDTO, rolePO);

        // 读取权限列表
        List<PermissionPO> newPermissionList = MyBeanUtil.copyListProperties(roleDTO.getPermissions(), PermissionPO.class);

        // 删除原来已经拥有的权限列表
        QueryWrapper<RolePermissionPO> rolePermissionPOQueryWrapper = new QueryWrapper<>();
        rolePermissionPOQueryWrapper.eq(RolePermissionPO.F_ROLE_ID, roleDTO.getId());
        boolean remove = rolePermissionService.remove(rolePermissionPOQueryWrapper);

        // 批量添加新的角色列表
        List<RolePermissionPO> insertAndUpdateList = new ArrayList<>();
        for (PermissionPO permissionPO : newPermissionList) {
            RolePermissionPO rolePermissionPO = new RolePermissionPO();
            rolePermissionPO.setRoleId(roleDTO.getId());
            rolePermissionPO.setPermissionId(permissionPO.getId());
            insertAndUpdateList.add(rolePermissionPO);
        }
        boolean save = rolePermissionService.saveBatch(insertAndUpdateList);

        return save && remove;
    }

    /**
     * 读取角色列表
     *
     * @return
     */
    @Override
    public List<RoleDTO> listAll() {
        QueryWrapper<RolePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc(RolePO.F_CREATE_TIME);
        List<RolePO> rolePOList = super.list(queryWrapper);
        // 转换数据
        return MyBeanUtil.copyListProperties(rolePOList, RoleDTO::new);
    }

    /**
     * 通过UID读取角色列表
     *
     * @return
     */
    @Override
    public List<RoleDTO> listByUserId(Long id) {
        List<RolePO> rolePOList = super.getBaseMapper().selectByUid(id);
        return MyBeanUtil.copyListProperties(rolePOList, RoleDTO::new);
    }

    /**
     * 分页读取
     *
     * @param pageDTO
     * @return
     */
    @Override
    public PageDTO<RoleDTO> listByPage(PageDTO<RoleDTO> pageDTO) {
        Page<RolePO> roleDTOPage = new Page<>();
        // 设置分页信息
        roleDTOPage.setCurrent(pageDTO.getPage());
        roleDTOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<RolePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(RolePO.F_ROLE, pageDTO.getKeyword()).or().like(RolePO.F_NAME, pageDTO.getKeyword());
        queryWrapper.orderByAsc(RolePO.F_CREATE_TIME);
        // 读取分页数据
        Page<RolePO> rolePOPage = super.page(roleDTOPage, queryWrapper);
        List<RolePO> records = rolePOPage.getRecords();
        // 转换数据
        List<RoleDTO> roleDTOList = MyBeanUtil.copyListProperties(records, RoleDTO::new);
        pageDTO.setTotal(rolePOPage.getTotal());
        pageDTO.setItems(roleDTOList);
        return pageDTO;
    }

    /**
     * 批量删除角色
     *
     * @param roleDTOList
     * @return
     */
    @Override
    public Boolean removeBatch(List<RoleDTO> roleDTOList) {
        List<Long> ids = new ArrayList<>();
        for (RoleDTO roleDTO : roleDTOList) {
            if (roleDTO.getId() != null) {
                ids.add(roleDTO.getId());
            }
        }
        return this.removeByIds(ids);
    }
}
