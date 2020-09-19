package com.bosssoft.ecds.pay.controller;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.bosssoft.ecds.pay.domain.dto.*;
import com.bosssoft.ecds.pay.domain.vo.*;
import com.bosssoft.ecds.pay.handle.CommonCodeHandle;
import com.bosssoft.ecds.pay.service.EcdsBillAccountingService;
import com.bosssoft.ecds.pay.service.EcdsBillInvocingService;
import com.bosssoft.ecds.pay.utils.QrCodeUtil;
import com.bosssoft.ecds.pay.utils.RedisUtil;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.google.zxing.WriterException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PaymentController
 * @Description 缴费系统管理类
 * @auther wangpeng
 * @Date 2020/8/20 9:08
 * @Version 1.0
 **/
@Slf4j
@RestController
@RefreshScope
public class PaymentController {


    @Autowired
    RedisUtil redisUtil;

    @Value("${success}")
    String url;

    @Autowired
    EcdsBillInvocingService ecdsBillInvocingService;

    @Autowired
    EcdsBillAccountingService ecdsBillAccountingService;

    /**
     * @Author wangpeng
     * @Description 缴费系统登陆
     * @Date 9:10 2020/8/20
     * @Param payLoginDTO 缴费登录数据DTO
     * @return QueryResponseResult
     **/
    @ApiOperation("登陆缴费系统返回数据方法")
    @PostMapping("payLogin")
    public QueryResponseResult payLogin(@RequestBody PayLoginDto payLoginDto){

        log.info("校验码"+payLoginDto.getCheckCode()+"\t手机号"+payLoginDto.getFPayerTel());
        PayVo payVo = new PayVo();
        WaitAccountVO waitAccountVO = new WaitAccountVO();
        WaitAccountVO waitAccountGetVO = new WaitAccountVO();
        waitAccountVO.setBillSerialId(payLoginDto.getCheckCode());
        waitAccountGetVO = Convert.convert(WaitAccountVO.class,(ecdsBillAccountingService.getWaitAccount(waitAccountVO).data));
        if(waitAccountGetVO == null){
            return new QueryResponseResult(CommonCodeHandle.Data_FAILED,"财政端数据查询失败");
        }
        log.info(waitAccountGetVO.toString());
        if (waitAccountGetVO.getAccountExist() == false) {
            return new QueryResponseResult(CommonCodeHandle.LOGIN_FAIL,"数据不存在");
        } else {
            PayDto payDto = new PayDto();
            if (waitAccountGetVO.getAccountStatus() == true){
                payVo.setType(1);
            } else {
                payVo.setType(0);
            }
            payDto = Convert.convert(PayDto.class,(ecdsBillInvocingService.getItems(waitAccountGetVO.getPayerTel(),waitAccountGetVO.getBillSerialId())).data);
            Integer code = Convert.convert(Integer.class,(ecdsBillInvocingService.getItems(waitAccountGetVO.getPayerTel(),waitAccountGetVO.getBillSerialId())).getCode());
            if(code != 10000){
               return new QueryResponseResult(CommonCodeHandle.Data_FAILED,"单位端数据查询为空");
            }
            BigDecimal bigDecimal = new BigDecimal("0");
            bigDecimal = waitAccountGetVO.getWaitAccount();
            payVo.setPayDto(payDto);
            payVo.setCheckCode(payLoginDto.getCheckCode());
            payVo.setFPayerTel(payLoginDto.getFPayerTel());
            payVo.setTotal(payDto.getUneCbillItems().size());
            payVo.setMoney(bigDecimal);
            return new QueryResponseResult(CommonCode.SUCCESS,payVo);
        }
    }

    /**
     * @Author wangpeng
     * @Description 获取支付二维码
     * @Date 9:11 2020/8/20
     * @Param []
     * @return QueryResponseResult
     **/
    @ApiOperation("获取支付二维码方法")
    @PostMapping("getQrCode")
    public QueryResponseResult getQrCode() throws IOException, WriterException {

        QrCodeVo qrCodeVo = new QrCodeVo();
        String uuid = "" + UUID.randomUUID();
        qrCodeVo.setUuid(uuid);
        String loginUrl = url + uuid;
        String image = QrCodeUtil.createQrCode(loginUrl);
        qrCodeVo.setImage(image);
        return new QueryResponseResult(CommonCode.SUCCESS,qrCodeVo);
    }

    /**
     * @Author wangpeng
     * @Description 缴费信息同步到财政的入账明细表
     * @Date 9:11 2020/8/20
     * @Param AccIntoInfoVO
     * @return ResponseResult
     **/
    @ApiOperation("插入入账明细表的方法")
    @PostMapping("addAccIntoInfoDto")
    public ResponseResult addAccIntoInfo(@RequestBody AccIntoInfoVO accIntoInfoVO) throws ParseException {
        log.info(accIntoInfoVO.toString());
        log.info(accIntoInfoVO.getTime().toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = df.format(new Date());
        log.info(DateUtil.parse(formatDate).toString());
        accIntoInfoVO.setTime(DateUtil.parse(formatDate));
        log.info(accIntoInfoVO.toString());
        Integer code = Convert.convert(Integer.class,(ecdsBillAccountingService.updateAccount((accIntoInfoVO))).getCode());
        if(code == 10000){
            return new ResponseResult(CommonCode.SUCCESS);
        }else{
            log.info(code.toString());
            return new ResponseResult(CommonCodeHandle.PAY_FAIL);
        }
    }

    /**
     * @Author wangpeng
     * @Description 将用户信息暂存在redis
     * @Date 9:11 2020/8/20
     * @Param UUidDto 封装标识用户的唯一id
     * @return
     **/
    @ApiOperation("手机端支付成功将用户信息插入到redis暂存")
    @GetMapping("setUUid")
    public QueryResponseResult setUUid (String UUid){
        log.info(UUid);
        if(UUid != null && !UUid.equals("")){
            log.info(UUid);
            redisUtil.addKey(UUid,UUid,10, TimeUnit.MINUTES);
            return new QueryResponseResult(CommonCode.SUCCESS,"支付成功");
        }else{
            return new QueryResponseResult(CommonCodeHandle.PAY_FAIL,"支付失败");
        }
    }

    /**
     * @Author wangpeng
     * @Description 前端轮询访问的函数 用于获取用户信息是否存储在redis
     * @Date 9:11 2020/8/20
     * @Param UUidVo 封装用于验证的指定用户的UUidVo
     * @return ResponseResult
     **/
    @ApiOperation("获取redis中指定用户的UUid")
    @PostMapping("getUUid")
    public QueryResponseResult getUUid(@RequestBody UUidVo uUidVo){
        String value = (String) redisUtil.getValue(uUidVo.getUUid());
        if (redisUtil.getValue(uUidVo.getUUid())!=null){
            return new QueryResponseResult(CommonCode.SUCCESS,"YES");
        }else{
            return new QueryResponseResult(CommonCode.SUCCESS,"NO");
        }
    }
}

