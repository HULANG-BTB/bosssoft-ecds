package com.bosssoft.usm.service.impl;

import com.bosssoft.usm.dao.mapper.TestMapper;
import com.bosssoft.usm.entity.po.Test;
import com.bosssoft.usm.service.AbstractService;
import com.bosssoft.usm.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author 张东海
 * @date 2020/8/10
 * @description
 */
@Service
public class TestServiceImpl extends AbstractService<Test, TestMapper>
    implements TestService {

}
