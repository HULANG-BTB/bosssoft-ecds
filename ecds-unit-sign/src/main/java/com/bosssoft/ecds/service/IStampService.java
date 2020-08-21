package com.bosssoft.ecds.service;

import com.bosssoft.ecds.dto.SignedDataDto;
import com.bosssoft.ecds.dto.StampInfo;
import com.itextpdf.text.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

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
     * @param request 请求对象
     * @param response 响应对象
     * @return 是否成功加盖电子印章
     * @throws Exception
     */
    boolean stamp(MultipartFile uploadFile, String unitSignValue, String financeSignValue,
               HttpServletRequest request,HttpServletResponse response) throws Exception;
}
