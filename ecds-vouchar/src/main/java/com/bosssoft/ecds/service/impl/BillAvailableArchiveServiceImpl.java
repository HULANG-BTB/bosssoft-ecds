package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillAvailableArchiveDao;
import com.bosssoft.ecds.entity.dto.BillAvailableInfoDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.BillAvailableArchivePO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.service.BillAvailableArchiveService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuke
 * @since 2020-08-13
 */
@Service
@Slf4j
public class BillAvailableArchiveServiceImpl extends ServiceImpl<BillAvailableArchiveDao, BillAvailableArchivePO> implements BillAvailableArchiveService {

    @Autowired
    private BillAvailableArchiveDao billAvailableArchiveDao;

    @Override
    public PageDTO<BillAvailableInfoDTO> getBillApplyInfos(CommonQuery query) {
        /*存放分页对象信息*/
        PageDTO<BillAvailableInfoDTO> pageDTO = new PageDTO<>();
        /*分页查询条件*/
        Page<BillAvailableArchivePO> pager = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<BillAvailableArchivePO> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(BillAvailableArchivePO::getAgenCode, query.getAgenCode())
                .orderByDesc(BillAvailableArchivePO::getCreateTime);
        Page<BillAvailableArchivePO> page = super.page(pager, lambdaQuery);
        pageDTO.setTotal(page.getTotal());
        List<BillAvailableArchivePO> list = page.getRecords();
        // 转换对象
        List<BillAvailableInfoDTO> dtos = MyBeanUtil.copyListProperties(list, BillAvailableInfoDTO::new);
        pageDTO.setData(dtos);
        return pageDTO;
    }

    @Override
    public void finaBillApplyArchive() {
        /*获取所有单位一天之内的票据申领信息*/
        List<BillAvailableInfoDTO> billAvailableInfoDTOS = billAvailableArchiveDao.collectBillAvailableInfo();
        List<BillAvailableArchivePO> billAvailableArchivePOS = new ArrayList<>();

        billAvailableInfoDTOS.forEach(
                dto -> {
                    BillAvailableArchivePO po = BeanUtil.toBean(dto, BillAvailableArchivePO.class);
                    /*
                     * 初始化数据
                     */
                    po.setVersion(0);
                    po.setLogicDelete(false);
                    billAvailableArchivePOS.add(po);
                }
        );
        /*批量插入*/
        saveBatch(billAvailableArchivePOS);
    }

}
