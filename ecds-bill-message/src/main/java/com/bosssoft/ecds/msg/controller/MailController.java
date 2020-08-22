package com.bosssoft.ecds.msg.controller;

import com.bosssoft.ecds.msg.entity.dto.MailDto;
import com.bosssoft.ecds.msg.entity.vo.*;
import com.bosssoft.ecds.msg.service.MailService;
import com.bosssoft.ecds.msg.service.SendMailService;
import com.bosssoft.ecds.msg.util.DozerUtils;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangxiaohui
 */
@Api(tags = "邮件发送及邮件记录")
@RestController
@RequestMapping("/mail")
@Slf4j
//@CrossOrigin
public class MailController extends BaseController {

    @Resource
    private SendMailService sendMailService;

    @Resource
    private MailService mailService;

    /**
     * 邮件发送接口
     *
     * @param sendMailVo 邮件发送对象包括：邮件收件人、主题、正文内容（messageDto的json格式）
     * @return 发送结果，成功与否
     * @throws ExecutionException   多线程异常
     * @throws InterruptedException 多线程异常
     */
    @ApiOperation("发送邮件")
    @PostMapping("/send")
    public ResponseResult sendMail(@RequestBody SendMailVo sendMailVo) throws ExecutionException, InterruptedException {
        MailDto mailDto = DozerUtils.map(sendMailVo, MailDto.class);
        MailDto sentMail = sendMailService.sendMail(mailDto).get();
        mailService.saveAutoSentMail(sentMail);
        boolean isSent = sentMail.getIsSent();
        return getRes(isSent);
    }

    /**
     * 分页查询邮件
     * 根据Id,isSent,mailTo字段查询匹配的邮件
     * page是当前页码，limit是每页大小
     * @param mailQuery 分页查询对象
     * @return 查询对象
     */
    @ApiOperation("查询邮件发件记录")
    @PostMapping("/list")
    public QueryResponseResult<PageResult> listPage( MailQueryVo mailQuery) {
        // 获取匹配记录数
        Long total = mailService.getTotal(mailQuery);
        // 查询匹配记录
        List<MailDto> mails = mailService.listPage(mailQuery, mailQuery.getPage(), mailQuery.getLimit());
        // 封装结果集，携带页面参数
        PageResult pageResult = new PageResult(total, mailQuery.getLimit(), mailQuery.getPage(), mails);
        return new QueryResponseResult<>(CommonCode.SUCCESS, pageResult);
    }

    /**
     * 邮件更新功能
     * 实现未发件的邮件状态更新为已发件
     *
     * @param mailVo 分页查询对象
     * @return ResponseResult
     */
    @ApiOperation("更新邮件发件状态为已发件")
    @PutMapping("/updateStatus")
    public ResponseResult updateStatus(@RequestBody MailVo mailVo) {
        MailDto mailDto = DozerUtils.map(mailVo, MailDto.class);
        // 修改isSent
        boolean b = mailService.updateStatus(mailDto);
        return getRes(b);
    }

    /**
     * 邮件添加功能
     *
     * @param mailVo 邮件详情
     */
    @ApiOperation("添加邮件发件记录")
    @PostMapping("/addMail")
    public ResponseResult add(@RequestBody MailVo mailVo) {
        MailDto mailDto = DozerUtils.map(mailVo, MailDto.class);
        // 修改isSent
        boolean b = mailService.saveMailManually(mailDto);
        return getRes(b);

    }

    /**
     * 邮件删除功能
     *
     * @param id 邮件id
     * @return ResponseResult
     */
    @ApiOperation("根据Id删除邮件发件记录")
    @DeleteMapping("/deleteMail")
    public ResponseResult delMail(String id) {
        boolean b = mailService.removeById(id);
        return getRes(b);
    }

    /**
     * 邮件批量删除功能
     *
     * @param mails 邮件集合
     * @return ResponseResult
     */
    @ApiOperation("批量删除邮件发件记录")
    @DeleteMapping("/deleteMailBatch")
    public ResponseResult delMailBatch(@RequestBody List<MailVo> mails) {
        List<Long> ids = Lists.newArrayList();
        mails.forEach(mailVo -> ids.add(mailVo.getId()));
        boolean b = mailService.removeByIds(ids);
        return getRes(b);
    }


}
