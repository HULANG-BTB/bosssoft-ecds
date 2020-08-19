package com.bosssoft.ecds.msg.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangxiaohui
 * @create 2020/8/19 13:09
 */
    public class RequestUtil {
    protected RequestUtil(){}

    private static final String UNKNOWN = "unknown";
    private static final String SPLIT_SYMBOL = ",";

    /**
     * 获取真实IP
     *
     * @param request 请求体
     * @return 真实IP
     */
    public static String getRealIp(HttpServletRequest request) {
        // 这个一般是Nginx反向代理设置的参数
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 处理多IP的情况（只取第一个IP）
        if (ip != null && ip.contains(SPLIT_SYMBOL)) {
            String[] ipArray = ip.split(SPLIT_SYMBOL);
            ip = ipArray[0];
        }
        return ip;
    }
}
