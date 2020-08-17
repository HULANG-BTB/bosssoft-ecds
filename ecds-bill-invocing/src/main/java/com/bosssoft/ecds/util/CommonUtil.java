package com.bosssoft.ecds.util;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.PayerDto;
import com.bosssoft.ecds.entity.dto.UneCbillDto;
import com.bosssoft.ecds.entity.po.UneCbill;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class CommonUtil {

    //雪花算法
    public static long generateID() {
        SnowflakeIdWorker sf = new SnowflakeIdWorker();
        long id = sf.nextId();
        return id;
    }

    //UUID
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public UneCbill convert(String unitName, PayerDto payerDto, UneCbillDto uneCbillDto, long billItemId, double amt) {
        //BeanUtil.copyProperties();
        UneCbill uneCbill = new UneCbill();
        //开票主表id
        long cbillId = generateID();
        uneCbill.setFId(cbillId);
        //单位
        uneCbill.setFAgenIdCode(unitName);
        uneCbill.setFPlaceCode("开票点代码");
        uneCbill.setFPlaceId("开票点id");
        uneCbill.setFPlaceName("开票点名称");
        uneCbill.setFRgnCode("区划码");
        uneCbill.setFAuthor("samuel");
        //票据
        uneCbill.setFBillId(uneCbillDto.getfBillId());
        uneCbill.setFBillNo(uneCbillDto.getfBillNo());
        uneCbill.setFType(uneCbillDto.getfType());
        uneCbill.setFDate(new Date());
        uneCbill.setFTypeCode("票据类型编码");
        uneCbill.setFBillBatchCode("票据批次号");
        //缴款人
        uneCbill.setFPayerName(payerDto.getfPayerName());
        uneCbill.setFPayerEmail(payerDto.getfPayerEmail());
        uneCbill.setFPayerTel(payerDto.getfPayerTel());
        uneCbill.setFMemo(payerDto.getfMemo());
        uneCbill.setFPayerType(payerDto.getfPayerType());
        //
        uneCbill.setFTotalAmt(amt);
        uneCbill.setFCreateTime(new Date());
        uneCbill.setFUpdateTime(new Date());
        uneCbill.setFVersion(0);
        uneCbill.setFSignId(generateID());
        uneCbill.setFState(0);
        uneCbill.setFPayCode(generateID());
        uneCbill.setFCheckCode("85c6s");
        return uneCbill;
    }
}
