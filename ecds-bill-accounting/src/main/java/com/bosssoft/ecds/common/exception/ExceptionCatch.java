package com.bosssoft.ecds.common.exception;

import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.common.response.ResultCode;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExceptionCatch {

    private ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;

    public static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();

    /**
     * 处理未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult systemException(Exception e){
        if(EXCEPTIONS == null){
           EXCEPTIONS = builder.build();
        }
        ResultCode resultCode = EXCEPTIONS.get(e.getClass());
        if(resultCode != null){
            return new ResponseResult(resultCode);
        }
        log.error("catch exception info:{}",e.getMessage());
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
        return new ResponseResult(false,1004, objectError.getDefaultMessage());
    }

    /**
     * 处理自定义业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException e){
        ResponseResult responseResult = new ResponseResult(e.getResultCode());
        return responseResult;
    }

    static{
        //处理非法参数异常
        builder.put(org.springframework.http.converter.HttpMessageNotReadableException.class, CommonCode.INVLIDATE);
        //处理请求方法异常
        builder.put(HttpRequestMethodNotSupportedException.class, CommonCode.METHOD_NOT_SUPPORTED);
        //除了CustomException以外的异常类型及对应的错误代码在这里定义,，如果不定义则统一返回固定的错误信息
    }

}
