package com.bosssoft.ecds.utils;

import com.bosssoft.ecds.domain.AlgorithmType;
import com.bosssoft.ecds.domain.StringType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/10
 * @Content: 生成文件摘要
 */
public class SummaryUtil {

    /**
     * 对指定文件使用指定的摘要函数生成摘要
     * @param file
     * @return
     */
    public static String getSummary(File file, AlgorithmType algorithmType,StringType stringType) {
        FileInputStream fis = null;
        try {
            // 使用MD5算法生成摘要
            MessageDigest md = MessageDigest.getInstance(algorithmType.getsummaryAlgorithType());
            fis = new FileInputStream(file);
            byte[] buffer = new byte[2048];
            int length = -1;
            long s = System.currentTimeMillis();
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            byte[] b = md.digest();
            return ByteUtill.encode(b, StringType.BASE64);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 对一段String使用指定类型的摘要函数生成摘要
     * @param message 要加密的String
     * @param algorithmType 摘要算法类型
     * @param stringType 摘要编码方式
     * @return 生成的摘要
     */
    public static String getSummary (String message, AlgorithmType algorithmType,StringType stringType) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithmType.getsummaryAlgorithType());
            byte[] b = md.digest(message.getBytes("utf-8"));
            return ByteUtill.encode(b, stringType);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
