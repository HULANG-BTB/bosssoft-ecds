package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.ItemVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.ItemService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/item")
@Api(value = "项目管理接口")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 插入项目相关信息
     *
     * @param itemVO 项目相关信息
     * @return
     */
    @ApiOperation(value = "添加项目")
    @PostMapping("/save")
    public ResponseResult addItem(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = MyBeanUtil.myCopyProperties(itemVO, ItemDTO.class);
        return itemService.save(itemDTO);
    }

    /**
     * 修改项目信息
     *
     * @param itemVO
     * @return
     */
    @ApiOperation(value = "修改项目")
    @PostMapping("/update")
    public ResponseResult updateItem(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = MyBeanUtil.myCopyProperties(itemVO, ItemDTO.class);
        return itemService.update(itemDTO);
    }

    /**
     * 主要用于批量审核,修改项目启用状态，输入需要修改
     *
     * @param itemVOList 需要修改审核的项目id
     * @return
     */
    @ApiOperation(value = "批量审核")
    @PostMapping("/batchVerify")
    public ResponseResult updateBatchVerifyItem(@RequestBody List<ItemVO> itemVOList) {
        List<ItemDTO> itemDTOS = MyBeanUtil.copyListProperties(itemVOList, ItemDTO::new);
        return itemService.batchVerify(itemDTOS);
    }

    /**
     * 删除项目
     *
     * @param itemVO
     * @return
     */
    @ApiOperation(value = "删除项目")
    @ApiImplicitParam(name = "id", value = "项目id", dataType = "Long")
    @PostMapping("/delete")
    public ResponseResult deleteItem(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = MyBeanUtil.myCopyProperties(itemVO, ItemDTO.class);
        return itemService.delete(itemDTO);
    }

    /**
     * 批量删除
     *
     * @param itemVOList
     * @return
     */
    @ApiOperation(value = "批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult batchDelete(@RequestBody List<ItemVO> itemVOList) {
        List<ItemDTO> itemDTOS = MyBeanUtil.copyListProperties(itemVOList, ItemDTO::new);
        return itemService.batchDelete(itemDTOS);
    }

    /**
     * 分页查询
     *
     * @param pageVO
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/listbypage")
    public QueryResponseResult<PageVO> listByPage(@RequestBody PageVO pageVO) {
        PageDTO<ItemDTO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return itemService.listByPage(pageDTO);
    }
}

