package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.dto.StampInfo;
import com.bosssoft.ecds.service.IStampService;
import com.bosssoft.ecds.util.RedisUtils;
import com.bosssoft.ecds.utils.KeyUtill;
import com.bosssoft.ecds.utils.StampUtill;
import com.bosssoft.ecds.utils.UnitP12Utill;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/15
 * @Content: 对票据 pdf 盖章 service
 */
@Service
@RefreshScope
public class StampServiceImpl implements IStampService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Value("#{'${stamp.location.llx}'}")
    private float llx;
    @Value("#{'${stamp.location.lly}'}")
    private float lly;
    @Value("#{'${stamp.location.urx}'}")
    private float urx = 200f;
    @Value("#{'${stamp.location.ury}'}")
    private float ury = 200f;
    @Value("#{'${stamp.page}'}")
    private int page =1;
    @Value("${stamp.imagePath}")
    private String imagePath;
    @Value("${stamp.defaultImageName}")
    private String defaultImageName;

    @Override
    public boolean stamp(MultipartFile uploadFile, String unitSignValue, String financeSignValue,
                      HttpServletResponse response) throws Exception {
        // 验证签名的有效性
        RedisUtils redisUtils = new RedisUtils(redisTemplate);
        String financeSign = (String) redisUtils.get(unitSignValue);
        if ( financeSign == null || "".equals(financeSign) || !financeSign.equals(financeSignValue)){
            return false;
        }
        // 读取要盖章的 pdf 文件
        InputStream is = uploadFile.getInputStream();
        // 盖章后写入response
        ServletOutputStream os = response.getOutputStream();
        // 获取私钥,密码和证书链
        KeyStore keyStore = UnitP12Utill.getKeyStore();
        String alias = keyStore.aliases().nextElement();
        String password = UnitP12Utill.getKeyPassword();
        PrivateKey pk = KeyUtill.getPrivateKey(keyStore, password);
        Certificate[] chain = keyStore.getCertificateChain(alias);
        // 设置电子印章位置信息
        StampInfo stampInfo = StampInfo.builder()
                .location(new Rectangle(llx,lly,urx,ury))
                .page(page)
                .stampName("大番茄帝国财政")
                .build();
        // 设置签名说明，签名机构位置，签名图片
        String reason = "帝国认证票据";
        String location = "福建福州";
        // 默认
        Image image ;
        if ( imagePath == null || "".equals(imagePath)|| !new File(imagePath).exists() ){
            if ( StampServiceImpl.class.getClassLoader().getResource(defaultImageName) != null ){
                image = Image.getInstance(StampServiceImpl.class.getClassLoader().getResource(defaultImageName));
            }else {
                throw new FileNotFoundException("印章图片没有找到");
            }
        }else {
            image = Image.getInstance(imagePath);
        }
        StampUtill.stamp(is,os,pk,password.toCharArray(),chain,stampInfo,reason,location,image);
        return true;
    }
}
