package com.bosssoft.ecds.util;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.PayerDto;
import com.bosssoft.ecds.entity.dto.UneCbillDto;
import com.bosssoft.ecds.entity.po.UneCbill;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class CommonUtil {
    //大写数字
    private static final String[] NUMBERS = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    // 整数部分的单位
    private static final String[] IUNIT = {"元","拾","佰","仟","万","拾","佰","仟","亿","拾","佰","仟","万","拾","佰","仟"};
    //小数部分的单位
    private static final String[] DUNIT = {"角","分","厘"};

    /**
     * 雪花算法生成ID
     * @return
     */
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
        uneCbill.setFVersion(1);
        uneCbill.setFSignId(generateID());
        uneCbill.setFState(0);
        uneCbill.setFPayCode(generateID());
        uneCbill.setFCheckCode("85c6s");
        return uneCbill;
    }

    /**
     * 将金额转成中文
     * @param str
     * @return
     */
    public static String toChinese(String str) {
        //判断输入的金额字符串是否符合要求
        if (StringUtils.isBlank(str) || !str.matches("(-)?[\\d]*(.)?[\\d]*")) {
            System.out.println("抱歉，请输入数字！");
            return str;
        }

        if("0".equals(str) || "0.00".equals(str) || "0.0".equals(str)) {
            return "零元";
        }

        //判断是否存在负号"-"
        boolean flag = false;
        if(str.startsWith("-")){
            flag = true;
            str = str.replaceAll("-", "");
        }

        str = str.replaceAll(",", "");//去掉","
        String integerStr;//整数部分数字
        String decimalStr;//小数部分数字


        //初始化：分离整数部分和小数部分
        if(str.indexOf(".")>0) {
            integerStr = str.substring(0,str.indexOf("."));
            decimalStr = str.substring(str.indexOf(".")+1);
        }else if(str.indexOf(".")==0) {
            integerStr = "";
            decimalStr = str.substring(1);
        }else {
            integerStr = str;
            decimalStr = "";
        }

        //beyond超出计算能力，直接返回
        if(integerStr.length()>IUNIT.length) {
            System.out.println(str+"：超出计算能力");
            return str;
        }

        int[] integers = toIntArray(integerStr);//整数部分数字
        //判断整数部分是否存在输入012的情况
        if (integers.length>1 && integers[0] == 0) {
            System.out.println("抱歉，请输入数字！");
            if (flag) {
                str = "-"+str;
            }
            return str;
        }
        boolean isWan = isWan5(integerStr);//设置万单位
        int[] decimals = toIntArray(decimalStr);//小数部分数字
        String result = getChineseInteger(integers,isWan)+getChineseDecimal(decimals);//返回最终的大写金额
        if(flag){
            return "负"+result;//如果是负数，加上"负"
        }else{
            return result;
        }
    }

    //将字符串转为int数组
    private static int[] toIntArray(String number) {
        int[] array = new int[number.length()];
        for(int i = 0;i<number.length();i++) {
            array[i] = Integer.parseInt(number.substring(i,i+1));
        }
        return array;
    }
    //将整数部分转为大写的金额
    public static String getChineseInteger(int[] integers,boolean isWan) {
        StringBuffer chineseInteger = new StringBuffer("");
        int length = integers.length;
        if (length == 1 && integers[0] == 0) {
            return "";
        }
        for(int i=0;i<length;i++) {
            String key = "";
            if(integers[i] == 0) {
                if((length - i) == 13)//万（亿）
                    key = IUNIT[4];
                else if((length - i) == 9) {//亿
                    key = IUNIT[8];
                }else if((length - i) == 5 && isWan) {//万
                    key = IUNIT[4];
                }else if((length - i) == 1) {//元
                    key = IUNIT[0];
                }
                if((length - i)>1 && integers[i+1]!=0) {
                    key += NUMBERS[0];
                }
            }
            chineseInteger.append(integers[i]==0?key:(NUMBERS[integers[i]]+IUNIT[length - i -1]));
        }
        return chineseInteger.toString();
    }
    //将小数部分转为大写的金额
    private static String getChineseDecimal(int[] decimals) {
        StringBuffer chineseDecimal = new StringBuffer("");
        for(int i = 0;i<decimals.length;i++) {
            if(i == 3) {
                break;
            }
            chineseDecimal.append(decimals[i]==0?"":(NUMBERS[decimals[i]]+DUNIT[i]));
        }
        return chineseDecimal.toString();
    }
    //判断当前整数部分是否已经是达到【万】
    private static boolean isWan5(String integerStr) {
        int length = integerStr.length();
        if(length > 4) {
            String subInteger = "";
            if(length > 8) {
                subInteger = integerStr.substring(length- 8,length -4);
            }else {
                subInteger = integerStr.substring(0,length - 4);
            }
            return Integer.parseInt(subInteger) > 0;
        }else {
            return false;
        }
    }
}
