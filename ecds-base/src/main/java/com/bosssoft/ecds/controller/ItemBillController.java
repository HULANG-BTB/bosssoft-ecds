package com.bosssoft.ecds.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResultCode;
import com.bosssoft.ecds.service.FabItemBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @ClassName :  ItemBillController
 * @Description : 项目票据种类管理
 * @Author : wuliming
 * @Date: 2020-08-10 13:56
 */
@RestController
@RequestMapping("/itembill")
public class ItemBillController {
    @Autowired
    FabItemBillService fabItemBillService;

    @PostMapping("/getitembybillcode")
    public QueryResponseResult getItemByBillCode(@PathParam("billCode") String billCode, @PathParam("pageNum") Integer pageNum, @PathParam("pageSize") Integer pageSize) {
        return new QueryResponseResult<IPage>(CommonCode.SUCCESS, fabItemBillService.selectItemByBillCode(pageNum, pageSize, billCode));
    }

    @PostMapping("/insert")
    public QueryResponseResult insertIntoItemBill(@PathParam("billCode") String billCode, @PathParam("itemId") String itemId) {
        return new QueryResponseResult<Boolean>(CommonCode.SUCCESS, fabItemBillService.insertIntoItemBill(billCode, itemId));
    }

    @PostMapping("/delete")
    public QueryResponseResult deleteFromItemBill(@PathParam("id") Long id) {
        return new QueryResponseResult<Boolean>(CommonCode.SUCCESS, fabItemBillService.deleteFromItemBill(id));
    }

    @PostMapping("/turnenabled")
    public QueryResponseResult turnEnabled(@PathParam("id") Long id) {
        return new QueryResponseResult<Boolean>(CommonCode.SUCCESS, fabItemBillService.turnEnabled(id));
    }

    @PostMapping("/check")
    public QueryResponseResult check(@PathParam("billCode") String billCode, @PathParam("itemId") String itemId) {
        return new QueryResponseResult<Boolean>(CommonCode.SUCCESS, fabItemBillService.checkItemBill(billCode, itemId));
    }


}
