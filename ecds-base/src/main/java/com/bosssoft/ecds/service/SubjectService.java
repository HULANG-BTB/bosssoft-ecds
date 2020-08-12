package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.SubjectDTO;
import com.bosssoft.ecds.entity.po.SubjectPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wy
 * @since 2020-08-10
 */
public interface SubjectService extends IService<SubjectPO> {
    /**
     *根据3级科目id返回其及其下4级科目
     * @param pid
     * @return
     */
     List<SubjectDTO> getFourthByPID(long pid);
}
