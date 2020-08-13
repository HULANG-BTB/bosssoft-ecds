package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.BillDestructionDto;
import com.bosssoft.ecds.entity.po.BillDestructionArchivePO;

import java.util.List;

/**
 * <p>
 *  服务类
 *  归档票据销毁
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface BillDestructionArchiveService extends IService<BillDestructionArchivePO> {
    /**
     * 根据单位编码获取单位的退票信息
     * @param agenIdCode
     * @return List<BillDestructionDto>
     */
    List<BillDestructionDto> queryBillDestruction(String agenIdCode);
}
