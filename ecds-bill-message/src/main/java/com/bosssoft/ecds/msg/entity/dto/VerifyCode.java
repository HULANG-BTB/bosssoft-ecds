package com.bosssoft.ecds.msg.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangxiaohui
 */
@Data
@AllArgsConstructor
public class VerifyCode implements Serializable {
    private String code;
}
