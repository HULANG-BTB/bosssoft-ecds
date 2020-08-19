package com.bosssoft.ecds.utils;

import com.baomidou.mybatisplus.extension.api.R;

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
}
