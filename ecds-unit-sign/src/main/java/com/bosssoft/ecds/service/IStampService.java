package com.bosssoft.ecds.service;

import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/15
 * @Content: 电子盖章服务
 */
public interface IStampService {

    /**
     * 对pdf文件加盖加盖印章
     * @param uploadFile 要加盖印章的pdf文件
     * @param unitSignValue 单位签名
     * @param financeSignValue 财政签名
     * @param response 响应对象
     * @return 是否成功加盖电子印章
     * @throws Exception 抛出异常
     */
    boolean stamp(MultipartFile uploadFile, String unitSignValue, String financeSignValue,
               HttpServletResponse response) throws Exception;
}
