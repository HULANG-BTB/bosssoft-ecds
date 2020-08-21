package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bosssoft.ecds.entity.dto.StringType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("une_cbill_sign")
public class Sign {

    /**
     * 签名表ID
     */
    @TableId(value = "sign_id")
    private long signId;

    /**
     * 签名dto的String
     */
    private String data;

    /**
     *签名
     */
    private String unitSignValue;

    /**
     * 单位端证书
     */
    private String unitCrtCert;

    /**
     * 签名验证code Unknown column 'finance_sign_value' in 'field list'
     */
    private String financeSignValue;

    /**
     * 财政数字证书
     */
    private String finaCrtCert;

    /**
     * 编码类型
     */
    private StringType stringType;
}
