package com.boss.msg.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangxiaohui
 * @create 2020/8/12 11:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillVo {
    /*开票日期、票据名称、票据代码8位、票据号码10位、校验码6位、开票单位、缴款人、金额合计*/

    private Date billDate;
    private String billType;
    private String billPreCode;
    private String billCode;
    private String billVerifyCode;
    private String billDep;
    private String payer;
    private BigDecimal billAmount;

}
