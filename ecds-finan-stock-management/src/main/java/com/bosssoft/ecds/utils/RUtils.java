package com.bosssoft.ecds.utils;

import com.baomidou.mybatisplus.extension.api.R;
import com.bosssoft.ecds.util.ResponseUtils;

/**
 * 用于生成返回信息的工具类
 *
 * @author cheng
 * @Date 2020/8/11 11:32
 */
public class RUtils {
    /**
     * 由布尔值生成返回信息
     *
     * @param success  操作结果
     * @param object   需要传递的数据，可以为null
     * @param errorMsg 返回的信息，可以为null
     * @return 包装后的返回信息
     */
    public static R responseForBoolean(boolean success, Object object, String errorMsg) {
        if (success) {
            return R.ok(object);
        }
        return R.failed(errorMsg);
    }
    
    public static R responseForNull(Object object, String errorMsg) {
        if (object == null) {
            return R.failed(errorMsg);
        }
        return R.ok(object);
    }
    
    /**
     * 根据操作结果返回响应字符串
     *
     * @param success  操作成功
     * @param errorMsg 响应的错误提示信息，为空则显示默认信息
     * @param data     响应的数据，为空则响应体中数据部分为空
     * @return 对应的响应字符串
     */
    public static String responseBoolean(boolean success, Object data, String okMsg, String errorMsg) {
        String response = null;
        if (success) {
            okMsg = (okMsg == null) ? ResponseUtils.ResultType.ACCEPTED.getMsg() : okMsg;
            response = ResponseUtils.getResponse(ResponseUtils.ResultType.ACCEPTED.getCode(),
                    okMsg, null);
        } else {
            errorMsg = (errorMsg == null) ? ResponseUtils.ResultType.NOT_ACCEPTABLE.getMsg() : errorMsg;
            response = ResponseUtils.getResponse(ResponseUtils.ResultType.NOT_ACCEPTABLE.getCode(),
                    errorMsg, null);
        }
        return response;
    }
}
