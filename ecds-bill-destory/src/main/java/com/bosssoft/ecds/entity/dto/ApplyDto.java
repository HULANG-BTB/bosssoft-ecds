package com.bosssoft.ecds.entity.dto;

import com.bosssoft.ecds.entity.po.ItemPo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: qiuheng
 * @create: 2020-08-12 17:06
 **/
public class ApplyDto {
    /**
     * 单号
     */
    private String fDestroyNo;

    /**
     * 单位识别码
     */
    private String fAgenIdCode;

    /**
     * 销毁类型：0.核销票据销毁；1.库存票据销毁
     */
    private Boolean fDestroyType;

    /**
     * 备注
     */
    private String fDestroyMemo;

    List<ItemDto> itemDtoList;

    /**
     * 申请人
     */
    private String fApplyAuthor;

    /**
     * 编制日期
     */
    private LocalDateTime fDate;

    /**
     * 审核状态：0.未审核；1.已审核
     */
    private Boolean fStatus;

}
