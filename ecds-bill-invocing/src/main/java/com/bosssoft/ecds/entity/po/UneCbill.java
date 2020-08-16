package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("une_cbill")
public class UneCbill {

    /**
     * 开票主表
     */
    @TableId(value = "f_id")
    private long fId;

    /**
     * 区划ID
     */
    private String fRgnCode;

    /**
     * 单位编码
     */
    private String fAgenIdCode;

    /**
     * 开票点ID
     */
    private String fPlaceId;

    /**
     * 开票点编码
     */
    private String fPlaceCode;

    /**
     * 开票点名称
     */
    private String fPlaceName;

    /**
     * 缴款人姓名
     */
    private String fPayerName;

    /**
     * 缴款人电话
     */
    private String fPayerTel;

    /**
     * 缴款人邮箱
     */
    private String fPayerEmail;

    /**
     * 开票日期
     */
    private Date fDate;

    /**
     * 编制人
     */
    private String fAuthor;

    /**
     * 票据类型
     */
    private String fType;

    /**
     * 票据类型编码
     */
    private String fTypeCode;

    /**
     * 票据编码
     */
    private String fBillId;

    /**
     * 票据批次号
     */
    private String fBillBatchCode;

    /**
     * 票据号码
     */
    private String fBillNo;

    /**
     * 开票备注
     */
    private String fMemo;

    /**
     * 校验码
     */
    private String fCheckCode;

    /**
     * 开票金额
     */
    private double fTotalAmt;

    /**
     * 票据状态（1:开票请求,2：单位端审核,3：财政端审核,4：开票成功/开票失败）
     */
    private int fState;

    private int fVersion;

    /**
     * 创建时间
     */
    private Date fCreateTime;

    /**
     * 修改时间
     */
    private Date fUpdateTime;

    /**
     * 票据签名记录ID
     */
    private long fSignId;

    /**
     *
     */
    private long fPayCode;

    /**
     * 缴款类型
     */
    private int fPayerType;

}
