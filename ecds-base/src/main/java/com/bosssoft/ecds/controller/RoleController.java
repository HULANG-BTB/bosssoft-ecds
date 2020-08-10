package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PermissionDTO;
import com.bosssoft.ecds.entity.dto.RoleDTO;
import com.bosssoft.ecds.service.impl.RoleServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.PermissionVO;
import com.bosssoft.ecds.entity.vo.RoleVO;
import com.bosssoft.ecds.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author AloneH
 * @since 2020-08-10
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    /**
     * 插入数据
     * @param roleVO
     * @return
     */
    @PostMapping("save")
    public QueryResponseResult save(@RequestBody RoleVO roleVO) {
        // 转换为DTO
        RoleDTO roleDTO = MyBeanUtil.copyProperties(roleVO, RoleDTO.class);
        List<PermissionVO> permissionVOList = roleVO.getPermissions();
        List<PermissionDTO> permissionDTOList = MyBeanUtil.copyListProperties(permissionVOList, PermissionDTO.class);
        roleDTO.setPermissions(permissionDTOList);
        // 执行业务逻辑
        roleDTO = roleService.save(roleDTO);
        // 转换为VO

        roleVO = MyBeanUtil.copyProperties(roleDTO, RoleVO.class);
        permissionDTOList = roleDTO.getPermissions();
        permissionVOList = MyBeanUtil.copyListProperties(permissionDTOList, PermissionVO.class);

        roleVO.setPermissions(permissionVOList);
        return new QueryResponseResult(CommonCode.SUCCESS, roleVO);
    }

    /**
     * 更新数据
     * @param roleVO
     * @return
     */
    @PutMapping("update")
    public QueryResponseResult update(@RequestBody RoleVO roleVO) {
        // 转换为DTO

        RoleDTO roleDTO = MyBeanUtil.copyProperties(roleVO, RoleDTO.class);
        List<PermissionVO> permissionVOList = roleVO.getPermissions();
        List<PermissionDTO> permissionDTOList = MyBeanUtil.copyListProperties(permissionVOList, PermissionDTO.class);

        roleDTO.setPermissions(permissionDTOList);
        // 执行业务逻辑
        Boolean result = roleService.update(roleDTO);
        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    /**
     * 读取全部
     * @return
     */
    @GetMapping("list")
    public QueryResponseResult listAll() {
        // 执行业务逻辑
        List<RoleDTO> roleDTOList = roleService.listAll();
        // 转换为VO
        List<RoleVO> roleVOList = MyBeanUtil.copyListProperties(roleDTOList, RoleVO.class);
        return new QueryResponseResult(CommonCode.SUCCESS, roleVOList);
    }

    /**
     * 通过ID读取
     * @return
     */
    @GetMapping("listByUserId")
    public QueryResponseResult listByUserId(@RequestParam("id") Long id) {
        // 执行业务逻辑
        List<RoleDTO> roleDTOList = roleService.listByUserId(id);
        // 转换为VO
        List<RoleVO> roleVOList = MyBeanUtil.copyListProperties(roleDTOList, RoleVO.class);
        return new QueryResponseResult(CommonCode.SUCCESS, roleVOList);
    }

    /**
     * 分页读取
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("listByPage")
    public QueryResponseResult listByPage(@RequestParam("page") Long page, @RequestParam("limit") Long limit, @RequestParam("keyword") String keyword) {
        PageVO pageVO = new PageVO();
        pageVO.setLimit(limit);
        pageVO.setPage(page);
        pageVO.setKeyword(keyword);
        PageDTO pageDTO = MyBeanUtil.copyProperties(pageVO, PageDTO.class);
        pageDTO = roleService.listByPage(pageDTO);

        pageVO = MyBeanUtil.copyProperties(pageDTO, PageVO.class);
        return new QueryResponseResult(CommonCode.SUCCESS, pageVO);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("remove/{id}")
    public QueryResponseResult remove(@PathVariable("id") Long id) {
        RoleVO roleVO = new RoleVO();
        roleVO.setId(id);

        RoleDTO roleDTO = MyBeanUtil.copyProperties(roleVO, RoleDTO.class);
        Boolean result = roleService.remove(roleDTO);
        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    /**
     * 批量删除
     * @param roleVOList
     * @return
     */
    @DeleteMapping("removeBatch")
    public QueryResponseResult removeBatch(@RequestBody List<RoleVO> roleVOList) {
        List<RoleDTO> roleDTOList = MyBeanUtil.copyListProperties(roleVOList, RoleDTO.class);
        Boolean result = roleService.removeBatch(roleDTOList);
        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

}

