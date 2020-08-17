package com.bosssoft.ecds.msg;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 * @author zhangxiaohui
 * 明文通过Jasypt加密
 */
@Slf4j
public class JasyptUtil {
    public static void main(String[] args) {
        //加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("123456");
        encryptor.setConfig(config);
        String plaintext = "root";
        //加密
        String ciphertext = encryptor.encrypt(plaintext);
        log.info(plaintext + " : " + ciphertext);
    }
}
