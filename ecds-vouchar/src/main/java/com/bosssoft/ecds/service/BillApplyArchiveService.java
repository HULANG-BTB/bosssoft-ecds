package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.BillApplyDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.BillApplyArchivePO;
import com.bosssoft.ecds.entity.query.CommonQuery;

/**
 * <p>
 *  服务类
 *  归档领用
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface BillApplyArchiveService extends IService<BillApplyArchivePO> {

    /**
     * 根据单位编码获取单位票据申领的情况
     * query 分页对象
     *
     * @param query
     * @return BillApplyDto
     */
    PageDTO<BillApplyDTO> getBillApplyInfo(CommonQuery query);


    /**
     * 财政端电子票据申领情况归档
     */
    void finaBillApplyArchive();


}
