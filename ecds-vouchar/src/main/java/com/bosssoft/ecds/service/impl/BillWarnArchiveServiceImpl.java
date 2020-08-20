package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillWarnArchiveDao;
import com.bosssoft.ecds.entity.dto.BillWarnDTO;
import com.bosssoft.ecds.entity.po.BillWarnArchivePO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.service.BillWarnArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
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
    public List<BillWarnDTO> getBillWarnInfos(CommonQuery query) {
        Page<BillWarnArchivePO> pager = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<BillWarnArchivePO> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(BillWarnArchivePO::getAgenCode, query.getAgenCode())
                .orderByDesc(BillWarnArchivePO::getCreateTime);
        Page<BillWarnArchivePO> page = page(pager, lambdaQuery);
        List<BillWarnArchivePO> list = page.getRecords();
        List<BillWarnDTO> res = new ArrayList<>();
        list.forEach(
                po -> {
                    BillWarnDTO dto = BeanUtil.toBean(po, BillWarnDTO.class);
                    res.add(dto);
                }
        );
        return res;
    }

    @Override
    public void finaBillWarnArchive() {
        /*获取一天之内的票据预警信息*/
        List<BillWarnArchivePO> billWarnArchivePOS = billWarnArchiveDao.queryBillWarnInfos();
        Assert.notEmpty(billWarnArchivePOS, "暂无可归档的票据预警信息");
        this.saveBatch(billWarnArchivePOS);
    }
}
