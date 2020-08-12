package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.entity.dto.AgenDto;
import com.bosssoft.ecds.entity.dto.ArchiveOverViewDto;
import com.bosssoft.ecds.entity.po.AgenPO;
import com.bosssoft.ecds.dao.AgenDao;
import com.bosssoft.ecds.entity.po.ArchivePO;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;
import com.bosssoft.ecds.service.AgenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuke
 * @since 2020-08-12
 */
@Service
@Slf4j
public class AgenServiceImpl extends ServiceImpl<AgenDao, AgenPO> implements AgenService {
    @Autowired
    private AgenDao agenDao;

    /**
     * 查询出单位的详细信息
     * @param query
     * @return AgenDto
     */
    @Override
    public AgenDto queryAgenInfo(ArchiveOverViewQuery query) {
        QueryWrapper<AgenPO> wrapper = new QueryWrapper();
        LambdaQueryWrapper<AgenPO> lambdaQueryWrapper = wrapper
                .lambda()
                .eq(AgenPO::getAgenCode, query.getAgenCode());
        AgenPO agenPO = agenDao.selectOne(lambdaQueryWrapper);
        AgenDto agenDto = new AgenDto();
        BeanUtil.copyProperties(agenPO, agenDto);
        return agenDto;
    }
}
