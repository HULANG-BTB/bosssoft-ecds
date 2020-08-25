package com.bosssoft.ecds.utils;

import com.bosssoft.ecds.dto.StampInfo;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/15
 * @Content:
 */
public class StampUtill {

    /**
     * 盖章工具类
     * @param src 需要印章的pdf文件输入流
     * @param dest 印章后需要写入的输出流
     * @param pk 私钥
     * @Param chain 证书链
     * @Param stampInfo 印章信息类
     * @param reason 印章的原因，显示在pdf签名属性中
     * @param location 印章的地点，显示在pdf签名属性中
     * @param image 印章图片地址
     * @throws GeneralSecurityException
     * @throws IOException
     * @throws DocumentException
     */
    public static void stamp(InputStream src
            , OutputStream dest
            , PrivateKey pk
            , char[] password
            , Certificate[] chain
            , StampInfo stampInfo
            , String reason
            , String location
            , Image image )
            throws GeneralSecurityException, IOException, DocumentException {
        // 示例固定步骤，参考贴:https://www.jianshu.com/p/92c6faec0427
        // Creating the reader and the stamper，开始pdfreader
        PdfReader reader = new PdfReader(src);
        //目标文件输出流
        //      创建签章工具PdfStamper ，最后一个boolean参数
        //          false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
        //          true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
        PdfStamper stamper = PdfStamper.createSignature(reader, dest, '\0', null, true);
        // 获取数字签章属性对象，设定数字签章的属性
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason(reason);
        appearance.setLocation(location);
        //设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样
        //签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
        //四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
        appearance.setVisibleSignature(stampInfo.getLocation(),stampInfo.getPage(),stampInfo.getStampName());
        //读取图章图片，这个image是itext包的image
        appearance.setSignatureGraphic(image);
        appearance.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
        //设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
        appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
        // 这里的itext提供了2个用于签名的接口，可以自己实现，后边着重说这个实现
        // 摘要算法
        ExternalDigest digest = new BouncyCastleDigest();
        // 签名算法
        ExternalSignature signature = new PrivateKeySignature(pk, DigestAlgorithms.SHA256, null);
        // 调用itext签名方法完成pdf签章CryptoStandard.CMS 签名方式，建议采用这种
        MakeSignature.signDetached(appearance, digest, signature, chain, null, null, null, 0, MakeSignature.CryptoStandard.CMS);
    }

}
