package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.CreateBatchBillCodeDto;
import com.bosssoft.ecds.entity.dto.CreateBillCodeDto;

/**
 * @Author 黄杰峰
 * @Date 2020/8/18 0018 8:58
 * @Description
 */
public interface CreateCodeService {

    /**
     * 若是一段从未使用的编码，则需要进行创建，需要操作人ID和名称
     *  创建单个编码
     * @param createBillCodeDto
     * @return
     */
    boolean createSingleCode(CreateBillCodeDto createBillCodeDto);

    /**
     * 批量创建编码
     * @param createBatchBillCodeDto
     * @return
     */
    boolean createBatchCode(CreateBatchBillCodeDto createBatchBillCodeDto);
}
