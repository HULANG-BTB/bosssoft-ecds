package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.AgenDto;
import com.bosssoft.ecds.entity.po.AgenPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuke
 * @since 2020-08-12
 */
public interface AgenService extends IService<AgenPO> {
    AgenDto queryAgenInfo(ArchiveOverViewQuery archiveOverViewQuery);
}
