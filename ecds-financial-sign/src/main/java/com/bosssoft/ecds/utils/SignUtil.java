package com.bosssoft.ecds.utils;

import com.bosssoft.ecds.domain.AlgorithmType;
import com.bosssoft.ecds.domain.StringType;
import org.apache.commons.codec.DecoderException;
import java.security.*;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/10
 * @Content: 签名工具类，加密摘要进行签名
 */
public class SignUtil {

    /**
     * 生成签名，即使用私钥对摘要进行加密
     * @param summary 摘要
     * @param privateKey 私钥
     * @param algorithmType 算法类型，对应者私钥和摘要的算法类型
     * @param stringType 签名字节数组编码类型
     * @return 签名
     * @throws Exception
     */
    public static String signData(String summary, PrivateKey privateKey, AlgorithmType algorithmType,StringType stringType) throws Exception {
        // 根据签名算法，获取签名实例
        Signature signture = Signature.getInstance(algorithmType.getsignatureAlgorithmType());
        signture.initSign(privateKey);
        // 对数据进行签名
        signture.update(summary.getBytes());
        // 返回签名后的数据
        byte[] sign = signture.sign();
        return ByteUtill.encode(sign, stringType);
    }

    /**
     * 验签，使用公钥验证签名信息
     * @param summary 摘要
     * @param signValue 签名
     * @param publicKey 公钥
     * @param algorithmType 算法类型
     * @param stringType 签名编码类型
     * @return 签名是否正确
     */
    public static boolean verifySign(String summary, String signValue, PublicKey publicKey, AlgorithmType algorithmType,StringType stringType) throws SignatureException, InvalidKeyException,NoSuchAlgorithmException, DecoderException {
        Signature signature = Signature.getInstance(algorithmType.getsignatureAlgorithmType());
        // 签名初始化公钥，即使用CA公钥解密
        signature.initVerify(publicKey);
        // 更新要签名或验证的数据,即摘要原文
        signature.update(summary.getBytes());
        // 使用解密获取的签名与签名原数据对比，验证签名
        return signature.verify(ByteUtill.decode(signValue,stringType));
    }
}
