package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PlaceDTO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.PlaceVO;
import com.bosssoft.ecds.service.PlaceService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
@RestController
@RequestMapping("/place")
@Api(value = "开票点管理接口")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    /**
     * 插入项目相关信息
     *
     * @param placeVO 项目相关信息
     * @return
     */
    @ApiOperation(value = "添加项目")
    @PostMapping("/save")
    public ResponseResult addItem(@RequestBody PlaceVO placeVO) {
        PlaceDTO placeDTO = MyBeanUtil.myCopyProperties(placeVO, PlaceDTO.class);
        return placeService.save(placeDTO);
    }

    /**
     * 修改项目信息
     *
     * @param placeVO
     * @return
     */
    @ApiOperation(value = "修改项目")
    @PostMapping("/update")
    public ResponseResult updateItem(@RequestBody PlaceVO placeVO) {
        PlaceDTO placeDTO = MyBeanUtil.myCopyProperties(placeVO, PlaceDTO.class);
        return placeService.update(placeDTO);
    }

    /**
     * 主要用于批量审核,修改项目启用状态，输入需要修改
     *
     * @param placeVOList 需要修改审核的项目id
     * @return
     */
    @ApiOperation(value = "批量审核")
    @PostMapping("/batchVerify")
    public ResponseResult updateBatchVerifyItem(@RequestBody List<PlaceVO> placeVOList) {
        List<PlaceDTO> placeDTOS = MyBeanUtil.copyListProperties(placeVOList, PlaceDTO::new);
        return placeService.batchVerify(placeDTOS);
    }

    /**
     * 删除项目
     *
     * @param placeVO
     * @return
     */
    @ApiOperation(value = "删除项目")
    @ApiImplicitParam(name = "id", value = "项目id", dataType = "Long")
    @PostMapping("/delete")
    public ResponseResult deleteItem(@RequestBody PlaceVO placeVO) {
        PlaceDTO placeDTO = MyBeanUtil.myCopyProperties(placeVO, PlaceDTO.class);
        return placeService.delete(placeDTO);
    }

    /**
     * 批量删除
     *
     * @param placeVOList
     * @return
     */
    @ApiOperation(value = "批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult batchDelete(@RequestBody List<PlaceVO> placeVOList) {
        List<PlaceDTO> placeDTOS = MyBeanUtil.copyListProperties(placeVOList, PlaceDTO::new);
        return placeService.batchDelete(placeDTOS);
    }

    /**
     * 分页查询
     *
     * @param pageVO
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/listByPage")
    public QueryResponseResult<PageVO> listByPage(@RequestBody PageVO pageVO) {
        PageDTO<PlaceDTO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return placeService.listByPage(pageDTO);
    }

}

