package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.po.FinanBillPo;
import com.bosssoft.ecds.entity.vo.StockOutVo;
import com.bosssoft.ecds.service.FinanBillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 *      * 接收参数RequestBody处理content-type不是默认的application/x-www-form-urlcoded编码的内容，
 *      * 比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。
 *
 * @author misheep
 * @since 2020-08-11
 */
@Slf4j
@RestController
@RequestMapping("/finan-bill")
public class FinanBillController {

    @Autowired
    private FinanBillService finanBillService;
    
    private static final String SUCCESS = "success";

    @RequestMapping("/test1")
    public String test1(){
        //JSONObject.toJSONString(  (  Hashmap  )  myCart.entrySet().toArray());
        List<FinanBillPo> finanBillPoS = finanBillService.queryByYear("2020", true);
        log.info("finanlist.size: {}",finanBillPoS.size());
        return SUCCESS;
    }
    @RequestMapping("/test2")
    public String test2(){
        List<FinanBillPo> finanBillPoS = finanBillService.queryBills(
                "00000120", "0000000001", "0000000006", true);
        log.info("finanlist.size: {}",finanBillPoS.size());
//        finanBillService.
        return SUCCESS;
    }

    /**
     * 通过仓库id获得当前时间该仓库全部可用的票据
     *
     * @param warehouseId 仓库id
     * @return 结果
     */
    @RequestMapping("/queryByWarehouseId")
    public String queryByWarehouseId(String warehouseId) {

        return SUCCESS;
    }



}

