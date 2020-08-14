package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillCheckArchiveDao;
import com.bosssoft.ecds.entity.dto.BillCheckDTO;
import com.bosssoft.ecds.entity.dto.CBillAccountingDTO;
import com.bosssoft.ecds.entity.po.*;
import com.bosssoft.ecds.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类 审验记录
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Service
@Slf4j
public class BillCheckArchiveServiceImpl extends ServiceImpl<BillCheckArchiveDao, BillCheckArchivePO> implements BillCheckArchiveService {

    @Autowired
    WriteoffService writeoffService;
    @Autowired
    WriteoffBillitemService writeoffBillitemService;
    @Autowired
    CbillAccountingService cbillAccountingService;
    @Autowired
    ArchiveOverViewService archiveOverViewService;
    @Autowired
    BillCheckArchiveDao billCheckArchiveDao;

    @Override
    public List<BillCheckDTO> getBillCheckInfos() {
        return null;
    }

    @Override
    public void finaBillCheckArchive() {
        List<BillCheckDTO> res = new ArrayList<>();

        /*
            票据审核信息收集并且入库   一段时间内
         */
        LambdaQueryWrapper<WriteoffPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.apply("date_format(f_create_time, '%Y-%m-%d') = date_format(date_sub(SYSDATE(), interval 1 day), '%Y-%m-%d')");
        List<WriteoffPO> writeoffPOS = writeoffService.list(wrapper);

        /*
            收集审验数据
         */
        writeoffPOS.forEach(
                item -> {
                    BillCheckDTO dto = new BillCheckDTO();
                    dto.setAgenCode(item.getAgenIdCode());
                    dto.setSignTime(item.getCheckDate());
                    dto.setSignName(item.getAuthor());
                    dto.setApplyId(item.getApplyId());
                    dto.setSignStatus(item.getCheckResult());
                    res.add(dto);
                }
        );

        /*
            收集审验票据数据并且归档
         */
        res.forEach(
                item -> {
                    LambdaQueryWrapper<WriteoffBillitemPO> lqw = new LambdaQueryWrapper<>();
                    lqw.eq(WriteoffBillitemPO::getPid, item.getApplyId())
                            .and(w -> w.apply("date_format(f_create_time, '%Y-%m-%d') = date_format(date_sub(SYSDATE(), interval 1 day), '%Y-%m-%d')"));
                    WriteoffBillitemPO one = writeoffBillitemService.getOne(lqw);
                    item.setBillCode(one.getBillCode());
                    item.setBillName(one.getBillName());
                    item.setBillNumber(one.getNumber());

                    /*
                        归档
                     */
                    BillCheckArchivePO po = new BillCheckArchivePO();
                    BeanUtil.copyProperties(item, po);
                    po.setVersion(1);
                    po.setLogicDelete(false);
                    billCheckArchiveDao.insert(po);
                }
        );

        /*
         * 更新信息
         */
        this.updateOverViewArchiveInfos();
    }

    /**
     * 更新归档总览表中的数据信息
     *
     * @return boolean
     */
    private boolean updateOverViewArchiveInfos() {
        /*
         * 获取各个公司一定时间内的审核信息
         */
        LambdaQueryWrapper<CbillAccountingPO> lqw = new LambdaQueryWrapper<>();
        lqw.apply("date_format(f_create_time, '%Y-%m-%d') = date_format(date_sub(SYSDATE(), interval 1 day), '%Y-%m-%d')");
        List<CbillAccountingPO> list = cbillAccountingService.list(lqw);

        /*
         * 信息整理  更新
         */
        Map<String, CBillAccountingDTO> cMap = new HashMap<>();

        /*
         * 各个公司的票据不同阶段的数量
         */
        list.forEach(
                item -> {
                    if (!cMap.containsKey(item.getAgenIdcode())) {
                        CBillAccountingDTO dto = new CBillAccountingDTO();
                        dto.setAgenIdcode(item.getAgenIdcode());
                        dto.setBillUsedNumber(1L);
                        if (Boolean.TRUE.equals(item.getAccountStatus())) {
                            dto.setBillCheckedNumber(1L);
                            dto.setBillUncheckNumber(0L);
                        } else {
                            dto.setBillCheckedNumber(0L);
                            dto.setBillUncheckNumber(1L);
                        }
                        cMap.put(item.getAgenIdcode(), dto);
                    } else {
                        /*
                         * map  数据操作简化  优化时间复杂度
                         * 根据规则实现自增
                         */
                        cMap.computeIfPresent(
                                item.getAgenIdcode(),
                                (k, v) -> {
                                    v.setBillUsedNumber(v.getBillUsedNumber() + 1);
                                    if (Boolean.TRUE.equals(item.getAccountStatus())) {
                                        v.setBillCheckedNumber(v.getBillCheckedNumber() + 1);
                                    } else {
                                        v.setBillUncheckNumber(v.getBillUncheckNumber() + 1);
                                    }
                                    return v;
                                }
                        );
                    }
                }
        );

        /**
         * 汇总 所有公司不同阶段的票据数量
         */
        /*
         * 读取各公司原来的信息
         */
        List<ArchivePO> infos = archiveOverViewService.list();
        log.info("infos old : " + infos);
        infos.forEach(
                item -> {
                    /*
                     * 判断该公司的信息是否变化
                     */
                    if (cMap.containsKey(item.getAgenCode())) {
                        log.info("item old info" + item);
                        CBillAccountingDTO cBillAccountingDTO = cMap.get(item.getAgenCode());
                        log.info("cbillAcc " + cBillAccountingDTO);
                        Long oldUseNumber = item.getUseNumber();
                        Long oldAuthorNumber = item.getAuthorNumber();
                        Long oldUnAuthorNumber = item.getUnAuthorNumber();
                        Long billUsedNumber = cBillAccountingDTO.getBillUsedNumber();
                        Long billCheckedNumber = cBillAccountingDTO.getBillCheckedNumber();
                        Long billUncheckNumber = cBillAccountingDTO.getBillUncheckNumber();
                        item.setUseNumber(oldUseNumber + billUsedNumber);
                        item.setAuthorNumber(oldAuthorNumber + billCheckedNumber);
                        item.setUnAuthorNumber(oldUnAuthorNumber + billUncheckNumber);
                        log.info("item new info " + item);
                    }
                }
        );
        log.info("infos new : " + infos);

        /*
         * 批量更新
         */
        return archiveOverViewService.updateBatchById(infos);
    }

    @Override
    public List<BillCheckDTO> getBillCheckInfo(String agenIdCode) {
        return null;
    }

    @Override
    public void unitBillCheckArchive() {

    }
}
