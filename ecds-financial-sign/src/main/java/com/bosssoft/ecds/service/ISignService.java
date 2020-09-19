package com.bosssoft.ecds.service;


import com.bosssoft.ecds.dto.SignedDataDto;
import org.apache.commons.codec.DecoderException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/11
 * @Content:
 */
public interface ISignService {

    /**
     * 对票据文件进行签名
     * @param signedData 需要签名的数据文件
     * @return 签名信息
     */
    SignedDataDto sign(SignedDataDto signedData) throws Exception;

    /**
     * 确认签名
     * @param signedData 签名信息类
     * @return 签名与文件正确
     */
    boolean verifySign(SignedDataDto signedData) throws NoSuchProviderException,
            CertificateException, NoSuchAlgorithmException,
            InvalidKeyException, SignatureException, DecoderException;

    /**
     * 验证 CA 证书
     * @param crtCert CA 证书
     * @return CA 证书是否正确
     * @throws Exception
     */
    boolean verifyCrtCert(X509Certificate crtCert) throws Exception;
}
