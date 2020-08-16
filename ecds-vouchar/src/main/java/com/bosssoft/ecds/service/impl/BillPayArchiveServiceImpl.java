package com.bosssoft.ecds.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillPayArchiveDao;
import com.bosssoft.ecds.entity.dto.BillPayDTO;
import com.bosssoft.ecds.entity.po.BillPayArchivePO;
import com.bosssoft.ecds.service.BillPayArchiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * 票据缴销
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Service
@Slf4j
public class BillPayArchiveServiceImpl extends ServiceImpl<BillPayArchiveDao, BillPayArchivePO> implements BillPayArchiveService {
    @Autowired
    BillPayArchiveDao billPayArchiveDao;

    @Override
    public void finaBillPayArchive() {
        List<BillPayDTO> billPayDTOS = billPayArchiveDao.queryBillPayInfos();
        log.info("old " + billPayDTOS);
        Assert.notEmpty(billPayDTOS, "缴款信息暂时为空");
        /*分组*/
        Map<String, List<BillPayDTO>> collect = billPayDTOS.stream().collect(
                Collectors.groupingBy(BillPayDTO::getAgenCode)
        );
        log.info("collect :" + collect);
        /*统计*/
        List<BillPayArchivePO> res = new ArrayList<>();
        collect.forEach(
                (agenCode, dtos) -> {
                    /*初始化变量*/
                    BillPayArchivePO po = new BillPayArchivePO();
                    po.setAgenCode(agenCode);
                    po.setAgenName(dtos.get(0).getAgenName());
                    po.setSummaryTime(new Date());
                    /*存储金额数据*/
                    BigDecimal[] accounts = {BigDecimal.ZERO, BigDecimal.ZERO};
                    /*统计数据*/
                    dtos.forEach(
                            dto -> {
                                accounts[0] = accounts[0].add(dto.getWaitAccount());
                                accounts[1] = accounts[1].add(dto.getAccount());
                            }
                    );
                    po.setUseNumber((int) dtos.stream().count());
                    log.info("number " + po.getUseNumber());
                    po.setWaitAccount(accounts[0]);
                    po.setAccount(accounts[1]);
                    po.setVersion(0);
                    po.setLogicDelete(false);
                    res.add(po);
                }
        );
        /*批量插入*/
        saveBatch(res);
    }
}
