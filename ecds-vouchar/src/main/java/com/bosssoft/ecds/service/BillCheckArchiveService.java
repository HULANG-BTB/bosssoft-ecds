package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.BillCheckDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.BillCheckArchivePO;
import com.bosssoft.ecds.entity.query.CommonQuery;

/**
 * <p>
 * 服务类  审验记录
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface BillCheckArchiveService extends IService<BillCheckArchivePO> {

    /**
     * 获取单位的票据申领情况
     *
     * @param query
     * @return List<BillApplyDto>
     */
    PageDTO<BillCheckDTO> getBillCheckInfos(CommonQuery query);

    /**
     * 票据检查结果审验详细信息   票据归档总表  已使用 ，已审核 ， 未查验 数据更新
     */
    void finaBillCheckArchive();

}
