package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PermissionDTO;
import com.bosssoft.ecds.service.impl.PermissionServiceImpl;
import com.bosssoft.ecds.utils.BeanUtil;
import com.bosssoft.ecds.utils.ResponseUtils;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.PermissionVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author AloneH
 * @since 2020-07-25
 */
@RestController
@RequestMapping("permission")
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
    @ApiOperation(value = "添加权限")
    @PostMapping("save")
    public String save(@RequestBody PermissionVO permissionVO) {
        PermissionDTO permissionDTO = new PermissionDTO();
        BeanUtil.copyProperties(permissionVO, permissionDTO);
        permissionDTO = permissionService.save(permissionDTO);
        BeanUtil.copyProperties(permissionDTO, permissionVO);
        return ResponseUtils.getResponse(permissionVO, ResponseUtils.ResultType.OK);
    }

    /**
     * 更新数据
     *
     * @param permissionVO
     * @return
     */
    @PutMapping("update")
    public String update(@RequestBody PermissionVO permissionVO) {
        PermissionDTO permissionDTO = new PermissionDTO();
        BeanUtil.copyProperties(permissionVO, permissionDTO);
        Boolean result = permissionService.update(permissionDTO);
        return ResponseUtils.getResponse(result, ResponseUtils.ResultType.OK);
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @DeleteMapping("remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setId(id);
        PermissionDTO permissionDTO = new PermissionDTO();
        BeanUtil.copyProperties(permissionVO, permissionDTO);
        Boolean result = permissionService.remove(permissionDTO);
        return ResponseUtils.getResponse(result, ResponseUtils.ResultType.OK);
    }

    /**
     * 通过分页查询
     *
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
        pageDTO = permissionService.listByPage(pageDTO);

        pageVO = BeanUtil.copyProperties(pageDTO, PageVO.class);
        return ResponseUtils.getResponse(pageVO, ResponseUtils.ResultType.OK);
    }

    /**
     * 通过角色ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("getByRid/{id}")
    public String getByRid(@PathVariable("id") Long id) {
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setId(id);

        PermissionDTO permissionDTO = BeanUtil.copyProperties(permissionVO, PermissionDTO.class);

        List<PermissionDTO> permissionDTOList = permissionService.getByRid(permissionDTO);
        List<PermissionVO> permissionVOList = BeanUtil.copyListProperties(permissionDTOList, PermissionVO.class);
        return ResponseUtils.getResponse(permissionVOList, ResponseUtils.ResultType.OK);
    }

    /**
     * 查询整体列表
     *
     * @return
     */
    @GetMapping("list")
    public String listAll() {
        List<PermissionDTO> permissionDTOList = permissionService.listAll();
        List<PermissionVO> permissionVOList = BeanUtil.copyListProperties(permissionDTOList, PermissionVO.class);
        return ResponseUtils.getResponse(permissionVOList, ResponseUtils.ResultType.OK);
    }

    /**
     * 读取权限树列表
     */
    @GetMapping("listByTree")
    public String getTreeList() {
        List<PermissionDTO> treeList = permissionService.listByTree();
        List<PermissionVO> permissionVOList = BeanUtil.copyListProperties(treeList, PermissionVO::new);
        return ResponseUtils.getResponse(permissionVOList, ResponseUtils.ResultType.OK);
    }

    /**
     * 批量删除
     *
     * @param permissionVOList
     * @return
     */
    @DeleteMapping("removeBatch")
    public String removeBatch(@RequestBody List<PermissionVO> permissionVOList) {
        List<PermissionDTO> permissionDTOList = BeanUtil.copyListProperties(permissionVOList, PermissionDTO.class);
        Boolean result = permissionService.removeBatch(permissionDTOList);
        return ResponseUtils.getResponse(result, ResponseUtils.ResultType.OK);
    }

}

