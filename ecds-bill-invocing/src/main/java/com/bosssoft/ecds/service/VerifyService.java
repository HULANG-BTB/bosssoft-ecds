package com.bosssoft.ecds.service;

public interface VerifyService {

    /**
     * 判断单位是否欠缴
     * @param unitName
     * @return
     */
    boolean isArrear(String unitName);

    /**
     * 查询单位是否有可用票据，有票据则返回票据信息
     * @return
     */
    String getUnitBill(String unitName);

    /**
     * 判断当前开票单位开票数是否已达到最大开票限制
     * @param unitName
     * @param billCount
     * @return
     */
    boolean isOutLimit(String unitName, int billCount);
}
