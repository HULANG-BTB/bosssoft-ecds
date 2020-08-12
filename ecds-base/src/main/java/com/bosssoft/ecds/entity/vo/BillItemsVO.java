package com.bosssoft.ecds.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName :  BillItemsVO
 * @Description : 批量添加票据与项目的关联
 * @Author : wuliming
 * @Date: 2020-08-10 22:30
 */
@Data
public class BillItemsVO {
    private String billCode;
    private List<String> itemIds;
}
