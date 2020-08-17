package com.boss.msg.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.boss.msg.entity.dto.SmsDto;
import com.boss.msg.entity.vo.*;
import com.boss.msg.service.SendSmsService;
import com.boss.msg.service.SmsService;
import com.boss.msg.util.DozerUtils;
import com.boss.msg.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * @author zhangxiaohui
 */
@RestController
@RequestMapping("/sms")
@CrossOrigin
@Slf4j
public class SmsController {
    @Resource
    private SendSmsService sendsmsService;

    @Resource
    private SmsService smsService;

    /**
     * 短信发送接口
     *
     * @param smsVo 短信收件人，及内容是必须的
     * @return 发件成功与否
     */
    @PostMapping("/send")
    public String sendSms(@RequestBody SendSmsVo smsVo) throws ClientException, ExecutionException, InterruptedException {
        BillVo billVo = new BillVo(new Date(), "电子票据", "12345678", "1234567890", "a1b2c3", "测试单位", "zhangsan", new BigDecimal(500));
        smsVo.setContent(JSON.toJSONString(billVo));
        SmsDto smsDto = DozerUtils.map(smsVo, SmsDto.class);
        boolean b = sendsmsService.sendSms(smsDto).get();

        if (b) {
            return ResponseUtils.getResponse(
                    ResponseUtils.ResultType.OK.getCode(),
                    ResponseUtils.ResultType.OK.getMsg(),
                    "success");
        }
        return ResponseUtils.getResponse(
                ResponseUtils.ResultType.NOT_ACCEPTABLE.getCode(),
                ResponseUtils.ResultType.NOT_ACCEPTABLE.getMsg(), "fail");
    }

    /**
     * 根据用户手机号码和校验码查询票据信息
     *
     * @param tel        11位电话号码
     * @param verifyCode 6位校验码,不区分大小写，一律转为小写进行查询
     * @return 票据信息json格式
     */
    @GetMapping("/getBill")
    public String getBillByKey(String tel, String verifyCode) {
        log.info(tel);
        log.info(verifyCode);
        if (tel == null || tel.length() != 11) {
            // 电话号码格式有误
            return ResponseUtils.getResponse(
                    ResponseUtils.ResultType.METHOD_NOT_ALLOWED.getCode(),
                    ResponseUtils.ResultType.METHOD_NOT_ALLOWED.getMsg());
        }
        if (verifyCode == null || verifyCode.length() != 6) {
            // 校验码格式有误
            return ResponseUtils.getResponse(
                    ResponseUtils.ResultType.METHOD_NOT_ALLOWED.getCode(),
                    ResponseUtils.ResultType.METHOD_NOT_ALLOWED.getMsg());
        }
        String bill = smsService.getBillByKey(tel, verifyCode.toLowerCase());
        if (StringUtils.isNotBlank(bill)) {
            return ResponseUtils.getResponse(
                    ResponseUtils.ResultType.OK.getCode(),
                    ResponseUtils.ResultType.OK.getMsg(),
                    bill);
        }
        return ResponseUtils.getResponse(
                ResponseUtils.ResultType.NOT_FOUND.getCode(),
                ResponseUtils.ResultType.NOT_FOUND.getMsg());
    }


    /**
     * 分页查询邮件
     * 根据Id,isSent,mailTo字段查询匹配的邮件
     * page是当前页码，limit是每页大小
     *
     * @param smsVo 分页查询对象
     */
    @PostMapping("/list")
    public String listPage(@RequestBody SmsQueryVo smsQuery) {
        // 获取匹配记录数
        Long total = smsService.getTotal(smsQuery);
        // 查询匹配记录
        List<SmsDto> mails = smsService.listPage(smsQuery, smsQuery.getPage(), smsQuery.getLimit());
        // 封装结果集，携带页面参数
        PageResult pageResult = new PageResult(total, smsQuery.getLimit(), smsQuery.getPage(), mails);

        return ResponseUtils.getResponse(
                ResponseUtils.ResultType.OK.getCode(),
                ResponseUtils.ResultType.OK.getMsg(),
                pageResult);
    }

    /**
     * 短信更新功能
     * 实现未发件的邮件状态更新为已发件
     *
     * @param smsVo 分页查询对象
     */
    @PutMapping("/updateStatus")
    public String updateStatus(@RequestBody SmsVo smsVo) {
        SmsDto smsDto = DozerUtils.map(smsVo, SmsDto.class);

        // 修改isSent
        boolean b = smsService.updateStatus(smsDto);

        if (b) {
            return ResponseUtils.getResponse(
                    ResponseUtils.ResultType.OK.getCode(),
                    ResponseUtils.ResultType.OK.getMsg(),
                    "success");
        }

        return ResponseUtils.getResponse(
                ResponseUtils.ResultType.NOT_MODIFIED.getCode(),
                ResponseUtils.ResultType.NOT_MODIFIED.getMsg(),
                "fail");

    }
}
