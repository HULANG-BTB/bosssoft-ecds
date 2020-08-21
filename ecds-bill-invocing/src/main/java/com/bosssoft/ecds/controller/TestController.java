package com.bosssoft.ecds.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.service.UneCbillService;
import com.bosssoft.ecds.service.serviceimp.UnePayBookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/test")
public class TestController {
    @Autowired
    private UneCbillService uneCbillService;

    @RequestMapping("/getList")
    public String getUneCBill() {
        String i = "1";
        UneCbill uneCbill = uneCbillService.getUneCBillById(i);
        return uneCbill.toString();
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(path = "/getPage", method = RequestMethod.GET)
    public IPage<UneCbill> getCbillPage(int currentPage, int pageSize) {
        Page<UneCbill> page = new Page<>(currentPage, pageSize);
        return uneCbillService.selectUnecBillPage(page);
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public UneCbill getUneCbillById(String billId) {
        return uneCbillService.getUneCBillById(billId);
    }
}
