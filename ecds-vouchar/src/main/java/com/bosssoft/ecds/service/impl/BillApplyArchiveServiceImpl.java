package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillApplyArchiveDao;
import com.bosssoft.ecds.entity.dto.ArchiveOverViewDTO;
import com.bosssoft.ecds.entity.dto.BillApplyDTO;
import com.bosssoft.ecds.entity.po.BillApplyArchivePO;
import com.bosssoft.ecds.service.ArchiveOverViewService;
import com.bosssoft.ecds.service.BillApplyArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 服务实现类
 * 单位开票和财政开票记录方式不同
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Service
public class BillApplyArchiveServiceImpl extends ServiceImpl<BillApplyArchiveDao, BillApplyArchivePO> implements BillApplyArchiveService {
    @Autowired
    private BillApplyArchiveDao dao;
    @Autowired
    private ArchiveOverViewService archiveOverViewService;

    /**
     * 财政端记录票据的使用方式
     */
    @Override
    public List<BillApplyDTO> getBillApplyInfos() {
        return null;
    }

    /**
     * <p>前提：一定时间段下
     * 1.获取所有单位的票据申领信息
     * 2.记录归档
     * 3.统计各个单位的票据申领情况汇总到归档主表
     * 可创建新的数据结构 BillApplyCount[agenCode,billApplyCount] List
     * 统计数量，更新主表信息
     * 相关表 ： fbe_stock_financeapply
     * </p>
     */
    @Override
    public void finaBillApplyArchive() {
        /*
         * 获取所有单位的票据申领信息
         */
        List<BillApplyDTO> billApplyDTOS = dao.queryBillApplyAll();

        /*
         * 记录每家公司申请的总票数*/
        Map<String, Integer> counts = new ConcurrentHashMap<>(16);

        /*
         * 信息处理
         */
        List<BillApplyArchivePO> res = new ArrayList<>();
        Assert.notNull(billApplyDTOS, "所有单位票据申请信息为空");
        billApplyDTOS.forEach(
                item -> {
                    BillApplyArchivePO po = new BillApplyArchivePO();
                    BeanUtil.copyProperties(item, po);
                    po.setVersion(0);
                    po.setLogicDelete(false);

                    /*
                     * 记录票数*/
                    counts.computeIfAbsent(po.getAgenCode(), agenCode -> 0);
                    counts.computeIfPresent(po.getAgenCode(),
                            (agenCode, applyNumber) -> applyNumber + item.getBatchNum());
                    res.add(po);
                }
        );

        /*
         * 申领信息归档
         */
        Assert.notNull(res, "所有单位票据申请信息的po集合为空");
        this.saveBatch(res);

        /*
         *获取归档总览表中所有的公司
         */
        List<ArchiveOverViewDTO> archiveOverViewDTOS = archiveOverViewService.queryOverViewArchiveAllInfo();

        /*
         * 更新归档总览表数量
         * 更新每家单位的数量
         * */
        archiveOverViewDTOS.forEach(
                item -> counts.computeIfPresent(item.getAgenCode(),
                        (agenCode, applyNumber) -> {
                            /*更新公司申请的值*/
                            item.setApplyNumber(applyNumber + item.getApplyNumber());
                            return 0;
                        }
                )

        );
        archiveOverViewService.updateBatch(archiveOverViewDTOS);
    }

    @Override
    public Long queryBillApplyAllNumber() {
        return null;
    }

    /**
     * <p>前提：一定时间段下  ×
     * 单位段
     * 1.获取本单位的票据申领信息
     * 2.记录归档
     * 3.统计本单位的票据申领情况汇总到归档主表
     * 可创建新的数据结构 BillApplyCount[agenCode,billApplyCount]
     * 统计数量，更新主表信息
     * 4.相关表 ：une_cbill
     * </p>
     */

    @Override
    public BillApplyDTO getBillApplyInfo(String agenCode) {
        return null;
    }

    @Override
    public Long queryBillApplyNumber(String agenCode) {
        return null;
    }

}
