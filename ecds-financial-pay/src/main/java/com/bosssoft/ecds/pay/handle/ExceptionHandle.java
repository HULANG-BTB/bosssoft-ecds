package com.bosssoft.ecds.pay.handle;

import com.bosssoft.ecds.exception.ExceptionCatch;
import com.google.zxing.WriterException;
import com.netflix.client.ClientException;
import feign.RetryableException;
import io.lettuce.core.RedisException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;
import java.text.ParseException;

/**
 * @ClassName ExceptionHandle
 * @Description 异常处理
 * @auther wangpeng
 * @Date 2020/8/21 14:03
 * @Version 1.0
 **/
@ControllerAdvice
public class ExceptionHandle extends ExceptionCatch {
    static{
        //二维码生成失败
        builder.put(IOException.class,CommonCodeHandle.QrCode_FAIL);
        builder.put(WriterException.class,CommonCodeHandle.QrCode_FAIL);
        builder.put(ParseException.class,CommonCodeHandle.Date_FAILED);
        builder.put(RetryableException.class,CommonCodeHandle.Data_FAILED);
        builder.put(RuntimeException.class,CommonCodeHandle.Data_FAILED);
        builder.put(RedisException.class,CommonCodeHandle.Data_FAILED);
    }
}
