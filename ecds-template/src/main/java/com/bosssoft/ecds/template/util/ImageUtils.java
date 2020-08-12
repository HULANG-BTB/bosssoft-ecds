package com.bosssoft.ecds.template.util;

import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 图片工具类
 */
public class ImageUtils {

    Font font = new Font("宋体", Font.PLAIN, 16);

    Color color = new Color(5, 0, 0, 255);

    /**
     * 使用 Graphics2D 在图片上绘制文字
     * @param billValues 包含文字内容，坐标
     * @param srcImgName 可以带 `classpath:` 前缀的图片文件位置
     * @return 图片文件字节数组
     */
    private byte[] addMark(List<TextValue> billValues, String srcImgName){

        File srcImgFile = null;
        try {
            srcImgFile = ResourceUtils.getFile(srcImgName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image srcImg = null;
        try {
            srcImg = ImageIO.read(srcImgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bufImg.createGraphics();
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        g.setColor(color);
        g.setFont(font);

        // 绘制文本
        for (TextValue value : billValues){
            g.drawString(value.getValue(), value.getX(), value.getY());
        }

        g.dispose();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufImg, "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return imgBytes;
    }
}
