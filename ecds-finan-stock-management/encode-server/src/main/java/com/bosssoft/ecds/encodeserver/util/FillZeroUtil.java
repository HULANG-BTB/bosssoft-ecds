package com.bosssoft.ecds.encodeserver.util;

import com.bosssoft.ecds.encodeserver.entity.dto.CreateBatchBillCodeDto;
import com.bosssoft.ecds.encodeserver.entity.dto.CreateBillCodeDto;
import com.bosssoft.ecds.encodeserver.entity.dto.GetBillNumDto;

import java.text.DecimalFormat;

/**
 * @Author 黄杰峰
 * @Date 2020/8/17 0017 15:17
 * @Description 填充0工具类
 */
public class FillZeroUtil {
    public static String fillBefore(String numStr) {
        // 规定为2位数
        DecimalFormat df = new DecimalFormat("00");

        if (numStr.length() == 1) {
            return df.format(Integer.valueOf(numStr));
        } else {
            return numStr;
        }
    }

    public static void fillZero(GetBillNumDto getBillNumDto) {
        getBillNumDto.setFRegiId(fillBefore(getBillNumDto.getFRegiId()));
        getBillNumDto.setFSortId(fillBefore(getBillNumDto.getFSortId()));
        getBillNumDto.setFTypeId(fillBefore(getBillNumDto.getFTypeId()));
        getBillNumDto.setFAnnualId(fillBefore(getBillNumDto.getFAnnualId()));
    }

    public static void fillZero(CreateBillCodeDto createBillCodeDto) {
        createBillCodeDto.setFRegiId(fillBefore(createBillCodeDto.getFRegiId()));
        createBillCodeDto.setFSortId(fillBefore(createBillCodeDto.getFSortId()));
        createBillCodeDto.setFTypeId(fillBefore(createBillCodeDto.getFTypeId()));
        createBillCodeDto.setFAnnualId(fillBefore(createBillCodeDto.getFAnnualId()));
    }
}
