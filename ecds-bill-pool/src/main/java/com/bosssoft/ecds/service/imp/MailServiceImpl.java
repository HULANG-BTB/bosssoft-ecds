package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.entity.dto.ErrorMailDto;
import com.bosssoft.ecds.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/24 19:10
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Resource
    private JavaMailSender mailSender;

    @Resource
    TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.nickname}")
    private String nickname;

    @Override
    public void sendTemplateMail(List<ErrorMailDto> list) {
        for (int i = 0; i < list.size(); i++) {
            Context context = new Context();
            context.setVariable("message", list.get(i).getContent());
            list.get(i).setContent(templateEngine.process("EmailErrorTemplate", context));
            MimeMessage message = mailSender.createMimeMessage();

            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(nickname + '<' + from + '>');
                helper.setTo(list.get(i).getTo());
                helper.setSubject(list.get(i).getSubject());
                helper.setText(list.get(i).getContent(), true);

                mailSender.send(message);
                logger.info("邮件发送成功");
            } catch (MessagingException e) {
                logger.error("邮件发生异常！", e);
            }
        }
    }
}
