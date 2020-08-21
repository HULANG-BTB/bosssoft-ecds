package com.bosssoft.ecds.common.exception;

import com.alibaba.fastjson.JSON;
import com.bosssoft.ecds.common.CommonCode;
import com.bosssoft.ecds.common.ResultCode;
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

@ControllerAdvice
@Slf4j
public class ExceptionCatch {

    /**
     * 构建ImmutableMap
     */
    public static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();

    static {
        //处理非法参数异常
        builder.put(org.springframework.http.converter.HttpMessageNotReadableException.class, CommonCode.INVLIDATE);
        //处理请求方法异常
        builder.put(HttpRequestMethodNotSupportedException.class, CommonCode.METHOD_NOT_SUPPORTED);
        //处理重复插入数据异常
        builder.put(DuplicateKeyException.class, CommonCode.DUPLICATE_ERROR);
        //除了CustomException以外的异常类型及对应的错误代码在这里定义,，如果不定义则统一返回固定的错误信息
    }

    /**
     * 定义map，存贮常见错误信息。该类map不可修改
     */
    private ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;

    /**
     * 处理未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult systemException(Exception e) {
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }
        ResultCode resultCode = EXCEPTIONS.get(e.getClass());
        ExceptionDetail detail = new ExceptionDetail();
        detail.setTags(e.getClass().getSimpleName());
        if (resultCode != null) {
            detail.setCode(resultCode.code());
            detail.setMessage(resultCode.message());
            log.error(JSON.toJSONString(detail));
            return new ResponseResult(resultCode);
        }
        detail.setCode(CommonCode.EMAIL_NOTIFICATION.code());
        detail.setMessage("Not captured exception:" + e.getClass().getSimpleName() + ",message:" + e.getMessage() + ",time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        log.error(JSON.toJSONString(detail));
        e.printStackTrace();
        return new ResponseResult(CommonCode.SERVER_ERROR);
    }

    /**
     * 处理请求参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult handleError(MethodArgumentNotValidException e) {
        log.error("参数验证错误", e);
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResponseResult(CommonCode.INVLIDATE, objectError.getDefaultMessage());
    }

    /**
     * 处理自定义业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException e) {
        ResponseResult responseResult = new ResponseResult(e.getResultCode());
        return responseResult;
    }

    /**
     * 处理重复插入数据异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResponseResult duplicateKeyError(DuplicateKeyException e) {
        log.error("重复插入数据错误", e);
        return new ResponseResult(CommonCode.DUPLICATE_ERROR);
    }

}
