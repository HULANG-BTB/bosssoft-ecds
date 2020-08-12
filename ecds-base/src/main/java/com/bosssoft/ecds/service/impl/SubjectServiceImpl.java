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


    /**
     * 获得所有三级科目
     * @param
     * @return
     */
    public List<SubjectDTO> getThird(){
        QueryWrapper<SubjectPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("level","3");
        List<SubjectPO> list = this.list(queryWrapper);
        return MyBeanUtil.copyListProperties(list,SubjectDTO::new);
    }

    /**
     *根据3级科目id返回其及其下4级科目
     * @param pid
     * @return
     */
    public List<SubjectDTO> getFourthByPID(long pid){
        List<SubjectDTO> subjectDTOS =new ArrayList<>();
        SubjectPO subjectPO = this.getById(pid);
        SubjectDTO subjectDTO = MyBeanUtil.copyProperties(subjectPO, SubjectDTO.class);
        subjectDTOS.add(subjectDTO);
        QueryWrapper<SubjectPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SubjectPO.F_PARENT_ID,pid).orderByAsc(SubjectPO.F_CREATE_TIME);
        List<SubjectPO> subjectPOS = this.getBaseMapper().selectList(queryWrapper);
        List<SubjectDTO> subjectDTOS1 = MyBeanUtil.copyListProperties(subjectPOS, SubjectDTO::new);
        subjectDTOS.addAll(subjectDTOS1);
        return subjectDTOS;
    }
}
