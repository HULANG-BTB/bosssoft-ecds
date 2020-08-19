package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.po.IncomeSortSubjectPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wy
 * @since 2020-08-10
 */
public interface IncomeSortSubjectService extends IService<IncomeSortSubjectPO> {

    /**
     * 添加incomeSortSubjectPO
     *
     * @param incomeSortSubjectPO
     * @return
     */
    boolean add(IncomeSortSubjectPO incomeSortSubjectPO);

    /**
     * 添加或更新incomeSortSubjectPO
     */
    boolean add(Long incomeId, Long subjectId);

    /**
     * 根据科目id删除表中数据
     */
    boolean deleteBySid(Long id);

    /**
     * 根据收入类别id返回对象
     */
    IncomeSortSubjectPO selectByIncomeId(Long incomeId);
}
