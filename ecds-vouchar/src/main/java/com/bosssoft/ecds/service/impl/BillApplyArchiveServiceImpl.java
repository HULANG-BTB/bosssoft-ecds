package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillApplyArchiveDao;
import com.bosssoft.ecds.entity.dto.ArchiveOverViewDTO;
import com.bosssoft.ecds.entity.dto.BillApplyDTO;
import com.bosssoft.ecds.entity.po.BillApplyArchivePO;
import com.bosssoft.ecds.entity.query.CommonQuery;
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
     * 获取单位申请的可用票据
     */
    @Override
    public List<BillApplyDTO> getBillApplyInfo(CommonQuery query) {
        /*分页查询*/
        Page<BillApplyArchivePO> pager = new Page<>(query.getPage(), query.getLimit());

        LambdaQueryWrapper<BillApplyArchivePO> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(BillApplyArchivePO::getAgenCode, query.getAgenCode())
                .orderByAsc(BillApplyArchivePO::getApplyTime);
        Page<BillApplyArchivePO> page = super.page(pager, lambdaQuery);
        List<BillApplyArchivePO> list = page.getRecords();
        List<BillApplyDTO> res = new ArrayList<>();
        list.forEach(
                po -> {
                    BillApplyDTO billApplyDTO = BeanUtil.toBean(po, BillApplyDTO.class);
                    res.add(billApplyDTO);
                }
        );
        return res;
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
        List<BillApplyArchivePO> billApplyArchivePOS = dao.queryBillApplyAll();

        /*
         * 记录每家公司申请的总票数*/
        Map<String, Integer> counts = new ConcurrentHashMap<>(16);

        /*
         * 信息处理
         */
        Assert.notNull(billApplyArchivePOS, "所有单位票据申请信息为空");
        billApplyArchivePOS.forEach(
                item -> {
                    /*
                     * 记录票数*/
                    counts.computeIfAbsent(item.getAgenCode(), agenCode -> 0);
                    counts.computeIfPresent(item.getAgenCode(),
                            (agenCode, applyNumber) -> applyNumber + item.getBatchNum());
                }
        );

        /*
         * 申领信息归档
         */
        this.saveBatch(billApplyArchivePOS);

        /*
         *获取归档总览表中所有的公司
         */

        List<ArchiveOverViewDTO> archiveOverViewDTOS = archiveOverViewService.queryOverViewArchiveInfos(null);

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
}
