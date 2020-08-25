package com.bosssoft.ecds.utils;

import java.security.*;
import java.security.cert.Certificate;
import java.util.Enumeration;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/11
 * @Content: 密钥管理工具类，从 keyStore 获取公私钥，或直接从证书中获取公钥
 */
public class KeyUtill {

    /**
     * 从 key store 读取公钥
     * @return PublicKey
     * @throws Exception
     */
    public static PublicKey getPublicKey(KeyStore keyStore) throws Exception {
        String keyAliases = null;
        Enumeration<String> enumeration = keyStore.aliases();
        keyAliases = enumeration.nextElement();
        // 从keyStore获取对应的公钥
        if (keyStore.isKeyEntry(keyAliases)) {
            PublicKey publicKey = keyStore.getCertificate(keyAliases).getPublicKey();
            return publicKey;
        }
        return null;
    }

    /**
     * 从 key store 读取私钥
     * @param keyPassword 私钥读取密码
     * @param keyStore 用于存储密钥和证书的存储设施
     * @return PrivateKey
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(KeyStore keyStore, String keyPassword) throws KeyStoreException,KeyStoreException,
            NoSuchAlgorithmException, UnrecoverableKeyException{
        String keyAliases = null;
        Enumeration<String> enumeration = keyStore.aliases();
        keyAliases = enumeration.nextElement();
        if (keyStore.isKeyEntry(keyAliases)) {
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAliases, keyPassword.toCharArray());
            return privateKey;
        }
        return null;
    }

    /**
     * 从证书中获取公钥
     * @param cert
     * @return
     */
    public static PublicKey getPublicKey(Certificate cert) {
        PublicKey publicKey = cert.getPublicKey();
        return publicKey;
    }
}
