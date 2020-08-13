package com.bosssoft.ecds.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResponseResult
 * @Description 统一的响应结果
 * @Auther UoweMe
 * @Date 2020/8/12 11:35
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class ResponseResult {

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    public ResponseResult(ResultCode resultCode){
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public ResponseResult(boolean success,int code,String message){
        this.success=success;
        this.code=code;
        this.message=message;
    }

    public static ResponseResult SUCCESS(){
        return new ResponseResult(CommonCode.SUCCESS);
    }
    public static ResponseResult FAIL(){
        return new ResponseResult(CommonCode.FAIL);
    }
}
