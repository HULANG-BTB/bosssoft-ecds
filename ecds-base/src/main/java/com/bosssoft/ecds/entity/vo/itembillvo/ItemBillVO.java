package com.bosssoft.ecds.entity.vo.itembillvo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;


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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 票据种类编码
     */
    private String billCode;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 项目id
     * 在项目表中是 f_item_id
     * 在关系表中是f_item_id_code
     */
    private String itemId;
    /**
     * 项目表中的项目名称
     */
    private String itemName;
    /**
     * 关系是否启用
     */
    private Boolean enabled;
    /**
     * 项目是否启用
     */
    private Boolean itemIsEnabled;
    /**
     *  关系创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 关系修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
