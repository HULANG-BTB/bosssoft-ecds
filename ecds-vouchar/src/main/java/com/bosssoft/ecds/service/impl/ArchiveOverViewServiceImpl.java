package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.ArchiveOverViewDao;
import com.bosssoft.ecds.entity.dto.ArchiveOverViewDTO;
import com.bosssoft.ecds.entity.po.ArchivePO;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;
import com.bosssoft.ecds.service.ArchiveOverViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 查询单位的票据信息
     *
     * @param query
     * @return
     */
    @Override
    public ArchiveOverViewDTO queryOverViewArchiveInfo(ArchiveOverViewQuery query) {
        return archiveDao.queryOverViewArchiveInfo(query);
    }

    /**
     * 查询出所有归档公司的信息
     * 方便服务之间调用，不设置分页
     *
     * @return
     */
    @Override
    public List<ArchiveOverViewDTO> queryOverViewArchiveInfos(ArchiveOverViewQuery archiveOverViewQuery) {
        return archiveDao.queryOverViewArchiveAllInfo(archiveOverViewQuery);
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
