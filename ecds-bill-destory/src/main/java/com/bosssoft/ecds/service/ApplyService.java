package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.po.ApplyPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qiuheng
 * @since 2020-08-12
 */
public interface ApplyService extends IService<ApplyPo> {
    ApplyPo selectById(Long id);

}
