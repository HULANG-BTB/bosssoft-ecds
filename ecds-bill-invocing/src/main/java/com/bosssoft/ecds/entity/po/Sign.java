package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("une_cbill_sign")
public class Sign {

    /**
     * 签名表ID
     */
    private long signId;

    /**
     * 签名dto的String
     */
    private String signDto;

    /**
     *签名
     */
    private String fSignature;

    /**
     * 签名验证code
     */
    private String fSecCode;

}
