package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.placedto.PlaceAllDTO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.PlaceVO;
import com.bosssoft.ecds.service.PlaceService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
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
     * 插入单位开票点相关信息
     *
     * @param placeVO 单位开票点相关信息
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "添加开票点", notes = "单位开票点相关信息")
    @PostMapping("/save")
    public ResponseResult save(@RequestBody PlaceVO placeVO) {
        PlaceAllDTO placeDTO = MyBeanUtil.myCopyProperties(placeVO, PlaceAllDTO.class);
        return placeService.save(placeDTO);
    }

    /**
     * 修改开票点信息
     *
     * @param placeVO 修改后的开票点信息
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "修改开票点")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody PlaceVO placeVO) {
        PlaceAllDTO placeDTO = MyBeanUtil.myCopyProperties(placeVO, PlaceAllDTO.class);
        return placeService.update(placeDTO);
    }

    /**
     * 主要用于批量审核,修改开票点启用状态，输入需要修改
     *
     * @param placeVOList 需要修改审核的开票点id
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "批量审核")
    @PostMapping("/batchVerify")
    public ResponseResult batchVerify(@RequestBody List<PlaceVO> placeVOList) {
        List<PlaceAllDTO> placeDTOS = MyBeanUtil.copyListProperties(placeVOList, PlaceAllDTO::new);
        return placeService.batchVerify(placeDTOS);
    }

    /**
     * 删除单个开票点
     *
     * @param placeVO 需要删除的开票点id
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "删除单个开票点", notes = "需要删除的开票点id")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody PlaceVO placeVO) {
        PlaceAllDTO placeDTO = MyBeanUtil.myCopyProperties(placeVO, PlaceAllDTO.class);
        return placeService.delete(placeDTO);
    }

    /**
     * 批量删除开票点
     *
     * @param placeVOList 需要删除的开票点idList
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "批量删除", notes = "需要删除的开票点idList")
    @PostMapping("/batchDelete")
    public ResponseResult batchDelete(@RequestBody List<PlaceVO> placeVOList) {
        List<PlaceAllDTO> placeDTOS = MyBeanUtil.copyListProperties(placeVOList, PlaceAllDTO::new);
        return placeService.batchDelete(placeDTOS);
    }

    /**
     * 分页查询
     *
     * @param pageVO 输入分页信息,limit、page、keyword、isenable
     *               keyword为空时普通查询，keyword不为空时模糊查询
     * @return limit、page、total、items
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/listByPage")
    public QueryResponseResult<PageVO> listByPage(@RequestBody PageVO pageVO) {
        PageDTO<PlaceAllDTO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return placeService.listByPage(pageDTO);
    }

}

