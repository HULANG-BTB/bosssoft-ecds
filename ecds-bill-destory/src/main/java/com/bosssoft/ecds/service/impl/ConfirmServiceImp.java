package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.ApplyMapper;
import com.bosssoft.ecds.dao.ConfirmMapper;
import com.bosssoft.ecds.entity.po.ApplyPo;
import com.bosssoft.ecds.entity.po.ConfirmPo;
import com.bosssoft.ecds.entity.vo.ConfirmVo;
import com.bosssoft.ecds.service.ConfirmService;
import com.bosssoft.ecds.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author: qiuheng
 * @create: 2020-08-21 10:20
 **/
@Service
public class ConfirmServiceImp extends ServiceImpl<ConfirmMapper, ConfirmPo> implements ConfirmService {
    @Autowired
    ConfirmMapper confirmMapper;

    @Override
    public boolean insertConfirmInfo(ConfirmVo confirmVo) {
        ConfirmPo confirmPo = new ConfirmPo();
        BeanUtils.copyProperties(confirmVo,confirmPo);
        confirmPo.setfId(CommonUtil.generateID());
        confirmPo.setfCreateTime(LocalDateTime.now());
        confirmPo.setfUpdateTime(LocalDateTime.now());
        confirmPo.setfVersion(0);
        confirmMapper.insert(confirmPo);
        return false;
    }
}
