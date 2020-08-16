package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillCheckArchiveDao;
import com.bosssoft.ecds.entity.dto.BillCheckDTO;
import com.bosssoft.ecds.entity.dto.CBillAccountingDTO;
import com.bosssoft.ecds.entity.po.ArchivePO;
import com.bosssoft.ecds.entity.po.BillCheckArchivePO;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import com.bosssoft.ecds.service.ArchiveOverViewService;
import com.bosssoft.ecds.service.BillCheckArchiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    ArchiveOverViewService archiveOverViewService;
    @Autowired
    BillCheckArchiveDao billCheckArchiveDao;

    @Override
    public List<BillCheckDTO> getBillCheckInfos() {
        return null;
    }

    @Override
    public void finaBillCheckArchive() {
        List<BillCheckArchivePO> billCheckArchivePOS = billCheckArchiveDao.collectBillCheckInfo();
        saveBatch(billCheckArchivePOS);
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
        log.info("updateOverViewArch start");
        /*
         * 获取各个公司一定时间内的审核信息
         */
        List<CbillAccountingPO> list = billCheckArchiveDao.collectCBillAccountInfo();
        Assert.notNull(list, "list为空！！");
        /*
         * 信息整理  更新
         */
        Map<String, CBillAccountingDTO> cMap = new HashMap<>(16);

        /*
         * 各个公司的票据不同阶段的数量
         */
        Assert.notEmpty(list, "公司的审核信息为空！");

        log.info(" " + list);
        list.forEach(
                item -> {
                    /*
                     * map  数据操作简化  优化时间复杂度
                     * 根据规则实现自增
                     */
                    cMap.computeIfAbsent(item.getAgenIdcode(),
                            k -> {
                                CBillAccountingDTO dto = new CBillAccountingDTO();
                                dto.setAgenIdcode(item.getAgenIdcode());
                                dto.setBillUsedNumber(0L);
                                dto.setBillCheckedNumber(0L);
                                dto.setBillUncheckNumber(0L);
                                return dto;
                            });
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
        );

        /**
         * 汇总 所有公司不同阶段的票据数量
         * 读取各公司原来的信息
         */
        List<ArchivePO> infos = archiveOverViewService.list();
        Assert.notEmpty(infos, "归档对象不为空");
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
        /*
         * 批量更新
         */
        return archiveOverViewService.updateBatchById(infos);
    }

    @Override
    public List<BillCheckDTO> getBillCheckInfo(String agenIdCode) {
        return null;
    }

}
