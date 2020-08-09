package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.RoleDTO;
import com.bosssoft.ecds.entity.dto.UserDTO;
import com.bosssoft.ecds.model.ResultType;
import com.bosssoft.ecds.service.impl.RoleServiceImpl;
import com.bosssoft.ecds.service.impl.UserServiceImpl;
import com.bosssoft.ecds.utils.BeanUtil;
import com.bosssoft.ecds.utils.ResponseUtil;
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
    public String save(@RequestBody UserVO userVO) {
        // 转换为DTO
        UserDTO userDTO = BeanUtil.copyProperties(userVO, UserDTO.class);
        List<RoleVO> roleVOList = userVO.getRoles();
        List<RoleDTO> roleDTOList = BeanUtil.copyListProperties(roleVOList, RoleDTO.class);
        userDTO.setRoles(roleDTOList);
        // 执行业务逻辑
        userDTO = userService.save(userDTO);
        // 转换为VO
        userVO = BeanUtil.copyProperties(userDTO, UserVO.class);
        roleDTOList = userDTO.getRoles();
        roleVOList = BeanUtil.copyListProperties(roleDTOList, RoleVO.class);
        userVO.setRoles(roleVOList);

        return ResponseUtil.getResponse(userVO, ResultType.OK);
    }

    /**
     * 更新用户
     * @param userVO
     * @return
     */
    @PutMapping("update")
    public String update(@RequestBody UserVO userVO) {
        UserDTO userDTO = BeanUtil.copyProperties(userVO, UserDTO.class);
        Boolean result = userService.update(userDTO);
        return ResponseUtil.getResponse(result, ResultType.OK);
    }

    /**
     * 分页读取
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("listByPage")
    public String listByPage(@RequestParam("page") Long page, @RequestParam("limit") Long limit, @RequestParam("keyword") String keyword) {
        PageVO pageVO = new PageVO();
        pageVO.setLimit(limit);
        pageVO.setPage(page);
        pageVO.setKeyword(keyword);
        PageDTO pageDTO = BeanUtil.copyProperties(pageVO, PageDTO.class);
        PageDTO result = userService.listByPage(pageDTO);
        pageVO = BeanUtil.copyProperties(pageDTO, PageVO.class);
        return ResponseUtil.getResponse(result, ResultType.OK);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        UserVO userVO = new UserVO();
        userVO.setId(id);
        UserDTO userDTO = BeanUtil.copyProperties(userVO, UserDTO.class);
        Boolean result = userService.remove(userDTO);
        return ResponseUtil.getResponse(result, ResultType.OK);
    }

    /**
     * 批量删除
     * @param userVOList
     * @return
     */
    @DeleteMapping("removeBatch")
    public String removeBatch(@RequestBody List<UserVO> userVOList) {
        List<UserDTO> userDTOList = BeanUtil.copyListProperties(userVOList, UserDTO::new);
        Boolean result = userService.removeBatch(userDTOList);
        return ResponseUtil.getResponse(result, ResultType.OK);
    }

    @PutMapping("resetPassword")
    public String resetPassword(@RequestBody UserVO userVO) {
        UserDTO userDTO = BeanUtil.copyProperties(userVO, UserDTO.class);
        Boolean resetResult = userService.resetPassword(userDTO);
        return ResponseUtil.getResponse(resetResult, ResultType.OK);
    }

}

