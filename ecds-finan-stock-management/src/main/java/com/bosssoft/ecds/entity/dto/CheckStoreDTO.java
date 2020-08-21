package com.bosssoft.ecds.entity.dto;

import lombok.Data;

/**
 * 检查票据段是否可用
 *
 * @author cheng
 * @Date 2020/8/13 16:11
 */
@Data
public class CheckStoreDTO {
    private String billCode;
    private Long start;
    private Long end;
}
