package com.bosssoft.ecds.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.bosssoft.ecds.domain.*;
import com.bosssoft.ecds.dto.SignedBillDto;
import com.bosssoft.ecds.service.ISignService;
import com.bosssoft.ecds.utils.*;
import org.springframework.stereotype.Service;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/11
 * @Content:
 */
@Service
public class SignServiceImpl implements ISignService {

    @Override
    public SignedBillDto sign(Bill bill) throws Exception {
        // 获取要签名数据的字符串
        JSON billJson = JSONUtil.parse(bill);
        String data = billJson.toString();
        // 使用 SHA256 摘要算法生成摘要，并对摘要使用 BASE64 编码
        String summary = SummaryUtil.getSummary(data, AlgorithmType.SHA256, StringType.BASE64);
        // 从 keyStore 中读取私钥
        KeyStore keyStore = UnitP12Utill.getKeyStore();
        String keyPassword = UnitP12Utill.getKeyPassword();
        PrivateKey privateKey = KeyUtill.getPrivateKey(keyStore, keyPassword);
        // 判断证书密钥算法类型
        X509Certificate x509Cert = (X509Certificate) UnitP12Utill.getCertificate();
        // 判断算法类型，
        if(!AlgorithmType.SHA256.getsignatureAlgorithmType().equals(x509Cert.getSigAlgName())){
            throw new Exception("此算法类型不支持");
        }
        // 使用私钥，采用SHA256算法加密摘要,获取签名
        String signValue = SignUtil.signData(summary, privateKey, AlgorithmType.SHA256, StringType.BASE64);
        // 获取单位端的CA证书 crtCert
        X509Certificate unitCrtCert = UnitCrtUtil.getCert();
        // 开始封装 SignedDocument, 并返回，使用建造者模式
        return SignedBillDto.builder()
                .bill(bill)
                .unitSignValue(signValue)
                .unitCrtCert(unitCrtCert)
                .stringType(StringType.BASE64)
                .build();
    }

    @Override
    public boolean verifySign(SignedBillDto signedBill) throws Exception {
        // 获取并验证财政端 CA 数字证书 crtCert
        X509Certificate finaCrtCert = signedBill.getFinaCrtCert();
        verifyCrtCert(finaCrtCert);
        // 判断算法类型，
        if(!AlgorithmType.SHA256.getsignatureAlgorithmType().equals(finaCrtCert.getSigAlgName())){
            throw new Exception("此算法类型不支持");
        }
        // 获取原文信息，生成摘要
        Bill bill = signedBill.getBill();
        JSON billJson = JSONUtil.parse(bill);
        String data = billJson.toString();
        String summary = SummaryUtil.getSummary(data, AlgorithmType.SHA256, signedBill.getStringType());
        // 获取财政签名
        String financeSignValue = signedBill.getFinanceSignValue();
        // 获取财政公钥
        PublicKey finaPublicKey = finaCrtCert.getPublicKey();
        if (SignUtil.verifySign(summary, financeSignValue, finaPublicKey, AlgorithmType.SHA256, signedBill.getStringType())){
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
