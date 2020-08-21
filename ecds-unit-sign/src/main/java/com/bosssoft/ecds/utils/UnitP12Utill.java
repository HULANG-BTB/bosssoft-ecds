package com.bosssoft.ecds.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/12
 * @Content:
 */
@Slf4j
public class UnitP12Utill {

    /**
     * keystore，用于存储本地证书，密钥对
     */
    private static KeyStore keyStore;

    /**
     * KeyStore 的密码
     */
    private static String keyPassword;

    /**
     * 从配置文件读取单位根证书信息，并将其加载进 KeyStore
     *      p12证书作为单位根证书，其包含单位私钥密钥
     */
    static{
        try {
            Properties pro=new Properties();
            // 加载配置文件
            InputStream resourceAsStream = UnitP12Utill.class.getClassLoader().getResourceAsStream("certConfig.properties");
            pro.load(resourceAsStream);
            // 从配置文件读取p12证书路径
            String certPath=pro.getProperty("p12CertPath");
            String defaultCertName = pro.getProperty("defaultP12CertName");
            resourceAsStream.close();
            // 读取本地的证书
            keyStore = KeyStore.getInstance("PKCS12");
            File certFile=new File(certPath);
            InputStream input=null;
            if(StrUtil.isEmpty(certPath) ||!certFile.exists()){
                // 如果配置证书路径，就默认读取resources目录下名为defaultCertName的默认证书文件
                input= RSAUtil.class.getClassLoader().getResourceAsStream(defaultCertName);
            }else{
                input=new FileInputStream(certPath);
            }
            // 从配置文件读取证书密码
            keyPassword=pro.getProperty("p12CertPWD");
            keyStore.load(input, keyPassword.toCharArray());
            input.close();
        } catch (Exception e) {
            log.error("单位p12根证书加载失败，请检查p12证书是否添加配置");
        }
    }

    public static KeyStore getKeyStore(){
        return keyStore;
    }

    public static String getKeyPassword(){
        return keyPassword;
    }

    /**
     * 从 keystore 获取证书
     * @return
     * @throws KeyStoreException
     */
    public static Certificate getCertificate() throws KeyStoreException {
        String alias = null;
        Enumeration<String> enumeration = keyStore.aliases();
        alias = enumeration.nextElement();
        // 从keyStore获取对应的公钥
        if (keyStore.isKeyEntry(alias)) {
            return keyStore.getCertificate(alias);
        }
        return null;
    }



}
