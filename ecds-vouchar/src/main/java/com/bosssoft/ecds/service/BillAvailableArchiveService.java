package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.BillAvailableInfoDTO;
import com.bosssoft.ecds.entity.po.BillAvailableArchivePO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuke
 * @since 2020-08-13
 */
public interface BillAvailableArchiveService extends IService<BillAvailableArchivePO> {

    /**
     * 获取所有单位的票据申领情况
     *
     * @param agenCode
     * @return List<BillApplyDto>
     */
    List<BillAvailableInfoDTO> getBillApplyInfos(String agenCode);

    /**
     * 财政端电子票据申领情况归档
     */
    void finaBillApplyArchive();

}
