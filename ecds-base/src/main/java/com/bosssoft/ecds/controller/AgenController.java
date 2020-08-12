package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.AgenDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.AgenVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.AgenService;
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
@Api(value = "单位管理接口")
@RequestMapping("/agen")
public class AgenController {

    @Autowired
    AgenService fabAgenService;

    /**
     *
     *
     * @description: 新增单位。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "新增单位")
    @PostMapping("/save")
    public String save(@RequestBody AgenVO fabAgenVO){
        AgenDTO fabAgenDTO = new AgenDTO();
        MyBeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        fabAgenDTO = fabAgenService.save(fabAgenDTO);
        MyBeanUtil.copyProperties(fabAgenDTO,fabAgenVO);
        return ResponseUtils.getResponse(fabAgenVO,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 按单位编码删除单位。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "按单位编码删除单位")
    @PostMapping("/remove")
    public String remove(@RequestBody AgenVO fabAgenVO){
        AgenDTO fabAgenDTO = new AgenDTO();
        MyBeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        Boolean result = fabAgenService.remove(fabAgenDTO);
        MyBeanUtil.copyProperties(fabAgenDTO,fabAgenVO);
        return ResponseUtils.getResponse(result,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 用于修改单位信息。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "用于修改单位信息")
    @PostMapping("/update")
    public String update(@RequestBody AgenVO fabAgenVO){
        AgenDTO fabAgenDTO = new AgenDTO();
        MyBeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        Boolean result = fabAgenService.update(fabAgenDTO);
        MyBeanUtil.copyProperties(fabAgenDTO,fabAgenVO);
        return ResponseUtils.getResponse(result,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 根据单位编码查询单位。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "根据单位编码查询单位")
    @PostMapping("/getByAgenCode")
    public String getByAgenCode(@RequestBody AgenVO fabAgenVO){
        AgenDTO fabAgenDTO = new AgenDTO();
        MyBeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        fabAgenDTO = fabAgenService.getByAgenCode(fabAgenDTO);
        MyBeanUtil.copyProperties(fabAgenDTO,fabAgenVO);
        return ResponseUtils.getResponse(fabAgenVO,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 根据单位名查询单位。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "根据单位名查询单位")
    @PostMapping("/getByAgenName")
    public String getByAgenName(@RequestBody AgenVO fabAgenVO){
        AgenDTO fabAgenDTO = new AgenDTO();
        MyBeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        fabAgenDTO = fabAgenService.getByAgenName(fabAgenDTO);
        MyBeanUtil.copyProperties(fabAgenDTO,fabAgenVO);
        return ResponseUtils.getResponse(fabAgenVO,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 用于查看单位列表。
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "用于查看单位列表")
    @GetMapping("/listAll")
    public String listAll(){
        List<AgenDTO> fabAgenDTOList = fabAgenService.listAll();
        List<AgenVO> fabAgenVOList = MyBeanUtil.copyListProperties(fabAgenDTOList, AgenVO.class);
        return ResponseUtils.getResponse(fabAgenVOList,ResponseUtils.ResultType.OK);
    }

    /**
     * 通过分页查询
     *
     * @param pageVO
     * @return
     */
    @ApiOperation(value = "通过分页查询")
    @PostMapping("listByPage")
    public String listByPage(@RequestBody PageVO pageVO) {
        PageDTO pageDTO = MyBeanUtil.copyProperties(pageVO, PageDTO.class);
        pageDTO = fabAgenService.listByPage(pageDTO);

        pageVO = MyBeanUtil.copyProperties(pageDTO, PageVO.class);
        return ResponseUtils.getResponse(pageVO, ResponseUtils.ResultType.OK);
    }

    /**
     * 批量删除单位
     *
     * @param fabAgenVOList
     * @return
     */
    @ApiOperation(value = "批量删除单位")
    @PostMapping("removeBatch")
    public String removeBatch(@RequestBody List<AgenVO> fabAgenVOList) {
        List<AgenDTO> fabAgenDTOList = MyBeanUtil.copyListProperties(fabAgenVOList, AgenDTO.class);
        Boolean result = fabAgenService.removeBatch(fabAgenDTOList);
        return ResponseUtils.getResponse(result, ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 根据部门编码查询单位。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @ApiOperation(value = "根据部门编码查询单位")
    @PostMapping("/getByDeptCode")
    public String getByDeptCode(@RequestBody AgenVO fabAgenVO){
        AgenDTO fabAgenDTO = new AgenDTO();
        MyBeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        List<AgenDTO> fabAgenDTOList = fabAgenService.getByDeptCode(fabAgenDTO);
        List<AgenVO> fabAgenVOList= MyBeanUtil.copyListProperties(fabAgenDTOList, AgenVO.class);
        return ResponseUtils.getResponse(fabAgenVOList,ResponseUtils.ResultType.OK);
    }
}

