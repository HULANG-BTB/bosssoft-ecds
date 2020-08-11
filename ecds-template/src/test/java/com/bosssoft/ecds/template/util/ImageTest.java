package com.bosssoft.ecds.template.util;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class ImageTest {

    @Test
    void imgTest() throws IOException{
        Font font = new Font("宋体", Font.PLAIN, 16);
        String sourceImg = "bill.png";
        String destImg = "out.png";
        Color color = new Color(5, 0, 0, 255);
        String mark = "xiaoming";

        addMark(sourceImg, destImg, mark, color, font);
    }

    public void addMark(String srcImgName, String destImgName, String mark,  Color color, Font font)
            throws IOException {
        File srcImgFile = new File(srcImgName);
        Image srcImg = ImageIO.read(srcImgFile);
        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bufImg.createGraphics();
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        g.setColor(color);
        g.setFont(font);

        int x = 77;
        int y = 85;
        g.drawString(mark, x, y);
        g.dispose();

//        FileOutputStream outputStream = new FileOutputStream(destImgName);
//        outputStream.flush();
//        outputStream.close();

//        File outFile = new File(destImgName);
//        ImageIO.write(bufImg, "png", outFile);

    }

    /**
     * 打印出系统的所有支持的字体
     */
    @Test
    void listFonts(){
        String[] fontNames = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames(Locale.ENGLISH);
        String[] fontNamesCN = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames(Locale.CHINESE);
        System.out.printf("一共有 %d 种字体。\n", fontNames.length);;
        for (String font : fontNamesCN){
            System.out.println(font);
        }
    }
}
