package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillApplyArchiveDao;
import com.bosssoft.ecds.entity.dto.BillApplyDto;
import com.bosssoft.ecds.entity.po.BillApplyArchivePO;
import com.bosssoft.ecds.service.BillApplyArchiveService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    /**
     * 财政端记录票据的使用方式
     */

    @Override
    public List<BillApplyDto> getBillApplyInfos() {
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
        /**
         * 获取所有单位的票据申领信息
         */

        /**
         * 信息处理
         */

        /**
         * 申领信息归档
         */
    }

    /**
     * <p>前提：一定时间段下
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
    public BillApplyDto getBillApplyInfo(String agenCode) {
        return null;
    }

    @Override
    public void unitBillApplyArchive() {

    }
}
