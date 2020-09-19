package com.bosssoft.ecds.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Properties;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/12
 * @Content: 加载 CA 机构的公钥证书，用以验证 crt 证书签名
 */
@Slf4j
public class CACrtUtil {

    /**
     * CA 证书
     */
    private static X509Certificate cert;

    /**
     * 从配置文件读取证书信息，并将其加载进 KeyStore
     */
    static{
        try {
            Properties pro=new Properties();
            // 加载配置文件
            InputStream resourceAsStream = CACrtUtil.class.getClassLoader().getResourceAsStream("certConfig.properties");
            pro.load(resourceAsStream);
            // 从配置文件读取证书信息
            String certPath=pro.getProperty("caCrtCertPath");
            String defaultCertName = pro.getProperty("caCrtCertDefaultName");
            resourceAsStream.close();
            // 初始化证书工厂,此处仅支持证书格式 X.509
            CertificateFactory certificatefactory=CertificateFactory.getInstance("X.509");
            File certFile=new File(certPath);
            InputStream input=null;
            if(StrUtil.isEmpty(certPath) ||!certFile.exists()){
                // 如果配置证书路径，就默认读取resources目录下名为defaultCertName的默认证书文件
                input= RSAUtil.class.getClassLoader().getResourceAsStream(defaultCertName);
            }else{
                input=new FileInputStream(certPath);
            }
            cert = (X509Certificate)certificatefactory.generateCertificate(input);
        } catch (Exception e) {
            log.error("CA证书加载失败，请检查证书是否添加配置");
        }
    }

    /**
     * 返回 CA 证书
     * @return
     */
    public static X509Certificate getCert(){
        return cert;
    }

}
