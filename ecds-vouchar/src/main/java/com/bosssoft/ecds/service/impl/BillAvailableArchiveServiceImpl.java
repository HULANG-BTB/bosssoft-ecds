package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillAvailableArchiveDao;
import com.bosssoft.ecds.entity.dto.BillApplyDTO;
import com.bosssoft.ecds.entity.dto.BillAvailableInfoDTO;
import com.bosssoft.ecds.entity.po.BillAvailableArchivePO;
import com.bosssoft.ecds.service.BillAvailableArchiveService;
import com.bosssoft.ecds.service.WriteoffBillsummaryService;
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

    /**
     * 审验单服务
     */
    @Autowired
    private WriteoffBillsummaryService writeoffBillsummaryService;

    @Autowired
    private BillAvailableArchiveDao billAvailableArchiveDao;

    /**
     * 财政端
     */

    @Override
    public List<BillApplyDTO> getBillApplyInfos() {
        return null;
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

    /**
     * 单位端
     */
    @Override
    public BillApplyDTO getBillApplyInfo(String agenCode) {
        return null;
    }

}
