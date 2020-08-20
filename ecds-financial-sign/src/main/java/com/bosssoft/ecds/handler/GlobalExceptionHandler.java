package com.bosssoft.ecds.handler;

import com.bosssoft.ecds.exception.ExceptionCatch;
import com.bosssoft.ecds.response.SignCode;
import org.apache.commons.codec.DecoderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/13
 * @Content: 全局异常处理，处理签名服务器的异常
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ExceptionCatch {

    static{
        builder.put(InvalidKeyException.class, SignCode.WRONG_CERT);
        builder.put(SignatureException.class, SignCode.WRONG_CERT_SIGN);
        builder.put(DecoderException.class, SignCode.WRONG_DECODE);
        builder.put(NoSuchAlgorithmException.class, SignCode.WRONG_ALGO);
        builder.put(NoSuchProviderException.class, SignCode.WRONG_PROVIDER);
        builder.put(CertificateException.class, SignCode.CERT_E);
    }

}
