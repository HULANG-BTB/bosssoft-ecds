package com.bosssoft.ecds.security.config;

import cn.hutool.crypto.asymmetric.RSA;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @ClassName RsaKeyProperties
 * @Author AloneH
 * @Date 2020/8/6 9:02
 * @Description TODO
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
