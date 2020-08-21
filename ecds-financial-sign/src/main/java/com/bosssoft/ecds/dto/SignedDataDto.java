package com.bosssoft.ecds.dto;


import com.bosssoft.ecds.domain.StringType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.cert.X509Certificate;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/12
 * @Content:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Api("签名后票据传输dto，包含票据信息，签名和数字证书")
public class SignedDataDto {

    /**
     * 数据信息
     */
    @ApiModelProperty("数据信息")
    private String data;

    /**
     * 单位签名
     */
    @ApiModelProperty("单位签名")
    private String unitSignValue;

    /**
     * 单位数字证书
     */
    @ApiModelProperty("单位数字证书")
    private String unitCrtCert;

    /**
     * 财政签名
     */
    @ApiModelProperty("财政签名")
    private String financeSignValue;

    /**
     * 财政数字证书
     */
    @ApiModelProperty("财政数字证书")
    private String finaCrtCert;

    /**
     * 编码类型
     */
    @ApiModelProperty("编码类型")
    private StringType stringType;

}
