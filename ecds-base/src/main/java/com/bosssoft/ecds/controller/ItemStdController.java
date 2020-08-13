package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.ItemStdDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.ItemStdVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemVO;
import com.bosssoft.ecds.service.ItemStdService;
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
 * 前端控制器
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
@RestController
@RequestMapping("/itemstd")
@Api(value = "项目标准管理接口")
public class ItemStdController {

    @Autowired
    private ItemStdService itemStdService;

    /**
     * 插入项目相关信息
     *
     * @param itemStdVO
     * @return
     */
    @ApiOperation(value = "添加项目标准")
    @PostMapping("/save")
    public ResponseResult addItem(@RequestBody ItemStdVO itemStdVO) {
        ItemStdDTO itemStdDTO = MyBeanUtil.myCopyProperties(itemStdVO, ItemStdDTO.class);
        return itemStdService.save(itemStdDTO);
    }

    /**
     * 修改项目信息
     *
     * @param itemStdVO
     * @return
     */
    @ApiOperation(value = "修改项目标准")
    @PostMapping("/update")
    public ResponseResult updateItem(@RequestBody ItemStdVO itemStdVO) {
        ItemStdDTO itemStdDTO = MyBeanUtil.myCopyProperties(itemStdVO, ItemStdDTO.class);
        return itemStdService.update(itemStdDTO);
    }

    /**
     * 删除项目
     *
     * @param itemStdVO
     * @return
     */
    @ApiOperation(value = "删除项目标准")
    @ApiImplicitParam(name = "id", value = "项目id", dataType = "Long")
    @PostMapping("/delete")
    public ResponseResult deleteItem(@RequestBody ItemStdVO itemStdVO) {
        ItemStdDTO itemStdDTO = MyBeanUtil.myCopyProperties(itemStdVO, ItemStdDTO.class);
        return itemStdService.delete(itemStdDTO);
    }

    /**
     * 批量删除
     *
     * @param itemVOList
     * @return
     */
    @ApiOperation(value = "批量删除项目标准")
    @PostMapping("/batchDelete")
    public ResponseResult batchDelete(@RequestBody List<ItemStdVO> itemVOList) {
        List<ItemStdDTO> itemStdDTOList = MyBeanUtil.copyListProperties(itemVOList, ItemStdDTO::new);
        return itemStdService.batchDelete(itemStdDTOList);
    }

    /**
     * 主要用于批量审核,修改项目标准启用状态，输入需要修改
     *
     * @param itemStdVOList 需要修改审核的项目id
     * @return
     */
    @ApiOperation(value = "批量审核")
    @PostMapping("/batchVerify")
    public ResponseResult updateBatchVerifyItem(@RequestBody List<ItemStdVO> itemStdVOList) {
        List<ItemStdDTO> itemStdDTOList = MyBeanUtil.copyListProperties(itemStdVOList, ItemStdDTO::new);
        return itemStdService.batchVerify(itemStdDTOList);
    }

    /**
     * 分页查询
     *
     * @param pageVO
     * @return
     */
    @ApiOperation(value = "分页查询项目标准")
    @PostMapping("/listByPage")
    public QueryResponseResult<PageVO> listByPage(@RequestBody PageVO pageVO) {
        PageDTO<ItemStdDTO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return itemStdService.listByPage(pageDTO);
    }

    /**
     * 通过项目编码查询项目标准
     *
     * @param itemStdVO
     * @return
     */
    @ApiOperation(value = "通过项目编码查询项目标准")
    @PostMapping("/getItemStd")
    public QueryResponseResult<ItemStdVO> listByPage(@RequestBody ItemStdVO itemStdVO) {
        ItemStdDTO itemStdDTO = MyBeanUtil.copyProperties(itemStdVO, ItemStdDTO.class);
        return itemStdService.getByItemCode(itemStdDTO);
    }
}

