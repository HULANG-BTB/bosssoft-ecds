package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.dao.MailDao;
import com.bosssoft.ecds.entity.dto.ErrorMailDto;
import com.bosssoft.ecds.entity.po.MailPo;
import com.bosssoft.ecds.service.MailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/24 19:17
 */
@RestController
public class TestController {

    @Resource
    MailService mailService;

    @Resource
    MailDao mailDao;

    @RequestMapping("/test")
    public void test() {
        System.out.println(mailDao.retrieveMailList().toString());
        String to = "std373567953@163.com";
        String subject = "hello";
        String content = "word";
        List<MailPo> mailPoList = mailDao.retrieveMailList();
        List<ErrorMailDto> errorMailDtoList = new ArrayList<>();

        for (int i = 0; i < mailPoList.size(); i ++) {
            ErrorMailDto errorMailDto = new ErrorMailDto(mailPoList.get(i).getEmail(), "自动放票系统发生错误",
                    "88888888未在规定时间内放票");
            errorMailDtoList.add(errorMailDto);
        }
        mailService.sendTemplateMail(errorMailDtoList);
    }
}
