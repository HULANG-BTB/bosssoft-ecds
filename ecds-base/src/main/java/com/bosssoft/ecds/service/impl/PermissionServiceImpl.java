package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.PermissionDao;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PermissionDTO;
import com.bosssoft.ecds.entity.po.PermissionPO;
import com.bosssoft.ecds.service.PermissionService;
import com.bosssoft.ecds.utils.MyBeanUtil;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionPO> implements PermissionService {

    /**
     * 保存接口
     *
     * @param entity
     * @return
     */
    @Override
    public PermissionDTO save(PermissionDTO entity) {
        PermissionPO permissionPO = new PermissionPO();
        MyBeanUtil.copyProperties(entity, permissionPO);
        super.save(permissionPO);
        return entity;
    }

    /**
     * 删除数据
     *
     * @param entity
     * @return
     */
    @Override
    public Boolean remove(PermissionDTO entity) {
        PermissionPO permissionPO = new PermissionPO();
        MyBeanUtil.copyProperties(entity, permissionPO);
        return super.removeById(permissionPO.getId());
    }

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    @Override
    public Boolean update(PermissionDTO entity) {
        PermissionPO permissionPO = super.getById(entity.getId());
        MyBeanUtil.copyProperties(entity, permissionPO);
        return super.updateById(permissionPO);
    }

    /**
     * 根据角色ID 读取权限
     *
     * @param entity
     * @return
     */
    @Override
    public List<PermissionDTO> getByRid(PermissionDTO entity) {
        List<PermissionPO> byRid = super.getBaseMapper().getByRid(entity.getId());
        return MyBeanUtil.copyListProperties(byRid, PermissionDTO.class);
    }

    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<PermissionDTO> listAll() {
        QueryWrapper<PermissionPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc(PermissionPO.F_URL);
        List<PermissionPO> permissionPOList = super.list(queryWrapper);
        // 转换数据
        return MyBeanUtil.copyListProperties(permissionPOList, PermissionDTO.class);
    }

    /**
     * 分页读取
     *
     * @param pageDTO
     * @return
     */
    @Override
    public PageDTO<PermissionDTO> listByPage(PageDTO<PermissionDTO> pageDTO) {
        Page<PermissionPO> roleDTOPage = new Page<>();
        // 设置分页信息
        roleDTOPage.setCurrent(pageDTO.getPage());
        roleDTOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<PermissionPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(PermissionPO.F_URL, pageDTO.getKeyword()).or().like(PermissionPO.F_NAME, pageDTO.getKeyword());
        queryWrapper.orderByAsc(PermissionPO.F_URL);
        // 读取分页数据
        Page<PermissionPO> rolePOPage = super.page(roleDTOPage, queryWrapper);
        List<PermissionPO> records = rolePOPage.getRecords();
        // 数据转换
        List<PermissionDTO> permissionDTOList = MyBeanUtil.copyListProperties(records, PermissionDTO.class);
        pageDTO.setTotal(rolePOPage.getTotal());
        pageDTO.setItems(permissionDTOList);
        return pageDTO;
    }

    /**
     * 查询权限树
     *
     * @return
     */
    @Override
    public List<PermissionDTO> listByTree() {
        List<PermissionPO> permissions = this.getBaseMapper().queryTreeList(0L);
        return MyBeanUtil.copyListProperties(permissions, PermissionDTO::new);
    }

    /**
     * 批量删除权限
     *
     * @param permissionDTOList
     * @return
     */
    @Override
    public Boolean removeBatch(List<PermissionDTO> permissionDTOList) {
        List<Long> ids = new ArrayList<>();
        for (PermissionDTO permissionDTO : permissionDTOList) {
            if (permissionDTO.getId() != null) {
                ids.add(permissionDTO.getId());
            }
        }
        return this.removeByIds(ids);
    }

}
