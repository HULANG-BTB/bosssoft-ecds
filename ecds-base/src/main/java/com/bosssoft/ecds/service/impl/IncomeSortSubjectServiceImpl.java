package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.entity.po.IncomeSortSubjectPO;
import com.bosssoft.ecds.dao.IncomeSortSubjectDao;
import com.bosssoft.ecds.entity.po.SubjectPO;
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
    @Override
    public boolean add(IncomeSortSubjectPO incomeSortSubjectPO){
        return this.save(incomeSortSubjectPO);
    }

    /**
     * 添加或更新incomeSortSubjectPO
     */
    @Override
    public boolean add(Long incomeId,Long subjectId){
        IncomeSortSubjectPO oldPO = selectByIncomeId(incomeId);
        if(oldPO!=null){
            oldPO.setSubjectId(subjectId);
            return baseMapper.updateById(oldPO)==1?true:false;
        }else{
            IncomeSortSubjectPO incomeSortSubjectPO = new IncomeSortSubjectPO();
            incomeSortSubjectPO.setSubjectId(subjectId);
            incomeSortSubjectPO.setIncomeSortId(incomeId);
            return this.save(incomeSortSubjectPO);
        }
    }

    /**
     * 根据收入类别id返回对象
     */
    @Override
    public IncomeSortSubjectPO selectByIncomeId(Long incomeId){
        QueryWrapper<IncomeSortSubjectPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_income_sort_id",incomeId);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据科目id删除表中数据
     */
    @Override
    public boolean deleteBySid(Long id){
        QueryWrapper<IncomeSortSubjectPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_subject_id", id);
        return remove(queryWrapper);
    }
}
