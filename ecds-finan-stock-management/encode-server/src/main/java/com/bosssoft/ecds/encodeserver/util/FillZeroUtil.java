package com.bosssoft.ecds.encodeserver.util;

import com.bosssoft.ecds.encodeserver.entity.dto.CreateFinanceCodeDto;
import com.bosssoft.ecds.encodeserver.entity.dto.GetFinanceNumDto;

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

    public static void fillZero(GetFinanceNumDto getFinanceNumDto) {
        getFinanceNumDto.setFRegiId(fillBefore(getFinanceNumDto.getFRegiId()));
        getFinanceNumDto.setFSortId(fillBefore(getFinanceNumDto.getFSortId()));
        getFinanceNumDto.setFTypeId(fillBefore(getFinanceNumDto.getFTypeId()));
        getFinanceNumDto.setFAnnualId(fillBefore(getFinanceNumDto.getFAnnualId()));
    }

    public static void fillZero(CreateFinanceCodeDto createFinanceCodeDto) {
        createFinanceCodeDto.setFRegiId(fillBefore(createFinanceCodeDto.getFRegiId()));
        createFinanceCodeDto.setFSortId(fillBefore(createFinanceCodeDto.getFSortId()));
        createFinanceCodeDto.setFTypeId(fillBefore(createFinanceCodeDto.getFTypeId()));
        createFinanceCodeDto.setFAnnualId(fillBefore(createFinanceCodeDto.getFAnnualId()));
    }
}
