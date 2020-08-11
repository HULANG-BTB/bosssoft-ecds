package com.bosssoft.ecds.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.UserDTO;
import com.bosssoft.ecds.dao.UserDao;
import com.bosssoft.ecds.entity.po.RolePO;
import com.bosssoft.ecds.entity.po.UserPO;
import com.bosssoft.ecds.entity.po.UserRolePO;
import com.bosssoft.ecds.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
public class UserServiceImpl extends ServiceImpl<UserDao, UserPO> implements UserService {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserRoleServiceImpl userRoleService;

    /**
     * 插入用户
     *
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO save(UserDTO userDTO) {
        // 转换为PO
        UserPO userPO = MyBeanUtil.copyProperties(userDTO, UserPO.class);
        List<RolePO> rolePOList = MyBeanUtil.copyListProperties(userDTO.getRoles(), RolePO.class);
        // 插入用户信息
        boolean userSaveResult = super.save(userPO);
        // 转换为DTO
        userDTO = MyBeanUtil.copyProperties(userPO, UserDTO.class);
        if (userSaveResult) {
            ArrayList<UserRolePO> userRolePOS = new ArrayList<>();
            for (RolePO rolePO : rolePOList) {
                UserRolePO userRolePO = new UserRolePO();
                userRolePO.setUserId(userPO.getId());
                userRolePO.setRoleId(rolePO.getId());
                userRolePOS.add(userRolePO);
            }
            userRoleService.saveBatch(userRolePOS);
        }
        return userDTO;
    }

    /**
     * 删除用户
     *
     * @param entity
     * @return
     */
    @Override
    public Boolean remove(UserDTO entity) {
        UserPO userPO = MyBeanUtil.copyProperties(entity, UserPO.class);
        return super.removeById(userPO.getId());
    }

    /**
     * 更新用户
     *
     * @param userDTO
     * @return
     */
    @Override
    public Boolean update(UserDTO userDTO) {
        // 读取用户信息
        UserPO userPO = super.getById(userDTO.getId());
        MyBeanUtil.copyProperties(userDTO, userPO);

        // 读取角色列表
        List<RolePO> newRoleList = MyBeanUtil.copyListProperties(userDTO.getRoles(), RolePO.class);

        // 读取用户已经拥有角色列表
        QueryWrapper<UserRolePO> userRolePOQueryWrapper = new QueryWrapper<>();
        userRolePOQueryWrapper.eq(UserRolePO.F_USER_ID, userDTO.getId());
        List<UserRolePO> userRoleHasExist = userRoleService.list(userRolePOQueryWrapper);

        // 需要删除的权限列表 记录ID
        ArrayList<Long> removeList = new ArrayList<>();
        // 需要更新的角色 和 需要添加的
        ArrayList<UserRolePO> insertAndUpdateList = new ArrayList<>();

        // 整合需要删除的角色 和 需要更新的权限
        for (UserRolePO userRolePO : userRoleHasExist) {
            Boolean shouldDelete = true;
            for (RolePO rolePO : newRoleList) {
                // 原有的角色在新的角色列表中存在 则不需要删除 标记为需要更新
                if (rolePO.getId().equals(userRolePO.getRoleId())) {
                    insertAndUpdateList.add(userRolePO);
                    shouldDelete = false;
                    break;
                }
            }
            if (shouldDelete.booleanValue()) {
                removeList.add(userRolePO.getId());
            }
        }

        // 整合需要添加的角色
        for (RolePO rolePO : newRoleList) {
            Boolean shouldAdd = true;
            for (UserRolePO userRolePO : userRoleHasExist) {
                // 新的角色在原来的角色列表中存在 则不需要添加
                if (rolePO.getId().equals(userRolePO.getRoleId())) {
                    shouldAdd = false;
                    break;
                }
            }
            if (shouldAdd.booleanValue()) {
                UserRolePO userRolePO = new UserRolePO();
                userRolePO.setUserId(userDTO.getId());
                userRolePO.setRoleId(rolePO.getId());
                insertAndUpdateList.add(userRolePO);
            }
        }

        // 更新用户信息
        boolean roleUpdateResult = super.updateById(userPO);
        // 删除需要删除的
        boolean removeByIdsResult = userRoleService.removeByIds(removeList);
        // 更新或者添加需要更新的和需要添加的
        boolean saveOrUpdateBatchResult = userRoleService.saveOrUpdateBatch(insertAndUpdateList);

        return roleUpdateResult && removeByIdsResult && saveOrUpdateBatchResult;
    }

    /**
     * 分页读取
     *
     * @param pageDTO
     * @return
     */
    @Override
    public PageDTO<UserDTO> listByPage(PageDTO<UserDTO> pageDTO) {
        Page<UserPO> userDTOPage = new Page<>();
        // 设置分页信息
        userDTOPage.setCurrent(pageDTO.getPage());
        userDTOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(UserPO.F_ID, pageDTO.getKeyword()).or().like(UserPO.F_USERNAME, pageDTO.getKeyword()).or().like(UserPO.F_NICKNAME, pageDTO.getKeyword());
        queryWrapper.orderByAsc(UserPO.F_CREATE_TIME);
        // 读取分页数据
        Page<UserPO> userPOPage = super.page(userDTOPage, queryWrapper);
        List<UserPO> records = userPOPage.getRecords();
        // 转换数据
        List<UserDTO> userDTOList = MyBeanUtil.copyListProperties(records, UserDTO::new);
        pageDTO.setTotal(userPOPage.getTotal());
        pageDTO.setItems(userDTOList);
        return pageDTO;
    }

    /**
     * 批量删除角色
     *
     * @param userDTOList
     * @return
     */
    @Override
    public Boolean removeBatch(List<UserDTO> userDTOList) {
        List<Long> ids = new ArrayList<>();
        for (UserDTO userDTO : userDTOList) {
            if (!userDTO.getId().equals(0L)) {
                ids.add(userDTO.getId());
            }
        }
        return super.removeByIds(ids);
    }

    /**
     * 重置密码
     * @param userDTO
     * @return
     */
    @Override
    public Boolean resetPassword(UserDTO userDTO) {
        UserPO userPO = MyBeanUtil.copyProperties(userDTO, UserPO.class);
        userPO = super.getById(userPO.getId());
        String hashpw = BCrypt.hashpw(userDTO.getPassword());
        userPO.setPassword(hashpw);
        return super.updateById(userPO);
    }

}
