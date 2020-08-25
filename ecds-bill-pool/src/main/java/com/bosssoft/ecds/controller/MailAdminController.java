package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.MailDto;
import com.bosssoft.ecds.entity.vo.MailVo;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.MailAdminService;
import com.bosssoft.ecds.utils.BeanUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/25 9:31
 */
@Api
@RestController
@RequestMapping("/Mail")
public class MailAdminController {

    @Resource
    private MailAdminService mailAdminService;

    @RequestMapping(value = "/createMail", method = RequestMethod.GET)
    public int createMail(@RequestBody MailVo mailVo) {
        MailDto mailDto = BeanUtils.convertObject(mailVo, MailDto.class);
        return mailAdminService.createMail(mailDto);
    }

    @RequestMapping(value = "/updateMail", method = RequestMethod.POST)
    public int updateMail(@RequestBody MailVo mailVo) {
        MailDto mailDto = BeanUtils.convertObject(mailVo, MailDto.class);
        return mailAdminService.updateMail(mailDto);
    }

    @RequestMapping(value = "/deleteMail", method = RequestMethod.POST)
    public int deleteMail(@RequestBody MailVo mailVo) {
        MailDto mailDto = BeanUtils.convertObject(mailVo, MailDto.class);
        return mailAdminService.deleteMail(mailDto);
    }

    @RequestMapping(value = "/retrieveMailList", method = RequestMethod.GET)
    public ResponseResult retrieveMailList() {
        List<MailVo> mailVoList = BeanUtils.convertList(mailAdminService.retrieveMailList(),
                MailVo.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS, mailVoList);
    }
}