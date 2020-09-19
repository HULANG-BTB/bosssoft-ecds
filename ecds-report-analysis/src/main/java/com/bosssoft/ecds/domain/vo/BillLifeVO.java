package com.bosssoft.ecds.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author syf
 * @Date 2020/8/18 16:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillLifeVO {

    private String billCode;

    private Date stockInTime;

    private Date stockOutTime;

    private Date stockBillTime;

    private Date stockDestroyTime;

}
