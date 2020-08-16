package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillWarnArchiveDao;
import com.bosssoft.ecds.entity.po.BillWarnArchivePO;
import com.bosssoft.ecds.service.BillWarnArchiveService;
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
    public void finaBillWarnArchive() {
        /*获取一天之内的票据预警信息*/
        List<BillWarnArchivePO> billWarnArchivePOS = billWarnArchiveDao.queryBillWarnInfos();
        Assert.notEmpty(billWarnArchivePOS, "暂无可归档的票据预警信息");
        this.saveBatch(billWarnArchivePOS);
    }
}
