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
    public int createSource(SourceSetDto sourceSetDto) {
        SourceSetPo sourceSetPo = BeanUtils.convertObject(sourceSetDto, SourceSetPo.class);
        sourceSetDao.deleteSourceTable(sourceSetPo);
        sourceSetDao.createTable(sourceSetPo);
        sourceSetDao.createTypeToPool(sourceSetPo);
        return sourceSetDao.createSet(sourceSetPo);
    }

    @Override
    public int updateSet(SourceSetDto sourceSetDto) {
        int result;
        SourceSetPo sourceSetPo = BeanUtils.convertObject(sourceSetDto, SourceSetPo.class);
        switch (sourceSetDto.getAlterCode()) {
            case 1:
                result = createSource(sourceSetDto);
                break;
            case 2:
                result = sourceSetDao.updateSet(sourceSetPo);
                break;
            default:
                result = -1;
        }
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
