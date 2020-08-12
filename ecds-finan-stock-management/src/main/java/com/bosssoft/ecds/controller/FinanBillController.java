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
     * 展示所有已保存的出库请求
     *
     * @return 已保存出库请求的list
     */
    @RequestMapping("/showAllSaved")
    public String showAllSaved() {

        return SUCCESS;
    }


    /**
     * 新增出库请求
     * 用户在前端点击“新增”，
     * 后台返回给用户一个单号。
     * 用户同时要做的还包括：获取全部仓库，获取全部可用票据。
     *
     * @return 出库通知单号
     */
    @RequestMapping("/add")
    public String add(){

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

    /**
     * 保存
     * 前端传入要保存的stockOutVo
     * 后端写入保存数据。
     * 在此过程中，后台会进行一次自动审核，若数据没问题，则允许保存。
     *
     * @param stockOutVo 存入的出库vo
     * @return 结果
     */
    @RequestMapping("/save")
    public String save(StockOutVo stockOutVo) {

        return SUCCESS;
    }

    /**
     * 提交审核
     * 用户将保存的出库请求提交到审核人处。
     *
     * @return 提交是否成功
     */
    @RequestMapping("/sentToCheck")
    public String sentToCheck() {

        return SUCCESS;
    }

    /**
     * 展示全部待审核的出库请求
     *
     * @return 结果
     */
    @RequestMapping("/showAllChecking")
    public String showAllChecking() {

        return SUCCESS;
    }

    /**
     * 人工审核
     * 人工进行判断出库请求是否通过审核。
     *
     * @param stockOutVo 出库请求vo
     * @return 是否通过
     */
    @RequestMapping("check")
    public String check(StockOutVo stockOutVo) {

        return SUCCESS;
    }

    /**
     * 展示全部已审核的出库请求
     * 包括通过审核的（绿）以及审核退回的出库请求
     *
     * @return 已审核的出库请求
     */
    @RequestMapping("/showAllChecked")
    public String showAllChecked() {

        return SUCCESS;
    }


    /**
     * 出库，也许没用？
     *
     * @return 结果
     */
    @RequestMapping("/stockOut")
    public String stockOut() {

        return SUCCESS;
    }


}

