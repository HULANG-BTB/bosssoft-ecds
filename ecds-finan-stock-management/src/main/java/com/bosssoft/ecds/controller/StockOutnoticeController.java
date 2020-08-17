package com.bosssoft.ecds.controller;


import ch.qos.logback.core.pattern.ConverterUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.bosssoft.ecds.entity.vo.StockOutVo;
import com.bosssoft.ecds.service.StockOutnoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
@RestController
@RequestMapping("/stock-outnotice-po")
public class StockOutnoticeController {

    @Autowired
    private StockOutnoticeService stockOutnoticeService;

    private static final String SUCCESS = "success";

    /**
     * 展示所有已保存的出库请求
     *
     * @return 已保存出库请求的list
     */
    @RequestMapping("/showAllSaved")
    public String showAllSaved() {
        /**
         *  1.获取 ActionForm 表单数据
         *          UserActionForm uForm = (UserActionForm) form;
         *          2.构造一个User对象
         *          User user = new User();
         *          3.赋值
         *          BeanUtils.copyProperties(user, uForm);
         */
        List<StockOutVo> stockOutVos = new ArrayList<>();
                stockOutnoticeService.queryByChangeState(1);
//        Convert.toList()
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

