package com.bosssoft.ecds.utils;

import com.bosssoft.ecds.domain.StringType;
import org.apache.commons.codec.DecoderException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/13
 * @Content: 将证书字符串转为 inputStream 读取为证书
 */
public class StrToCertUtil {

    /**
     * 将 dto 中公钥证书的字符串形式转为 X509Certificate
     * @param certStr
     * @return
     * @throws DecoderException
     * @throws CertificateException
     */
    public static X509Certificate toCert(String certStr) throws DecoderException, CertificateException {
        InputStream is =new ByteArrayInputStream(certStr.getBytes());
        CertificateFactory certificatefactory=CertificateFactory.getInstance("X.509");
        return (X509Certificate)certificatefactory.generateCertificate(is);
    }

}
