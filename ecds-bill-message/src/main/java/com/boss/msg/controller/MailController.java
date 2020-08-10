package com.boss.msg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boss.msg.entity.dto.MailDto;
import com.boss.msg.entity.po.MailPo;
import com.boss.msg.entity.vo.MailVo;
import com.boss.msg.service.MailService;
import com.boss.msg.service.SendMailService;
import com.boss.msg.util.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangxiaohui
 */
@RestController
@RequestMapping("/mail")
@Slf4j
public class MailController {
    @Resource
    private SendMailService sendMailService;
    @Resource
    private MailService mailService;

    @RequestMapping("/send")
    public boolean mailTest(MailVo mailVo) throws ExecutionException, InterruptedException {
        mailVo = new MailVo();
        mailVo.setMailTo("386455317@qq.com");
        mailVo.setSubject("subject");
        mailVo.setContent("{\"code\":\"123\",\"msg\":\"231\"}");
        /*File file = new File("D://编程规范.pdf");
        ArrayList<File> files = Lists.newArrayList(file);
        mailVo.setFiles(files);*/
        return sendMailService.sendMail(DozerUtils.map(mailVo, MailDto.class)).get().getIsSent();
    }

    /**
     * 分页查询邮件
     * 根据Id,isSent,mailTo字段查询匹配的邮件
     * current，size字段是必须的，否则查询不到数据
     * current是当前页码，size是每页大小
     * @param mailVo
     */
    @RequestMapping("/list")
    public void listPage(MailVo mailVo) {
        List<MailDto> mailDtos = mailService.listPage(DozerUtils.map(mailVo, MailDto.class));
        log.info(mailDtos.toString());
        log.info(mailDtos.toString());
    }
}
