package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.dao.MailDao;
import com.bosssoft.ecds.entity.dto.MailDto;
import com.bosssoft.ecds.entity.po.MailPo;
import com.bosssoft.ecds.service.MailAdminService;
import com.bosssoft.ecds.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/25 9:27
 */
@Service
public class MailAdminServiceImpl implements MailAdminService {

    @Resource
    private MailDao mailDao;

    @Override
    public int createMail(MailDto mailDto) {
        return 0;
    }

    @Override
    public int updateMail(MailDto mailDto) {
        return 0;
    }

    @Override
    public int deleteMail(MailDto mailDto) {
        return 0;
    }

    @Override
    public List<MailDto> retrieveMailList() {
        List<MailPo> mailPoList = mailDao.retrieveMailList();
        List<MailDto> mailDtoList = BeanUtils.convertList(mailPoList, MailDto.class);
        return mailDtoList;
    }
}
