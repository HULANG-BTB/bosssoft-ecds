package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.dao.SourceSetDao;
import com.bosssoft.ecds.entity.dto.SourceSetDto;
import com.bosssoft.ecds.entity.po.SourceSetPo;
import com.bosssoft.ecds.service.SourceSetService;
import com.bosssoft.ecds.utils.BeanUtils;
import com.bosssoft.ecds.utils.UpdateSourceMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
@Service
public class SourceSetServiceImpl implements SourceSetService {

    private static final Logger logger = LoggerFactory.getLogger(SourceSetServiceImpl.class);

    @Resource
    SourceSetDao sourceSetDao;

    @Resource
    UpdateSourceMessageUtils utils;

    @Resource
    RedisTemplate redisTemplate;

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
        boolean exist = redisTemplate.hasKey(sourceSetDto.getBillTypeCode());
        if (exist) {
            logger.info("票据已存在");
            return -1;
        } else {
            SourceSetPo sourceSetPo = BeanUtils.convertObject(sourceSetDto, SourceSetPo.class);
            sourceSetDao.deleteSourceTable(sourceSetPo);
            sourceSetDao.createTable(sourceSetPo);
            sourceSetDao.createTypeToPool(sourceSetPo);
            return sourceSetDao.createSet(sourceSetPo);
        }
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
            case 3:
                result = sourceSetDao.updateSet(sourceSetPo);
                break;
            default:
                result = -1;
        }
        if (result != -1) {
            utils.update(sourceSetDto.getBillTypeCode());
        }
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
