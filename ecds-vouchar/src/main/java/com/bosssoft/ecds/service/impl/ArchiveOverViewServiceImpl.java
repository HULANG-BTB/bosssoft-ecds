package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.entity.dto.AgenDto;
import com.bosssoft.ecds.entity.dto.ArchiveOverViewDto;
import com.bosssoft.ecds.entity.po.ArchivePO;
import com.bosssoft.ecds.dao.ArchiveOverViewDao;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;
import com.bosssoft.ecds.service.AgenService;
import com.bosssoft.ecds.service.ArchiveOverViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 *  归档总览
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Service
@Slf4j
public class ArchiveOverViewServiceImpl extends ServiceImpl<ArchiveOverViewDao, ArchivePO> implements ArchiveOverViewService {
    @Autowired
    ArchiveOverViewDao archiveDao;
    @Autowired
    AgenService agenService;
    @Override
    public ArchiveOverViewDto queryOverViewArchiveInfo(ArchiveOverViewQuery query) {
        ArchiveOverViewDto dto = new ArchiveOverViewDto();
        /**
         * 获取单位的详细信息
         */
        AgenDto agenDto = agenService.queryAgenInfo(query);
        BeanUtil.copyProperties(agenDto, dto);
        log.info(""+agenDto);
        log.info("dto1  "+dto);
        /**
         * 补充归档票据的票据信息
         */
        QueryWrapper<ArchivePO> qw = new QueryWrapper();
        LambdaQueryWrapper<ArchivePO> eq = qw
                .lambda()
                .eq(ArchivePO::getAgenCode, query.getAgenCode());
        ArchivePO po = archiveDao.selectOne(eq);
        BeanUtil.copyProperties(po, dto);
        log.info("dto2  "+dto);
        return dto;
    }
}
