package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.dao.SourceSetDao;
import com.bosssoft.ecds.entity.dto.SourceSetDto;
import com.bosssoft.ecds.entity.po.SourceSetPo;
import com.bosssoft.ecds.service.SourceSetService;
import com.bosssoft.ecds.utils.BeanUtils;
import com.bosssoft.ecds.utils.UpdateSourceMessageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        utils.update(sourceSetDto.getBillTypeCode());
        return result;
    }

    @Override
    public int updatePushNumber(SourceSetDto sourceSetDto) {
        SourceSetPo sourceSetPo = BeanUtils.convertObject(sourceSetDto, SourceSetPo.class);
        int result = sourceSetDao.updatePushNumber(sourceSetPo);
        utils.update(sourceSetDto.getBillTypeCode());
        return result;
    }

    @Override
    public int updateSet(SourceSetDto sourceSetDto) {
        SourceSetPo sourceSetPo = BeanUtils.convertObject(sourceSetDto, SourceSetPo.class);
        int result = sourceSetDao.updateSet(sourceSetPo);
        utils.update(sourceSetDto.getBillTypeCode());
        return result;
    }

    @Override
    public List<SourceSetDto> retrieveSetList() {
        List<SourceSetDto> list = BeanUtils.convertList(sourceSetDao.retrieveSetList(), SourceSetDto.class);
        return list;
    }

    @Override
    public SourceSetDto retrieveSetByCode(String billTypeCode) {
        SourceSetDto sourceSetDto = BeanUtils.convertObject(sourceSetDao.retrieveSetByCode(billTypeCode), SourceSetDto.class);
        return sourceSetDto;
    }
}
