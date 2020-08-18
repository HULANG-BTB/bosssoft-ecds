package com.bosssoft.ecds.exception;

import com.bosssoft.ecds.response.ResultCode;
import lombok.Data;

/**
 * @author :Raiz
 * @date :2020/8/18
 */
@Data
public class ExceptionDetail {

    int code;

    String message;

    String tags;

    public ExceptionDetail (ResultCode resultCode, String tags){
        this.code  = resultCode.code();
        this.message = resultCode.message();
        this.tags = tags;
    }
    public ExceptionDetail (ResultCode resultCode, String errorMsg,String tags){
        this.code  = resultCode.code();
        this.tags = tags;
        if (errorMsg != null && !"".equals(errorMsg.trim())) {
            this.message = errorMsg;
        } else {
            this.message = resultCode.message();
        }
    }
}
