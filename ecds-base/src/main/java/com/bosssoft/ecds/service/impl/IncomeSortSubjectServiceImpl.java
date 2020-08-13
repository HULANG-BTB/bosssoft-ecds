package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.entity.po.IncomeSortSubjectPO;
import com.bosssoft.ecds.dao.IncomeSortSubjectDao;
import com.bosssoft.ecds.service.IncomeSortSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wy
 * @since 2020-08-10
 */
@Service
public class IncomeSortSubjectServiceImpl extends ServiceImpl<IncomeSortSubjectDao, IncomeSortSubjectPO> implements IncomeSortSubjectService {

    @Autowired
    IncomeSortSubjectDao incomeSortSubjectDao;

    /**
     * 添加incomeSortSubjectPO
     * @param incomeSortSubjectPO
     * @return
     */
    public boolean add(IncomeSortSubjectPO incomeSortSubjectPO){
        return this.save(incomeSortSubjectPO);
    }
}
