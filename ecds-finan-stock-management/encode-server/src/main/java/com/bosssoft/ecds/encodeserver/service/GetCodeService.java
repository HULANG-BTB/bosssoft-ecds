package com.bosssoft.ecds.encodeserver.service;


import com.bosssoft.ecds.encodeserver.entity.dto.NumSegDto;
import com.bosssoft.ecds.encodeserver.entity.dto.CreateFinanceCodeDto;
import com.bosssoft.ecds.encodeserver.entity.dto.GetFinanceNumDto;

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
     * 赋码 - 单张发放
     * @param getFinanceNumDTO
     * @return 单个票号
     */
    long getSingleCode(GetFinanceNumDto getFinanceNumDTO);

    /**
     * 赋码 - 批量发放
     * @param getFinanceNumDTO
     * @return 票号段实体类
     */
    NumSegDto getBatchCode(GetFinanceNumDto getFinanceNumDTO);

    /**
     * 若是一段从未使用的编码，则需要进行创建，需要操作人ID和名称
     * @param createFinanceCodeDTO
     * @return
     */
    boolean createNewCode(CreateFinanceCodeDto createFinanceCodeDTO);
}
