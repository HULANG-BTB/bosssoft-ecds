package com.bosssoft.ecds.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName RsaKeyProperties
 * @Author AloneH
 * @Date 2020/8/6 9:02
 * @Description
 *      RSA 配置文件读取
 **/
@Configuration
@ConfigurationProperties("rsa.key")
public class RsaKeyProperties {

    private String privateKey;

    private String publicKey;

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
