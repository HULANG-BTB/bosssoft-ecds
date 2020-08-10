package com.bosssoft.ecds.entity.vo;

import lombok.Data;

/**
 * @ClassName :  ItemBillVo
 * @Description : 票据种类关系
 * @Author : wuliming
 * @Date: 2020-08-10 09:38
 */
@Data
public class ItemBillVO {

    /**
     * 关联表主键
     */
    Long id;
    /**
     * 操作人
     */
    String operator;
    /**
     *  项目id
     *  在项目表中是 f_item_id
     *  在关系表中是f_item_id_code
     */
    String itemId;
    /**
     *  项目表中的项目名称
     */
    String itemName;

    /**
     *  关系是否启用
     */
    boolean enabled;
    /**
     * 项目是否启用
     */
    boolean itemIsEnabled;


}
