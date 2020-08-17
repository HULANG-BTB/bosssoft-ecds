package com.bosssoft.ecds.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.utils.StringUtils;
import com.bosssoft.ecds.msg.entity.po.MailPo;
import com.bosssoft.ecds.msg.exception.MsgException;
import com.bosssoft.ecds.msg.mapper.MailMapper;
import com.bosssoft.ecds.msg.entity.dto.MailDto;
import com.bosssoft.ecds.msg.service.SendMailService;
import com.bosssoft.ecds.msg.util.DozerUtils;
import com.bosssoft.ecds.msg.util.SnowflakeUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author zhangxiaohui
 */
@Service
@Slf4j
public class SendMailServiceImpl implements SendMailService {

    @Resource
    private JavaMailSenderImpl mailSender;

    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Resource
    private MailMapper mailMapper;

    private static final String SPLIT_SYMBOL = ",";

    /**
     * 异步发送邮件实现
     *
     * @param mailDto 发送邮件所需信息
     */
    @Async
    @Override
    public Future<MailDto> sendMail(MailDto mailDto) {
        try {
            //1.检测邮件是否合法
            checkMail(mailDto);
            //2.发送邮件
            sendMimeMail(mailDto);
            return new AsyncResult<>(mailDto);
        } catch (Exception e) {
            mailDto.setIsSent(false);
            mailDto.setError(e.getMessage());
            throw new MsgException("发件错误" + e);

        } finally {
            //3.保存邮件
            saveMail(mailDto);
        }
    }

    /**
     * 检测邮件信息类
     */
    private void checkMail(MailDto mailDto) {
        if (StringUtils.isEmpty(mailDto.getMailTo())) {
            throw new MsgException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailDto.getSubject())) {
            throw new MsgException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailDto.getContent())) {
            throw new MsgException("邮件内容不能为空");
        }
    }

    /**
     * 构建复杂邮件信息类
     */
    private void sendMimeMail(MailDto mailDto) {
        try {
            // true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            // 增强邮件对象
            enhanceMail(mailDto);
            // 邮件发件人
            messageHelper.setFrom(getMailSendFrom());
            // 邮件收信人
            messageHelper.setTo(mailDto.getMailTo().split(SPLIT_SYMBOL));
            // 邮件主题
            messageHelper.setSubject(mailDto.getSubject());
            // 邮件内容，格式化为html
            String templateMail = templateMail(mailDto.getContent(), mailDto.getTemplate());
            messageHelper.setText(templateMail, true);
            if (mailDto.getFiles() != null) {
                // 添加邮件附件
                for (File file : mailDto.getFiles()) {
                    messageHelper.addAttachment(file.getName(), file);
                }
            }

            // 发送时间
            messageHelper.setSentDate(mailDto.getSentDate());
            // 发送邮件
            mailSender.send(messageHelper.getMimeMessage());

            mailDto.setIsSent(true);
        } catch (Exception e) {
            throw new MsgException("发件错误：" + e);
        }
    }

    /**
     * 保存邮件
     */
    private void saveMail(MailDto mailDto) {
        // 将邮件保存到数据库..
        MailPo mail = DozerUtils.map(mailDto, MailPo.class);
        mailMapper.insert(mail);
    }

    /**
     * 邮件信息的增强处理
     * 添加邮件id，对邮件的空值进行定义
     */
    private void enhanceMail(MailDto mailDto) {
        // 设置邮件id
        mailDto.setId(SnowflakeUtil.genId());

        // 发件人处理
        if (StringUtils.isEmpty(mailDto.getMailFrom())) {
            mailDto.setMailFrom(getMailSendFrom());
        }

        // 发件时间处理
        if (StringUtils.isEmpty((CharSequence) mailDto.getSentDate())) {
            mailDto.setSentDate(new Date());
        }
    }

    /**
     * mailVo.getText()为JSON格式，需要将Json转为Html模版格式
     *
     * @param content 待处理邮件正文
     */
    private String templateMail(String content, String localTemplate) {
        try {
            // 获得模板
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(localTemplate);

            log.info(content);

            // 解析正文内容
            Map<?, ?> map = JSON.parseObject(content, Map.class);
            log.info(map.toString());
            // 传入数据模型到模板，替代模板中的占位符，并将模板转化为html字符串
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

        } catch (TemplateException | IOException e) {
            log.error("发送邮件时发生异常！", e);
            throw new MsgException("发送邮件时发生异常");
        }
    }

    /**
     * 获取邮件发信人
     */
    public String getMailSendFrom() {
        return mailSender.getUsername();
    }


}




