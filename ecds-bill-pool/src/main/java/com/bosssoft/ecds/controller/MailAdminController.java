package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.vo.MailVo;
import com.bosssoft.ecds.service.MailAdminService;
import com.bosssoft.ecds.utils.BeanUtils;
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
@RestController
public class MailAdminController {

    @Resource
    private MailAdminService mailAdminService;

    @RequestMapping(value = "/retrieveMailList", method = RequestMethod.GET)
    public List<MailVo> retrieveMailList() {
        List<MailVo> mailVoList = BeanUtils.convertList(mailAdminService.retrieveMailList(),
                MailVo.class);
        return mailVoList;
    }
}
