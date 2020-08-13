package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.po.IncomeSortSubjectPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wy
 * @since 2020-08-10
 */
public interface IncomeSortSubjectService extends IService<IncomeSortSubjectPO> {

    /**
     * 添加incomeSortSubjectPO
     * @param incomeSortSubjectPO
     * @return
     */
    public boolean add(IncomeSortSubjectPO incomeSortSubjectPO);



}
