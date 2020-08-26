package com.bosssoft.ecds.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/10
 * @Content: 摘要函数与加密算法类型
 */
@ApiModel("算法与编码类型枚举类")
public enum AlgorithmType implements Serializable {
    /**
     * 使用SHA1摘要函数，使用RSA加密
     */
    @ApiModelProperty("使用SHA1摘要函数，使用RSA加密")
    SHA1("SHA1WithRSA","SHA-1"),
    /**
     * 使用SHA256摘要函数，使用RSA加密
     */
    @ApiModelProperty("使用SHA256摘要函数，使用RSA加密")
    SHA256("SHA256withRSA","SHA-256"),
    /**
     * 使用SHA384摘要函数，使用RSA加密
     */
    @ApiModelProperty("使用SHA384摘要函数，使用RSA加密")
    SHA384("SHA384WithRSA","SHA-384"),
    /**
     * 使用MD5摘要函数，使用RSA加密
     */
    @ApiModelProperty("使用MD5摘要函数，使用RSA加密")
    MD5("MD5withRSA","MD5");

    /**
     * 签名类的算法类型
     */
    private final String signatureAlgorithmType;

    /**
     * 摘要函数的算法类型
     */
    private final String summaryAlgorithType;

    AlgorithmType(String signatureAlgorithmType,String summaryAlgorithType){
        this.signatureAlgorithmType = signatureAlgorithmType;
        this.summaryAlgorithType = summaryAlgorithType;
    }

    public String getsignatureAlgorithmType(){
        return this.signatureAlgorithmType;
    }

    public String getsummaryAlgorithType(){
        return this.summaryAlgorithType;
    }
}
