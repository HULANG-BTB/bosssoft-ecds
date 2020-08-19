package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.DeptDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PagesDTO;
import com.bosssoft.ecds.entity.vo.DeptVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.PagesVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.DeptService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import com.bosssoft.ecds.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vihenne
 * @since 2020-08-09
 */
@Slf4j
@RestController
@Api(value = "部门管理接口")
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptService fabDeptService;

    /**
     *
     *
     * @description: 新增单位。
     * @param {FabDeptVO} fabDeptVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "新增单位")
    @PostMapping("/save")
    public QueryResponseResult save(@RequestBody DeptVO fabDeptVO){
        DeptDTO fabDeptDTO = new DeptDTO();
        MyBeanUtil.copyProperties(fabDeptVO,fabDeptDTO);
        fabDeptDTO = fabDeptService.save(fabDeptDTO);
        MyBeanUtil.copyProperties(fabDeptDTO,fabDeptVO);
        return new QueryResponseResult<>(CommonCode.SUCCESS,fabDeptVO);
    }

    /**
     *
     *
     * @description: 按部门编码删除部门。
     * @param {FabDeptVO} fabDeptVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "按部门编码删除部门")
    @PostMapping("/remove")
    public QueryResponseResult remove(@RequestBody DeptVO fabDeptVO){
        DeptDTO fabDeptDTO = new DeptDTO();
        MyBeanUtil.copyProperties(fabDeptVO,fabDeptDTO);
        Boolean result = fabDeptService.remove(fabDeptDTO);
        MyBeanUtil.copyProperties(fabDeptDTO,fabDeptVO);
        return new QueryResponseResult<>(CommonCode.SUCCESS,result);
    }

    /**
     *
     *
     * @description: 用于修改部门信息。
     * @param {FabDeptVO} fabDeptVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "用于修改部门信息")
    @PostMapping("/update")
    public QueryResponseResult update(@RequestBody DeptVO fabDeptVO){
        DeptDTO fabDeptDTO = new DeptDTO();
        MyBeanUtil.copyProperties(fabDeptVO,fabDeptDTO);
        Boolean result = fabDeptService.update(fabDeptDTO);
        MyBeanUtil.copyProperties(fabDeptDTO,fabDeptVO);
        return new QueryResponseResult<>(CommonCode.SUCCESS,result);
    }

    /**
     *
     *
     * @description: 根据部门编码查询部门。
     * @param {FabDeptVO} fabDeptVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "根据部门编码查询部门")
    @PostMapping("/getByDeptCode")
    public QueryResponseResult getByDeptCode(@RequestBody DeptVO fabDeptVO){
        DeptDTO fabDeptDTO = new DeptDTO();
        MyBeanUtil.copyProperties(fabDeptVO,fabDeptDTO);
        fabDeptDTO = fabDeptService.getByDeptCode(fabDeptDTO);
        MyBeanUtil.copyProperties(fabDeptDTO,fabDeptVO);
        return new QueryResponseResult<>(CommonCode.SUCCESS,fabDeptVO);
    }

    /**
     *
     *
     * @description: 根据部门名查询部门。
     * @param {FabDeptVO} fabDeptVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "根据部门名查询部门")
    @PostMapping("/getByDeptName")
    public QueryResponseResult getByDeptName(@RequestBody DeptVO fabDeptVO){
        DeptDTO fabDeptDTO = new DeptDTO();
        MyBeanUtil.copyProperties(fabDeptVO,fabDeptDTO);
        fabDeptDTO = fabDeptService.getByDeptName(fabDeptDTO);
        MyBeanUtil.copyProperties(fabDeptDTO,fabDeptVO);
        return new QueryResponseResult<>(CommonCode.SUCCESS,fabDeptVO);
    }

    /**
     *
     *
     * @description: 用于查看部门列表。
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "用于查看部门列表")
    @GetMapping("/listAll")
    public QueryResponseResult listAll(){
        List<DeptDTO> fabDeptDTOList = fabDeptService.listAll();
        List<DeptVO> fabDeptVOList = MyBeanUtil.copyListProperties(fabDeptDTOList, DeptVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS,fabDeptVOList);
    }

    /**
     * 通过分页查询
     *
     * @param pagesVO
     * @return
     */
    @ApiOperation(value = "通过分页查询")
    @PostMapping("/listByPage")
    public QueryResponseResult listByPage(@RequestBody PagesVO pagesVO) {
        PagesDTO pagesDTO = MyBeanUtil.copyProperties(pagesVO, PagesDTO.class);
        pagesDTO = fabDeptService.listByPage(pagesDTO);

        pagesVO = MyBeanUtil.copyProperties(pagesDTO, PagesVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS,pagesVO);
    }

    /**
     * 批量删除
     *
     * @param fabDeptVOList
     * @return
     */
    @ApiOperation(value = "批量删除")
    @PostMapping("/removeBatch")
    public QueryResponseResult removeBatch(@RequestBody List<DeptVO> fabDeptVOList) {
        List<DeptDTO> fabDeptDTOList = MyBeanUtil.copyListProperties(fabDeptVOList, DeptDTO.class);
        Boolean result = fabDeptService.removeBatch(fabDeptDTOList);
        return new QueryResponseResult<>(CommonCode.SUCCESS,result);
    }
}

