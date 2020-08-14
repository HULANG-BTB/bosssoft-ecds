package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.dao.SourceSetDao;
import com.bosssoft.ecds.entity.dto.SourceSetDto;
import com.bosssoft.ecds.entity.po.SourceSetPo;
import com.bosssoft.ecds.service.SourceSetService;
import com.bosssoft.ecds.utils.BeanUtils;
import com.bosssoft.ecds.utils.UpdateSourceMessageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SourceSetServiceImp implements SourceSetService {

    @Resource
    SourceSetDao sourceSetDao;

    @Resource
    UpdateSourceMessageUtils utils;

    @Override
    public int updateMin(SourceSetDto sourceSetDto) {
        SourceSetPo sourceSetPo = BeanUtils.convertObject(sourceSetDto, SourceSetPo.class);
        int result = sourceSetDao.updateMin(sourceSetPo);
        utils.update(sourceSetDto.getRegionCode());
        return result;
    }

    @Override
    public int updatePushNumber(SourceSetDto sourceSetDto) {
        SourceSetPo sourceSetPo = BeanUtils.convertObject(sourceSetDto, SourceSetPo.class);
        int result = sourceSetDao.updatePushNumber(sourceSetPo);
        utils.update(sourceSetDto.getRegionCode());
        return result;
    }
}
