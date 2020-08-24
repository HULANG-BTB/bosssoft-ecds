package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.ApplyDto;
import com.bosssoft.ecds.entity.po.ApplyPo;
import com.bosssoft.ecds.entity.po.ConfirmPo;
import com.bosssoft.ecds.entity.vo.ConfirmVo;

/**
 * @author: qiuheng
 * @create: 2020-08-21 10:18
 **/
public interface ConfirmService extends IService<ConfirmPo> {
    boolean insertConfirmInfo(ConfirmVo confirmVo);

}
