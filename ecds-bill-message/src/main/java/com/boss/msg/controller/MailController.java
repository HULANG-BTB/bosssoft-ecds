package com.boss.msg.controller;

import com.alibaba.fastjson.JSON;
import com.boss.msg.entity.dto.MailDto;
import com.boss.msg.entity.vo.*;
import com.boss.msg.service.MailService;
import com.boss.msg.service.SendMailService;
import com.boss.msg.util.DozerUtils;
import com.boss.msg.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangxiaohui
 * todo 统一异常处理
 */
@RestController
@RequestMapping("/mail")
@Slf4j
@CrossOrigin
public class MailController {

    @Resource
    private SendMailService sendMailService;

    @Resource
    private MailService mailService;

    @PostMapping("/send")
    public String sendMail(@RequestBody SendMailVo mailVo) throws ExecutionException, InterruptedException {
        mailVo.setSubject("电子票据开票通知");
        BillVo billVo = new BillVo(new Date(), "电子票据", "12345678", "1234567890", "a1b2c3", "测试单位", "zhangsan", new BigDecimal(500));
        mailVo.setContent(JSON.toJSONString(billVo));
        /*File file = new File("D://编程规范.pdf");
        ArrayList<File> files = Lists.newArrayList(file);
        mailVo.setFiles(files);*/
        MailDto mailDto = DozerUtils.map(mailVo, MailDto.class);
        Boolean isSent = sendMailService.sendMail(mailDto).get().getIsSent();

        return ResponseUtils.getResponse(
                ResponseUtils.ResultType.OK.getCode(),
                ResponseUtils.ResultType.OK.getMsg(),isSent);
    }

    /**
     * 分页查询邮件
     * 根据Id,isSent,mailTo字段查询匹配的邮件
     * page是当前页码，limit是每页大小
     *
     * @param mailQuery 分页查询对象
     */
    @PostMapping("/list")
    public String listPage(@RequestBody MailQueryVo mailQuery) {
        log.info(mailQuery.toString());
        // 获取匹配记录数
        Long total = mailService.getTotal(mailQuery);
        // 查询匹配记录
        List<MailDto> mails = mailService.listPage(mailQuery, mailQuery.getPage(), mailQuery.getLimit());
        // 封装结果集，携带页面参数
        PageResult pageResult = new PageResult(total, mailQuery.getLimit(), mailQuery.getPage(), mails);

        return ResponseUtils.getResponse(
                ResponseUtils.ResultType.OK.getCode(),
                ResponseUtils.ResultType.OK.getMsg(),
                pageResult);
    }

    /**
     * 邮件更新功能
     * 实现未发件的邮件状态更新为已发件
     *
     * @param mailVo 分页查询对象
     */
    @PutMapping("/updateStatus")
    public String updateStatus(@RequestBody MailVo mailVo) {
        MailDto mailDto = DozerUtils.map(mailVo, MailDto.class);
        // 修改isSent
        boolean b = mailService.updateStatus(mailDto);

        if (b) {
            return ResponseUtils.getResponse(
                    ResponseUtils.ResultType.OK.getCode(),
                    ResponseUtils.ResultType.OK.getMsg(),
                    b);
        }

        return ResponseUtils.getResponse(
                ResponseUtils.ResultType.NOT_MODIFIED.getCode(),
                ResponseUtils.ResultType.NOT_MODIFIED.getMsg(),
                false);

    }
}
