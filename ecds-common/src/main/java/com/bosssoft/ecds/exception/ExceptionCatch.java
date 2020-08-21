package com.bosssoft.ecds.exception;

import com.alibaba.fastjson.JSON;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.response.ResultCode;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 统一异常捕获类，其他模块想要扩充可以自行继承此类进行扩充，扩充的子类只需要创建
 * static代码块并使用builder.put()添加需要捕获的异常和对应的响应数据类型，同时也
 * 可以自行定义捕获异常逻辑，自定义异常捕获逻辑中需要添加日志打印，但推荐使用builder
 * 进行扩充，方便快捷，只需定义对应的{@link ResultCode}即可，同时注意子类也需要
 * 加{@link ControllerAdvice}注解
 */
@ControllerAdvice
@Slf4j(topic = "kafka_logger")
public class ExceptionCatch {

    private ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;

    public static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = ImmutableMap.builder();

    /**
     * 处理未知异常
     * @param e 异常信息
     * @return 统一响应数据格式
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult systemException(Exception e){
        if(EXCEPTIONS == null){
           EXCEPTIONS = builder.build();
        }
        ResultCode resultCode = EXCEPTIONS.get(e.getClass());
        ExceptionDetail detail = new ExceptionDetail();
        detail.setTags(e.getClass().getSimpleName());
        if(resultCode != null){
            detail.setCode(resultCode.code());
            detail.setMessage(resultCode.message());
            log.error(JSON.toJSONString(detail));
            return new ResponseResult(resultCode);
        }
        detail.setCode(CommonCode.EMAIL_NOTIFICATION.code());
        detail.setMessage("Not captured exception:"+e.getClass().getSimpleName()+",message:"+e.getMessage()+",time:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        log.error(JSON.toJSONString(detail));
        e.printStackTrace();
        return new ResponseResult(CommonCode.SERVER_ERROR);
    }

    /**
     * 处理请求参数校验异常
     * @param e 异常信息
     * @return 统一响应数据格式
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult handleError(MethodArgumentNotValidException e) {
        log.error("参数验证错误", e);
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResponseResult(false,1004, objectError.getDefaultMessage());
    }

    /**
     * 处理自定义业务异常
     * @param e 异常信息
     * @return 统一响应数据格式
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException e){
        // 定义返回信息
        ResponseResult responseResult = new ResponseResult(e.getResultCode());
        // 异常名称
        String exceptionType = CustomException.class.getSimpleName();
        // 要写入日志的信息
        String errMsg = e.getErrorMsg();
        // 创建异常的详细信息,message不传则采用resultCode中的message
        ExceptionDetail exceptionDetail = new ExceptionDetail(e.getResultCode(),errMsg,exceptionType);
        // 将异常信息以JSON格式打印到日志
        String message = JSON.toJSONString(exceptionDetail);
        log.error(message);
        return responseResult;
    }

    static{
        //处理非法参数异常
        builder.put(org.springframework.http.converter.HttpMessageNotReadableException.class, CommonCode.INVLIDATE);
        //处理请求方法异常
        builder.put(HttpRequestMethodNotSupportedException.class,CommonCode.METHOD_NOT_SUPPORTED);
        //处理重复插入数据异常
        builder.put(DuplicateKeyException.class, CommonCode.DUPLICATE_ERROR);
        //除了CustomException以外的异常类型及对应的错误代码在这里定义,，如果不定义则统一返回固定的错误信息
    }

}
