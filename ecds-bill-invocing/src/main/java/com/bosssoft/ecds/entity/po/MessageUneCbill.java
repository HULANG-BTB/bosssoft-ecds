package com.bosssoft.ecds.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MessageUneCbill extends UneCbill{
    /**
     * 票据对应的模板地址
     */
    private String imgUrl;
}
