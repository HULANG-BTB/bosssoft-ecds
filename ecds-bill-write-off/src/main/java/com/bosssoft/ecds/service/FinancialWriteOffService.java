package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.WriteOffApplyItemDTO;
import com.bosssoft.ecds.entity.dto.WriteOffDetailDTO;
import com.bosssoft.ecds.entity.dto.WriteOffReceiveDTO;
import com.bosssoft.ecds.entity.dto.WriteOffResultDTO;
import com.bosssoft.ecds.entity.po.WriteOffApplyItemPO;

import java.util.List;

public interface FinancialWriteOffService {

    /***
     * 通过单位编码
     * 获取单位申报的核销信息
     * @param fAgenIdCode
     * @return java.util.List
     */
    List<WriteOffReceiveDTO> receive(String fAgenIdCode);

    // 将核销申请退回单位端
    boolean sendBack(List<WriteOffReceiveDTO> list);

    // 存入审验结果
    boolean setResult(WriteOffDetailDTO writeOffDetailDTO, WriteOffResultDTO writeOffResultDTO);

    // 获取核销申请详细信息
    WriteOffDetailDTO getDetail(String fPid);

}
