package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillWarnArchiveDao;
import com.bosssoft.ecds.entity.dto.BillWarnDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.BillWarnArchivePO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.service.BillWarnArchiveService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 *  服务实现类
 *  归档预警
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Service
public class BillWarnArchiveServiceImpl extends ServiceImpl<BillWarnArchiveDao, BillWarnArchivePO> implements BillWarnArchiveService {

    @Autowired
    private BillWarnArchiveDao billWarnArchiveDao;

    @Override
    public PageDTO<BillWarnDTO> getBillWarnInfos(CommonQuery query) {
        /*存放分页对象信息*/
        PageDTO<BillWarnDTO> pageDTO = new PageDTO<>();
        // 构造查询条件
        Page<BillWarnArchivePO> pager = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<BillWarnArchivePO> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(BillWarnArchivePO::getAgenCode, query.getAgenCode())
                .orderByDesc(BillWarnArchivePO::getCreateTime);
        Page<BillWarnArchivePO> page = page(pager, lambdaQuery);
        // 赋予查询总数
        pageDTO.setTotal(page.getTotal());
        // 转换对象
        List<BillWarnArchivePO> list = page.getRecords();
        List<BillWarnDTO> dtos = MyBeanUtil.copyListProperties(list, BillWarnDTO.class);
        pageDTO.setData(dtos);
        return pageDTO;
    }

    @Override
    public void finaBillWarnArchive() {
        /*获取一天之内的票据预警信息*/
        List<BillWarnArchivePO> billWarnArchivePOS = billWarnArchiveDao.queryBillWarnInfos();
        Assert.notEmpty(billWarnArchivePOS, "暂无可归档的票据预警信息");
        this.saveBatch(billWarnArchivePOS);
    }
}
