package com.bosssoft.ecds.encodeserver.service;


import com.bosssoft.ecds.encodeserver.entity.dto.NumSegDto;
import com.bosssoft.ecds.encodeserver.entity.dto.GetBillNumDto;

/**
 * @Author 黄杰峰
 * @Date 2020/8/6 0006 11:25
 * @Description Redis-cluster Implement Global Only, One-step Increment Code Function
 *              Redis集群实现生成全局唯一，单步递增赋码的功能
 *
 *     赋码功能主要实现两部分：
 *      1. 申领发放时赋码（批量发放）
 *          * 申领发放时，
 *      2. 自动发放时赋码（单张发放）
 */
public interface GetCodeService {
    /**
     * 赋码 - 单个票号
     * @param getBillNumDto
     * @return 单个票号
     */
    long getSingleCode(GetBillNumDto getBillNumDto);

    /**
     * 赋码 - 票号段
     * @param getBillNumDto
     * @return 票号段实体类
     */
    NumSegDto getBatchCode(GetBillNumDto getBillNumDto);

}
