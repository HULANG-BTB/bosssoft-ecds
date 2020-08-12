package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PermissionDTO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.PermissionVO;
import com.bosssoft.ecds.service.impl.PermissionServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author AloneH
 * @since 2020-08-08
 */
@RestController
@RequestMapping("permission")
@Api(value = "权限管理接口")
@Slf4j
public class PermissionController {

    @Autowired
    PermissionServiceImpl permissionService;

    /**
     * 插入数据
     *
     * @param permissionVO
     * @return
     */
    @PostMapping("save")
    @ApiOperation(value = "添加权限", notes = "返回添加的权限实体信息")
    public QueryResponseResult<PermissionVO> save(@RequestBody @Validated PermissionVO permissionVO) {
        PermissionDTO permissionDTO = new PermissionDTO();
        MyBeanUtil.copyProperties(permissionVO, permissionDTO);
        permissionDTO = permissionService.save(permissionDTO);
        MyBeanUtil.copyProperties(permissionDTO, permissionVO);
        return new QueryResponseResult<>(CommonCode.SUCCESS, permissionVO);
    }

    /**
     * 更新数据
     *
     * @param permissionVO
     * @return
     */
    @PutMapping("update")
    @ApiOperation(value = "更新权限信息")
    public QueryResponseResult<Boolean> update(@RequestBody @Validated PermissionVO permissionVO) {
        PermissionDTO permissionDTO = new PermissionDTO();
        MyBeanUtil.copyProperties(permissionVO, permissionDTO);
        Boolean result = permissionService.update(permissionDTO);
        return new QueryResponseResult<>(CommonCode.SUCCESS, result);
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @DeleteMapping("remove/{id}")
    @ApiOperation(value = "删除权限")
    public QueryResponseResult<Boolean> remove(@PathVariable("id") Long id) {
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setId(id);
        PermissionDTO permissionDTO = new PermissionDTO();
        MyBeanUtil.copyProperties(permissionVO, permissionDTO);
        Boolean result = permissionService.remove(permissionDTO);
        return new QueryResponseResult<>(CommonCode.SUCCESS, result);
    }

    /**
     * 通过分页查询
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("listByPage")
    @ApiOperation(value = "分页查询列表")
    public QueryResponseResult<PageVO> listByPage(@RequestParam("page") Long page, @RequestParam("limit") Long limit, @RequestParam("keyword") String keyword) {
        PageVO pageVO = new PageVO();
        pageVO.setLimit(limit);
        pageVO.setPage(page);
        pageVO.setKeyword(keyword);
        PageDTO<PermissionDTO> pageDTO = MyBeanUtil.copyProperties(pageVO, PageDTO.class);
        pageDTO = permissionService.listByPage(pageDTO);

        pageVO = MyBeanUtil.copyProperties(pageDTO, PageVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS, pageVO);
    }

    /**
     * 通过角色ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("getByRid/{id}")
    @ApiOperation(value = "通过角色ID查询权限列表")
    public QueryResponseResult<List<PermissionVO>> getByRid(@PathVariable("id") Long id) {
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setId(id);

        PermissionDTO permissionDTO = MyBeanUtil.copyProperties(permissionVO, PermissionDTO.class);

        List<PermissionDTO> permissionDTOList = permissionService.getByRid(permissionDTO);
        List<PermissionVO> permissionVOList = MyBeanUtil.copyListProperties(permissionDTOList, PermissionVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS, permissionVOList);
    }

    /**
     * 查询整体列表
     *
     * @return
     */
    @GetMapping("list")
    @ApiOperation(value = "查询所有列表")
    public QueryResponseResult<List<PermissionVO>> listAll() {
        List<PermissionDTO> permissionDTOList = permissionService.listAll();
        List<PermissionVO> permissionVOList = MyBeanUtil.copyListProperties(permissionDTOList, PermissionVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS, permissionVOList);
    }

    /**
     * 读取权限树列表
     */
    @GetMapping("listByTree")
    @ApiOperation(value = "查询权限树")
    public QueryResponseResult<List<PermissionVO>> getTreeList() {
        List<PermissionDTO> treeList = permissionService.listByTree();
        List<PermissionVO> permissionVOList = MyBeanUtil.copyListProperties(treeList, PermissionVO::new);
        return new QueryResponseResult<>(CommonCode.SUCCESS, permissionVOList);
    }

    /**
     * 批量删除
     *
     * @param permissionVOList
     * @return
     */
    @DeleteMapping("removeBatch")
    @ApiOperation(value = "批量删除")
    public QueryResponseResult<Boolean> removeBatch(@RequestBody List<PermissionVO> permissionVOList) {
        List<PermissionDTO> permissionDTOList = MyBeanUtil.copyListProperties(permissionVOList, PermissionDTO.class);
        Boolean result = permissionService.removeBatch(permissionDTOList);
        return new QueryResponseResult<>(CommonCode.SUCCESS, result);
    }

}

