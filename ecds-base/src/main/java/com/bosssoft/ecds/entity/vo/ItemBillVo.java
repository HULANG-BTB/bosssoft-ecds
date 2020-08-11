package com.bosssoft.ecds.entity.vo;

import lombok.Data;

/**
 * @ClassName :  ItemBillVo
 * @Description : 票据种类关系
 * @Author : wuliming
 * @Date: 2020-08-10 09:38
 */
@Data
public class ItemBillVo {

    /**
     *  项目id
     *  在项目表中是 f_item_id
     *  在关系表中是f_item_id_code
     */
    String FItemId;
    /**
     *  项目表中的项目名称
     */
    String FItemName;

    /**
     *  关系表中的是否启用
     */
    boolean FIsEnabled;


}
