package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.SignedDataDto;
import com.bosssoft.ecds.entity.po.Sign;

public interface SignService {
    /**
     * 获取签名服务器所需的dto
     * @param billId
     * @param billNo
     * @return
     */
    SignedDataDto getSignData(String billId, String billNo);

    /**
     * 新增签名
     * @param sign
     * @return
     */
    int addSign(Sign sign);
}
