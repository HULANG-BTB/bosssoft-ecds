package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.common.exception.MyExceptionCode;
import com.bosssoft.ecds.dao.BillPayArchiveDao;
import com.bosssoft.ecds.entity.dto.BillPayDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.BillPayArchivePO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.exception.ExceptionCast;
import com.bosssoft.ecds.service.BillPayArchiveService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

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
@Slf4j(topic = "kafka_business_logger")
public class BillPayArchiveServiceImpl extends ServiceImpl<BillPayArchiveDao, BillPayArchivePO> implements BillPayArchiveService {
    @Autowired
    BillPayArchiveDao billPayArchiveDao;

    @Override
    public PageDTO<BillPayDTO> getBillPayInfos(CommonQuery query) {
        /*存放分页对象信息*/
        PageDTO<BillPayDTO> pageDTO = new PageDTO<>();
        // 构造查询条件
        Page<BillPayArchivePO> pager = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<BillPayArchivePO> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(BillPayArchivePO::getAgenCode, query.getAgenCode())
                .orderByDesc(BillPayArchivePO::getSummaryTime);
        Page<BillPayArchivePO> page = super.page(pager, lambdaQuery);
        // 赋予查询总数
        pageDTO.setTotal(page.getTotal());
        // 获取并且转换数据
        List<BillPayArchivePO> list = page.getRecords();
        List<BillPayDTO> dtos = MyBeanUtil.copyListProperties(list, BillPayDTO::new);
        pageDTO.setData(dtos);
        return pageDTO;
    }

    @Override
    public void finaBillPayArchive() {
        List<BillPayArchivePO> billPayArchivePOS = billPayArchiveDao.queryBillPayInfos();
        log.info("old " + billPayArchivePOS);
        if (billPayArchivePOS == null || billPayArchivePOS.isEmpty()) {
            ExceptionCast.cast(MyExceptionCode.BILL_PAY_DATE_EMPTY);
        }
        /*分组*/
        Map<String, List<BillPayArchivePO>> collect = new HashMap<>(16);
        for (BillPayArchivePO billPayArchivePO : billPayArchivePOS) {
            collect.computeIfAbsent(billPayArchivePO.getAgenCode(), k -> new ArrayList<>()).add(billPayArchivePO);
        }
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
                    res.add(po);
                }
        );
        /*批量插入*/
        saveBatch(res);
    }
}
