package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.RoleDTO;
import com.bosssoft.ecds.entity.dto.UserDTO;
import com.bosssoft.ecds.service.impl.RoleServiceImpl;
import com.bosssoft.ecds.service.impl.UserServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.RoleVO;
import com.bosssoft.ecds.entity.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author AloneH
 * @since 2020-07-25
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    /**
     * 插入用户
     * @param userVO
     * @return
     */
    @PostMapping("save")
    public QueryResponseResult<UserVO> save(@RequestBody UserVO userVO) {
        // 转换为DTO
        UserDTO userDTO = MyBeanUtil.copyProperties(userVO, UserDTO.class);
        List<RoleVO> roleVOList = userVO.getRoles();
        List<RoleDTO> roleDTOList = MyBeanUtil.copyListProperties(roleVOList, RoleDTO.class);
        userDTO.setRoles(roleDTOList);
        // 执行业务逻辑
        userDTO = userService.save(userDTO);
        // 转换为VO
        userVO = MyBeanUtil.copyProperties(userDTO, UserVO.class);
        roleDTOList = userDTO.getRoles();
        roleVOList = MyBeanUtil.copyListProperties(roleDTOList, RoleVO.class);
        userVO.setRoles(roleVOList);
        return new QueryResponseResult<>(CommonCode.SUCCESS, userVO);
    }

    /**
     * 更新用户
     * @param userVO
     * @return
     */
    @PutMapping("update")
    public QueryResponseResult<Boolean> update(@RequestBody UserVO userVO) {
        UserDTO userDTO = MyBeanUtil.copyProperties(userVO, UserDTO.class);
        Boolean result = userService.update(userDTO);
        return new QueryResponseResult<>(CommonCode.SUCCESS, result);
    }

    /**
     * 分页读取
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("listByPage")
    public QueryResponseResult<PageVO> listByPage(@RequestParam("page") Long page, @RequestParam("limit") Long limit, @RequestParam("keyword") String keyword) {
        PageVO pageVO = new PageVO();
        pageVO.setLimit(limit);
        pageVO.setPage(page);
        pageVO.setKeyword(keyword);
        PageDTO<UserDTO> pageDTO = MyBeanUtil.copyProperties(pageVO, PageDTO.class);
        pageDTO = userService.listByPage(pageDTO);
        pageVO = MyBeanUtil.copyProperties(pageDTO, PageVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS, pageVO);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("remove/{id}")
    public QueryResponseResult<Boolean> remove(@PathVariable("id") Long id) {
        UserVO userVO = new UserVO();
        userVO.setId(id);
        UserDTO userDTO = MyBeanUtil.copyProperties(userVO, UserDTO.class);
        Boolean result = userService.remove(userDTO);
        return new QueryResponseResult<>(CommonCode.SUCCESS, result);
    }

    /**
     * 批量删除
     * @param userVOList
     * @return
     */
    @DeleteMapping("removeBatch")
    public QueryResponseResult<Boolean> removeBatch(@RequestBody List<UserVO> userVOList) {
        List<UserDTO> userDTOList = MyBeanUtil.copyListProperties(userVOList, UserDTO::new);
        Boolean result = userService.removeBatch(userDTOList);
        return new QueryResponseResult<>(CommonCode.SUCCESS, result);
    }

    @PutMapping("resetPassword")
    public QueryResponseResult<Boolean> resetPassword(@RequestBody UserVO userVO) {
        UserDTO userDTO = MyBeanUtil.copyProperties(userVO, UserDTO.class);
        Boolean resetResult = userService.resetPassword(userDTO);
        return new QueryResponseResult<>(CommonCode.SUCCESS, resetResult);
    }

}

