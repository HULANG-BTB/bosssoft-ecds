package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.BillWarnDTO;
import com.bosssoft.ecds.entity.po.BillWarnArchivePO;
import com.bosssoft.ecds.entity.query.CommonQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 *  归档预警
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface BillWarnArchiveService extends IService<BillWarnArchivePO> {

    /**
     * 获取单位缴款信息情况
     *
     * @param query
     * @return List<BillWarnDTO>
     */
    List<BillWarnDTO> getBillWarnInfos(CommonQuery query);

    /**
     * 预警电子票据归档信息
     */
    void finaBillWarnArchive();
}
