package com.bosssoft.ecds.service;

import com.bosssoft.ecds.domain.Bill;
import com.bosssoft.ecds.dto.SignedBillDto;

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
     * @param bill 需要签名的数据文件
     * @return 签名信息
     */
    public SignedBillDto sign(Bill bill) throws Exception;

    /**
     * 确认签名
     * @param signedBill 签名信息类
     * @return 签名与文件正确
     */
    public boolean verifySign(SignedBillDto signedBill) throws Exception;

    /**
     * 验证 CA 证书
     * @param crtCert CA 证书
     * @return CA 证书是否正确
     * @throws Exception
     */
    public boolean verifyCrtCert(X509Certificate crtCert) throws Exception;
}
