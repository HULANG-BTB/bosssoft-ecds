package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.BillCheckDTO;
import com.bosssoft.ecds.entity.po.BillCheckArchivePO;

import java.util.List;

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
     * 财政端
     */

    /**
     * 获取所有单位的票据申领情况
     *
     * @return List<BillApplyDto>
     */
    List<BillCheckDTO> getBillCheckInfos();

    /**
     * 票据检查结果审验详细信息   票据归档总表  已使用 ，已审核 ， 未查验 数据更新
     */
    void finaBillCheckArchive();

    /**
     * 单位端
     */

    /**
     * 查询各单位下的票据的审验信息
     *
     * @param agenIdCode
     * @return List<BillCheckDto>
     */
    List<BillCheckDTO> getBillCheckInfo(String agenIdCode);

    /**
     * 单位端票据申领情况归档
     */
    void unitBillCheckArchive();


}
