package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.entity.po.ApplyPo;
import com.bosssoft.ecds.dao.ApplyDao;
import com.bosssoft.ecds.service.ApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qiuheng
 * @since 2020-08-12
 */
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyDao, ApplyPo> implements ApplyService {

    @Autowired
    ApplyDao applyDao;

    @Override
    public ApplyPo selectById(Long id) {
        return applyDao.selectById(id);
    }
}
