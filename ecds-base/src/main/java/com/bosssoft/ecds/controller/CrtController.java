package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.CrtDTO;
import com.bosssoft.ecds.entity.dto.PagesDTO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.CrtVO;
import com.bosssoft.ecds.entity.vo.PagesVO;
import com.bosssoft.ecds.service.CrtService;
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
 * @since 2020-08-10
 */
@Slf4j
@RestController
@Api(value = "准购证管理接口")
@RequestMapping("/crt")
public class CrtController {

    @Autowired
    CrtService uabCrtService;

    /**
     *
     *
     * @description: 新增领购证。
     * @param {UabCrtVO} uabCrtVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增领购证")
    public QueryResponseResult save(@RequestBody CrtVO uabCrtVO){
        CrtDTO uabCrtDTO = new CrtDTO();
        MyBeanUtil.copyProperties(uabCrtVO,uabCrtDTO);
        uabCrtDTO = uabCrtService.save(uabCrtDTO);
        MyBeanUtil.copyProperties(uabCrtDTO,uabCrtVO);
        return new QueryResponseResult<>(CommonCode.SUCCESS,uabCrtVO);
    }

    /**
     *
     *
     * @description: 按领购证ID删除领购证。
     * @param {UabCrtVO} uabCrtVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/remove")
    @ApiOperation(value = "按领购证ID删除领购证")
    public QueryResponseResult remove(@RequestBody CrtVO uabCrtVO){
        CrtDTO uabCrtDTO = new CrtDTO();
        MyBeanUtil.copyProperties(uabCrtVO,uabCrtDTO);
        Boolean result = uabCrtService.remove(uabCrtDTO);
        MyBeanUtil.copyProperties(uabCrtDTO,uabCrtVO);
        return new QueryResponseResult<>(CommonCode.SUCCESS,result);
    }

    /**
     *
     *
     * @description: 用于修改领购证信息。
     * @param {UabCrtVO} uabCrtVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/update")
    @ApiOperation(value = "用于修改领购证信息")
    public QueryResponseResult update(@RequestBody CrtVO uabCrtVO){
        CrtDTO uabCrtDTO = new CrtDTO();
        MyBeanUtil.copyProperties(uabCrtVO,uabCrtDTO);
        Boolean result = uabCrtService.update(uabCrtDTO);
        MyBeanUtil.copyProperties(uabCrtDTO,uabCrtVO);
        return new QueryResponseResult<>(CommonCode.SUCCESS,result);
    }

    /**
     *
     *
     * @description: 根据领购证编码查询领购证。
     * @param {UabCrtVO} uabCrtVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/getByCrtCode")
    @ApiOperation(value = "根据领购证编码查询领购证")
    public QueryResponseResult getByCrtCode(@RequestBody CrtVO uabCrtVO){
        CrtDTO uabCrtDTO = new CrtDTO();
        MyBeanUtil.copyProperties(uabCrtVO,uabCrtDTO);
        uabCrtDTO = uabCrtService.getByCrtCode(uabCrtDTO);
        MyBeanUtil.copyProperties(uabCrtDTO,uabCrtVO);
        return new QueryResponseResult<>(CommonCode.SUCCESS,uabCrtVO);
    }

    /**
     *
     *
     * @description: 根据领购证id查询领购证。
     * @param {UabCrtVO} uabCrtVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/getById")
    @ApiOperation(value = "根据领购证id查询领购证")
    public QueryResponseResult getById(@RequestBody CrtVO uabCrtVO){
        CrtDTO uabCrtDTO = new CrtDTO();
        MyBeanUtil.copyProperties(uabCrtVO,uabCrtDTO);
        uabCrtDTO = uabCrtService.getById(uabCrtDTO);
        MyBeanUtil.copyProperties(uabCrtDTO,uabCrtVO);
        return new QueryResponseResult<>(CommonCode.SUCCESS,uabCrtVO);
    }

    /**
     *
     *
     * @description: 根据单位编码查询领购证。
     * @param {UabCrtVO} uabCrtVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/getByAgenCode")
    @ApiOperation(value = "根据单位编码查询领购证")
    public QueryResponseResult getByAgenCode(@RequestBody CrtVO uabCrtVO){
        CrtDTO uabCrtDTO = new CrtDTO();
        MyBeanUtil.copyProperties(uabCrtVO,uabCrtDTO);
        List<CrtDTO> uabCrtDTOList = uabCrtService.getByAgenCode(uabCrtDTO);
        MyBeanUtil.copyListProperties(uabCrtDTOList, CrtVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS,uabCrtDTOList);
    }

    /**
     *
     *
     * @description: 用于查看领购证列表。
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @GetMapping("/listAll")
    @ApiOperation(value = "用于查看领购证列表")
    public QueryResponseResult listAll(){
        List<CrtDTO> uabCrtDTOList = uabCrtService.listAll();
        List<CrtVO> uabCrtVOList = MyBeanUtil.copyListProperties(uabCrtDTOList, CrtVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS,uabCrtVOList);
    }

    /**
     * 通过分页查询
     *
     * @param pagesVO
     * @return
     */
    @PostMapping("/listByPage")
    @ApiOperation(value = "通过分页查询")
    public QueryResponseResult listByPage(@RequestBody PagesVO pagesVO) {
        PagesDTO pagesDTO = MyBeanUtil.copyProperties(pagesVO, PagesDTO.class);
        pagesDTO = uabCrtService.listByPage(pagesDTO);

        pagesVO = MyBeanUtil.copyProperties(pagesDTO, PagesVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS,pagesVO);
    }

    /**
     * 准购证审核分页查询
     *
     * @param pagesVO
     * @return
     */
    @PostMapping("/checkListByPage")
    @ApiOperation(value = "准购证审核分页查询")
    public QueryResponseResult checkListByPage(@RequestBody PagesVO pagesVO) {
        PagesDTO pagesDTO = MyBeanUtil.copyProperties(pagesVO, PagesDTO.class);
        pagesDTO = uabCrtService.checkListByPage(pagesDTO);

        pagesVO = MyBeanUtil.copyProperties(pagesDTO, PagesVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS,pagesVO);
    }

    /**
     * 批量删除领购证
     *
     * @param uabCrtVOList
     * @return
     */
    @PostMapping("/removeBatch")
    @ApiOperation(value = "批量删除领购证")
    public QueryResponseResult removeBatch(@RequestBody List<CrtVO> uabCrtVOList) {
        List<CrtDTO> uabCrtDTOList = MyBeanUtil.copyListProperties(uabCrtVOList, CrtDTO.class);
        Boolean result = uabCrtService.removeBatch(uabCrtDTOList);
        return new QueryResponseResult<>(CommonCode.SUCCESS,result);
    }

    /**
     * 批量审核领购证
     *
     * @param uabCrtVOList
     * @return
     */
    @PostMapping("checkBatch")
    @ApiOperation(value = "批量删除领购证")
    public QueryResponseResult checkBatch(@RequestBody List<CrtVO> uabCrtVOList) {
        List<CrtDTO> uabCrtDTOList = MyBeanUtil.copyListProperties(uabCrtVOList, CrtDTO.class);
        Boolean result = uabCrtService.checkBatch(uabCrtDTOList);
        return new QueryResponseResult<>(CommonCode.SUCCESS,result);
    }

}

