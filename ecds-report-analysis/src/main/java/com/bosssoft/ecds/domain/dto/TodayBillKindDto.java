package com.bosssoft.ecds.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 每日票据种类信息类
 * @author LiDaShan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodayBillKindDto implements Serializable {

    /**
     * 票据名
     */
    private String billname;

    /**
     * 票据数量
     */
    private int billnum;
}
