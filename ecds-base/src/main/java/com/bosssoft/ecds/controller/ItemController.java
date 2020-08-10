package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.ItemVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.ItemService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import com.bosssoft.ecds.utils.ResponseUtils;
import io.swagger.annotations.ApiImplicitParam;
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
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 插入项目相关信息
     *
     * @param itemVO
     * @return
     */
    @ApiOperation(value = "添加项目")
    @PostMapping("/save")
    public String addItem(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = MyBeanUtil.myCopyProperties(itemVO, ItemDTO.class);
        boolean save = itemService.save(itemDTO);
        return ResponseUtils.getResponse(save, ResponseUtils.ResultType.CREATED);
    }

    /**
     * 修改项目信息
     *
     * @param itemVO
     * @return
     */
    @ApiOperation(value = "修改项目")
    @PostMapping("/update")
    public String updateItem(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = MyBeanUtil.myCopyProperties(itemVO, ItemDTO.class);
        boolean update = itemService.update(itemDTO);
        return ResponseUtils.getResponse(update, ResponseUtils.ResultType.OK);
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
    public String deleteItem(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = MyBeanUtil.myCopyProperties(itemVO, ItemDTO.class);
        boolean delete = itemService.delete(itemDTO);
        return ResponseUtils.getResponse(delete, ResponseUtils.ResultType.OK);
    }

    /**
     * 批量删除
     *
     * @param itemVOList
     * @return
     */
    @ApiOperation(value = "批量删除")
    @PostMapping("/batchdelete")
    public String batchDelete(@RequestBody List<ItemVO> itemVOList) {
        List<ItemDTO> itemDTOS = MyBeanUtil.copyListProperties(itemVOList, ItemDTO::new);
        boolean batchdelete = itemService.batchdelete(itemDTOS);
        return ResponseUtils.getResponse(batchdelete, ResponseUtils.ResultType.OK);
    }


    /**
     * 分页查询
     *
     * @param pageVO
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/listbypage")
    public String listByPage(@RequestBody PageVO pageVO) {
        PageDTO<ItemDTO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        PageVO pageVO1 = itemService.listByPage(pageDTO);
        if (pageVO1 != null) {
            return ResponseUtils.getResponse(pageVO1, ResponseUtils.ResultType.OK);
        } else {
            return ResponseUtils.getResponse(pageVO1, ResponseUtils.ResultType.INTERNAL_SERVER_ERROR);
        }
    }
}

