package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.po.BillPayArchivePO;

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
     * 电子票据缴销情况归档
     */
    void finaBillPayArchive();
}
