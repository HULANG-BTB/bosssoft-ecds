package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.BillApplyDTO;
import com.bosssoft.ecds.entity.po.BillApplyArchivePO;

import java.util.List;

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
     * 财政端
     */

    /**
     * 获取所有单位的票据申领情况
     *
     * @return List<BillApplyDto>
     */
    List<BillApplyDTO> getBillApplyInfos();

    /**
     * 获取全部申请的票据数量
     *
     * @return 全部申请的票据数量
     */
    Long queryBillApplyAllNumber();

    /**
     * 财政端电子票据申领情况归档
     */
    void finaBillApplyArchive();

    /**
     * 单位端
     */

    /**
     * 根据单位编码获取单位票据申领的情况
     *
     * @param agenCode
     * @return BillApplyDto
     */
    BillApplyDTO getBillApplyInfo(String agenCode);

    /**
     * 根据单位编码查询申请数量
     *
     * @param agenCode
     * @return 票据申请数量
     */
    Long queryBillApplyNumber(String agenCode);
}
