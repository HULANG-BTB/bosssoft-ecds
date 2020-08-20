package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.BillPayDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.BillPayArchivePO;
import com.bosssoft.ecds.entity.query.CommonQuery;

/**
 * <p>
 * 服务类
 * 票据缴销
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface BillPayArchiveService extends IService<BillPayArchivePO> {
    /**
     * 获取单位缴款信息情况
     *
     * @param query
     * @return List<BillPayDTO>
     */
    PageDTO<BillPayDTO> getBillPayInfos(CommonQuery query);

    /**
     * 电子票据缴销情况归档
     */
    void finaBillPayArchive();
}
