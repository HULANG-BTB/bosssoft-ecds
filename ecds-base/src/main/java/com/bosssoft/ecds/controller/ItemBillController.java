package com.bosssoft.ecds.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.entity.vo.BillItemsInsertVO;
import com.bosssoft.ecds.entity.vo.BillItemsVO;
import com.bosssoft.ecds.entity.vo.ItemBillVO;
import com.bosssoft.ecds.entity.vo.SelectItemVO;
import com.bosssoft.ecds.service.FabItemBillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @ClassName :  ItemBillController
 * @Description : 项目票据种类管理
 * @Author : wuliming
 * @Date: 2020-08-10 13:56
 */
@RestController
@RequestMapping("/itembill")
@Api(value = "票据与项目关系管理", description = "票据与项目关系管理 ")
public class ItemBillController {
    @Autowired
    FabItemBillService fabItemBillService;

    @PostMapping("/insert")
    @ApiOperation(value = "添加票据项目关系", notes = "")
    public QueryResponseResult insertIntoItemBill(@RequestBody @Validated BillItemsInsertVO itemsInsertVO) {
        return new QueryResponseResult<Boolean>(CommonCode.SUCCESS, fabItemBillService.insertIntoItemBill(itemsInsertVO));
    }

    @PostMapping("/insertbatch")
    @ApiOperation(value = "批量添加票据项目关系", notes = "")
    public QueryResponseResult insertBatchItemBill(@RequestBody @Validated BillItemsVO billItemsVO) {
        return new QueryResponseResult<Boolean>(CommonCode.SUCCESS, fabItemBillService.insertBatchItemBill(billItemsVO));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除票据项目关系", notes = "")
    public QueryResponseResult deleteFromItemBill(@RequestBody ItemBillVO itemBillVO) {
        return new QueryResponseResult<Boolean>(CommonCode.SUCCESS, fabItemBillService.deleteFromItemBill(itemBillVO.getId()));
    }

    @PostMapping("/turnenabled")
    @ApiOperation(value = "更改启用关系状态", notes = "1 启用 0 停用")
    public QueryResponseResult turnEnabled(@RequestBody ItemBillVO itemBillVO) {
        return new QueryResponseResult<Boolean>(CommonCode.SUCCESS, fabItemBillService.turnEnabled(itemBillVO.getId()));
    }


    @PostMapping("/getitemwithbillcode")
    @ApiOperation(value = "获取与票据种类有关的项目 分页与模糊查询", notes = " ")
    public QueryResponseResult getItemWithBillCode(@RequestBody SelectItemVO selectItemVO) {
        return new QueryResponseResult<IPage>(CommonCode.SUCCESS, fabItemBillService.selectItemWithBillCode(selectItemVO));
    }

    @PostMapping("/getnocontactitem")
    @ApiOperation(value = "获取与票据种类无关的项目 分页与模糊查询", notes = " ")
    public QueryResponseResult getNoContactItem(@RequestBody @Validated SelectItemVO selectItemVO) {
        return new QueryResponseResult<IPage>(CommonCode.SUCCESS, fabItemBillService.selectNoContactItem(selectItemVO));
    }

    @PostMapping("/check")
    @ApiOperation(value = "查看票据种类与项目是否有关", notes = " ")
    public QueryResponseResult check(@RequestBody @Validated BillItemsInsertVO itemsInsertVO) {
        return new QueryResponseResult<Boolean>(CommonCode.SUCCESS, fabItemBillService.checkItemBill(itemsInsertVO));
    }

}
