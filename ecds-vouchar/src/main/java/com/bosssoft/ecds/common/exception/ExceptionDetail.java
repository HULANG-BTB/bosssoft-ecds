package com.bosssoft.ecds.common.exception;

import com.bosssoft.ecds.common.CommonCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Raiz
 * @date :2020/8/18
 */
@Data
@NoArgsConstructor
public class ExceptionDetail {

    Long code;

    String message;

    String tags;

    public ExceptionDetail(CommonCode resultCode, String tags) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.tags = tags;
    }

    public ExceptionDetail(CommonCode resultCode, String errorMsg, String tags) {
        this.code = resultCode.code();
        this.tags = tags;
        if (errorMsg != null && !"".equals(errorMsg.trim())) {
            this.message = errorMsg;
        } else {
            this.message = resultCode.message();
        }
    }
}
