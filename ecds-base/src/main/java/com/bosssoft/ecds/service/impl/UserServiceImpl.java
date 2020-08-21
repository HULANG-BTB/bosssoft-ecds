package com.bosssoft.ecds.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.UserDao;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.UserDTO;
import com.bosssoft.ecds.entity.po.RolePO;
import com.bosssoft.ecds.entity.po.UserPO;
import com.bosssoft.ecds.entity.po.UserRolePO;
import com.bosssoft.ecds.service.UserService;
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
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserDao, UserPO> implements UserService {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserRoleServiceImpl userRoleService;

    @Autowired
    RoleServiceImpl roleService;

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

        // 获得请求中的角色列表
        List<RolePO> newRoleList = MyBeanUtil.copyListProperties(userDTO.getRoles(), RolePO.class);

        // 删除所有原来有的角色列表
        QueryWrapper<UserRolePO> userRolePOQueryWrapper = new QueryWrapper<>();
        userRolePOQueryWrapper.eq(UserRolePO.F_USER_ID, userDTO.getId());
        boolean remove = userRoleService.remove(userRolePOQueryWrapper);

        // 添加新的角色列表
        List<UserRolePO> userRoleList = new ArrayList<>();
        for (RolePO rolePO : newRoleList) {
            UserRolePO userRolePO = new UserRolePO();
            userRolePO.setUserId(userDTO.getId());
            userRolePO.setRoleId(rolePO.getId());
            userRoleList.add(userRolePO);
        }
        boolean save = userRoleService.saveBatch(userRoleList);

        return save && remove;
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

    @Override
    public UserDTO getById() {
        Long authId = Long.valueOf(httpServletRequest.getHeader("auth_id"));
        UserPO userPO = super.getById(authId);
        userPO.setPassword(null);
        List<RolePO> rolePOS = roleService.getBaseMapper().selectByUid(userPO.getId());
        userPO.setRoles(rolePOS);
        UserDTO userDTO = MyBeanUtil.copyProperties(userPO, UserDTO.class);
        return userDTO;
    }

}
