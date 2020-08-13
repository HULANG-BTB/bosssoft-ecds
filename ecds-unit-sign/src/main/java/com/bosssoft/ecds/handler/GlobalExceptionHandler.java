package com.bosssoft.ecds.handler;

import com.bosssoft.ecds.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/13
 * @Content:
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * controller 发生异常时，统一处理并返回前端服务器内部错误
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String handleE(Exception e){
        log.error(e.getMessage(),e);
        return ResponseUtils.getResponse(500,"服务器出错");
    }

    /**
     * 证书过期或证书失效
     * @param e
     * @return
     */
    @ExceptionHandler({NoSuchProviderException.class, CertificateException.class})
    public String certWorry(Exception e){
        log.error(e.getMessage(),e);
        return ResponseUtils.getResponse(500,"证书非法或失效");
    }

    /**
     * 密钥加密算法不支持
     * @param e
     * @return
     */
    @ExceptionHandler(NoSuchAlgorithmException.class)
    public String algorithmWorry(Exception e){
        log.error(e.getMessage(),e);
        return ResponseUtils.getResponse(500,"此证书暂不支持");
    }

    /**
     * 证书公钥错误
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidKeyException.class)
    public String invalidKey (Exception e){
        log.error(e.getMessage(),e);
        return ResponseUtils.getResponse(500,"证书存在错误");
    }

    /**
     * 公钥证书crt中CA签名错误
     * @param e
     * @return
     */
    @ExceptionHandler(SignatureException.class)
    public String SignatureWorry(Exception e){
        log.error(e.getMessage(),e);
        return ResponseUtils.getResponse(500,"公钥证书签名不匹配，证书非法");
    }

    /**
     * 签名解码错误
     * @param e
     * @return
     */
    @ExceptionHandler( DecoderException.class)
    public String  decoderExceptionWorry(Exception e){
        log.error(e.getMessage(),e);
        return ResponseUtils.getResponse(500,"编码错误");
    }


}
