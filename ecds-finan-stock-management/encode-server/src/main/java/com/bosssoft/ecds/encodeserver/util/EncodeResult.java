package com.bosssoft.ecds.encodeserver.util;

/**
 * @Author 黄杰峰
 * @Date 2020/8/12 0012 16:08
 * @Description
 */
public class EncodeResult extends ResponseUtils.ResponseBody {

    public static EncodeResult getEncodeResult(Integer code, String msg) {
        EncodeResult encodeResult = new EncodeResult();
        encodeResult.setStatus(code);
        encodeResult.setMsg(msg);
        return encodeResult;
    }

    public static EncodeResult getEncodeResult(Integer code, String msg, Object content) {
        EncodeResult encodeResult = new EncodeResult();
        encodeResult.setStatus(code);
        encodeResult.setMsg(msg);
        encodeResult.setData(content);
        return encodeResult;
    }
}
