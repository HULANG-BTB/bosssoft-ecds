package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.entity.dto.SubjectDTO;
import com.bosssoft.ecds.entity.po.SubjectPO;
import com.bosssoft.ecds.dao.SubjectDao;
import com.bosssoft.ecds.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wy
 * @since 2020-08-10
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectDao, SubjectPO> implements SubjectService {
    @Autowired
    private  SubjectDao subjectDao;

    public List<SubjectDTO> getThird(){
        QueryWrapper<SubjectPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("level","3");
        List<SubjectPO> list = this.list(queryWrapper);
        return MyBeanUtil.copyListProperties(list,SubjectDTO.class);
    }

}
