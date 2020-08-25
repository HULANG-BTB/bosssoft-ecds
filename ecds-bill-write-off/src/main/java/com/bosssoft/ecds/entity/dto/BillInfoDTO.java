package com.bosssoft.ecds.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author hujierong
 * @date 2020-8-19
 */
@Data
@AllArgsConstructor
public class BillInfoDTO {
    /**
     * 票号信息列表
     */
    private List<WriteOffApplyItemDTO> applyItemDTOS;

    /**
     * 项目情况列表
     */
    private List<WriteOffApplyIncomeDTO> applyIncomeDTOS;
}
