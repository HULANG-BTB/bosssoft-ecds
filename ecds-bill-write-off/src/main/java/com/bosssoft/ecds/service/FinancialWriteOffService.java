package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.WriteOffReceiveDTO;
import com.bosssoft.ecds.entity.dto.WriteOffResultDTO;

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

    // 获取核销申请的详情
    Object getDetails(Object object);

    // 存入审验结果
    boolean setResult(WriteOffResultDTO writeOffResultDTO);

}
