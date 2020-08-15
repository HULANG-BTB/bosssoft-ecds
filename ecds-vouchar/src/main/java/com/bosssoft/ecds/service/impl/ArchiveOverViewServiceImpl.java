package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.ArchiveOverViewDao;
import com.bosssoft.ecds.entity.dto.AgenDto;
import com.bosssoft.ecds.entity.dto.ArchiveOverViewDTO;
import com.bosssoft.ecds.entity.po.AgenPO;
import com.bosssoft.ecds.entity.po.ArchivePO;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;
import com.bosssoft.ecds.service.AgenService;
import com.bosssoft.ecds.service.ArchiveOverViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

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
    public ArchiveOverViewDTO queryOverViewArchiveInfo(ArchiveOverViewQuery query) {
        ArchiveOverViewDTO dto = new ArchiveOverViewDTO();
        /*
         * 获取单位的详细信息
         */
        AgenDto agenDto = agenService.queryAgenInfo(query);
        BeanUtil.copyProperties(agenDto, dto);
        /*
         * 补充归档票据的票据信息
         */
        QueryWrapper<ArchivePO> qw = new QueryWrapper<>();
        LambdaQueryWrapper<ArchivePO> eq = qw
                .lambda()
                .eq(ArchivePO::getAgenCode, query.getAgenCode());
        ArchivePO po = archiveDao.selectOne(eq);
        BeanUtil.copyProperties(po, dto);
        return dto;
    }

    /**
     * 查询出所有归档公司的信息
     * 方便服务之间调用，不设置分页
     *
     * @return
     */
    @Override
    public List<ArchiveOverViewDTO> queryOverViewArchiveAllInfo() {
        List<ArchiveOverViewDTO> res = new ArrayList<>();

        /*
         * 查询出所有公司的公司编码
         */
        List<ArchivePO> archivePOS = archiveDao.selectList(null);

        /*
         * 转换参数类型
         */
        Assert.notEmpty(archivePOS, "归档中暂无公司信息");
        ArchiveOverViewDTO dto = null;
        for (ArchivePO po : archivePOS) {
            dto = new ArchiveOverViewDTO();
            BeanUtil.copyProperties(po, dto);
            res.add(dto);
        }

        /*
         * 查询出所有公司的信息,并且转换参数类型
         */
        for (int i = 0; i < res.size(); i++) {
            ArchiveOverViewDTO temp = res.get(i);
            LambdaQueryWrapper<AgenPO> lqw = new LambdaQueryWrapper<>();
            lqw.eq(AgenPO::getAgenCode, temp.getAgenCode());
            AgenPO one = agenService.getOne(lqw);
            BeanUtil.copyProperties(one, temp);
            res.set(i, temp);
        }
        return res;
    }

    /**
     * 批量更新
     *
     * @param list
     * @return boolean
     */
    @Override
    public Boolean updateBatch(List<ArchiveOverViewDTO> list) {
        List<ArchivePO> res = new ArrayList<>();
        list.forEach(
                dto -> {
                    ArchivePO po = new ArchivePO();
                    BeanUtil.copyProperties(dto, po);
                    res.add(po);
                }
        );
        return saveOrUpdateBatch(res);
    }
}
