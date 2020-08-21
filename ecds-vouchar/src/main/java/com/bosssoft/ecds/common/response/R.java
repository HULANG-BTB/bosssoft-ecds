package com.bosssoft.ecds.common.response;

import com.bosssoft.ecds.common.CommonCode;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuke
 */
@Data
public class R implements Serializable {
    private Boolean success;

    private Long code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    /**
     * 构造器私有
     */
    private R() {
    }

    /**
     * 通用返回成功
     */
    public static R ok() {
        R r = new R();
        r.setSuccess(CommonCode.SUCCESS.success());
        r.setCode(CommonCode.SUCCESS.code());
        r.setMessage(CommonCode.SUCCESS.message());
        return r;
    }

    /**
     * 通用返回失败，未知错误
     */
    public static R error() {
        R r = new R();
        r.setSuccess(CommonCode.UNKNOWN_ERROR.success());
        r.setCode(CommonCode.UNKNOWN_ERROR.code());
        r.setMessage(CommonCode.UNKNOWN_ERROR.message());
        return r;
    }

    /**
     * 设置结果，形参为结果枚举
     */
    public static R setResult(CommonCode result) {
        R r = new R();
        r.setSuccess(result.success());
        r.setCode(result.code());
        r.setMessage(result.message());
        return r;
    }

    /**
     * ------------使用链式编程，返回类本身-----------
     **/

    // 自定义返回数据
    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    // 通用设置data
    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    // 自定义状态信息
    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    // 自定义状态码
    public R code(Long code) {
        this.setCode(code);
        return this;
    }

    // 自定义返回结果
    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }
}
