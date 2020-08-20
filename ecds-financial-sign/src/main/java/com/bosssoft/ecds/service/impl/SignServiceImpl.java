package com.bosssoft.ecds.service.impl;

import cn.hutool.json.JSONUtil;
import com.bosssoft.ecds.domain.AlgorithmType;
import com.bosssoft.ecds.domain.StringType;
import com.bosssoft.ecds.dto.SignedDataDto;
import com.bosssoft.ecds.utils.*;
import com.bosssoft.ecds.service.ISignService;
import org.apache.commons.codec.DecoderException;
import org.springframework.stereotype.Service;
import sun.security.x509.X509CertImpl;

import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author LiDaShan
 * @Date 2020/8/11
 * @Content:
 */
@Service
public class SignServiceImpl implements ISignService {

    @Override
    public SignedDataDto sign(SignedDataDto signedData) throws Exception{
        // 使用 SHA256 摘要算法生成摘要，并对摘要使用 BASE64 编码
        String summary = SummaryUtil.getSummary(signedData.getData(), AlgorithmType.SHA256, StringType.BASE64);
        // 从 keyStore 中读取私钥
        KeyStore keyStore = UnitP12Utill.getKeyStore();
        String keyPassword = UnitP12Utill.getKeyPassword();
        PrivateKey privateKey = KeyUtill.getPrivateKey(keyStore, keyPassword);
        // 判断证书密钥算法类型
        X509Certificate x509Cert = (X509Certificate) UnitP12Utill.getCertificate();
        // 判断算法类型，
        if(!AlgorithmType.SHA256.getsignatureAlgorithmType().equals(x509Cert.getSigAlgName())){
            throw new NoSuchAlgorithmException("此算法不支持");
        }
        // 使用私钥，采用SHA256算法加密摘要,获取签名
        String signValue = SignUtil.signData(summary, privateKey, AlgorithmType.SHA256, StringType.BASE64);
        // 获取公钥证书 crtCert已的字符串
        String crtCertStr = UnitCrtUtil.getCertStr();
        // 开始封装 SignedDocument, 并返回，使用建造者模式
        return SignedDataDto.builder()
                .data(signedData.getData())
                .unitSignValue(signedData.getUnitCrtCert())
                .unitCrtCert(signedData.getUnitCrtCert())
                .financeSignValue(signValue)
                .finaCrtCert(crtCertStr)
                .stringType(StringType.BASE64)
                .build();
    }

    @Override
    public boolean verifySign(SignedDataDto signedData) throws NoSuchProviderException,
            CertificateException, NoSuchAlgorithmException,
            InvalidKeyException, SignatureException, DecoderException {
        // 获取并验证单位端公钥数字证书 crtCert
        String crtCertStr = signedData.getUnitCrtCert();
        X509Certificate crtCert = StrToCertUtil.toCert(crtCertStr);
        verifyCrtCert(crtCert);
        // 判断算法类型， SHA256withRSA
        if(!AlgorithmType.SHA256.getsignatureAlgorithmType().equals(crtCert.getSigAlgName())){
            throw new NoSuchAlgorithmException("此算法不支持");
        }
        // 获取原文信息，生成摘要
        String data = signedData.getData();
        String summary = SummaryUtil.getSummary(data, AlgorithmType.SHA256, signedData.getStringType());
        // 获取财政签名
        String financeSignValue = signedData.getUnitSignValue();
        // 获取财政公钥
        PublicKey finaPublicKey = crtCert.getPublicKey();
        if (SignUtil.verifySign(summary, financeSignValue, finaPublicKey, AlgorithmType.SHA256, signedData.getStringType())){
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyCrtCert(X509Certificate crtCert) throws NoSuchProviderException, CertificateException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // 读取 CACrtCert,获取CA的公钥
        X509Certificate caCrtCert = CACrtUtil.getCert();
        PublicKey caPublicKey = caCrtCert.getPublicKey();
        // 验证证书有效性
        caCrtCert.verify(caPublicKey);
        return true;
    }
}