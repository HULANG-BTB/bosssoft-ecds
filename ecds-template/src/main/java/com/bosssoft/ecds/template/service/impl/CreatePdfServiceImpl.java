package com.bosssoft.ecds.template.service.impl;

import com.bosssoft.ecds.template.service.CreatePdfService;
import com.itextpdf.text.pdf.BaseFont;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;

/**
 * 将freemarker模板文件转成pdf文件
 */
@Service
public class CreatePdfServiceImpl implements CreatePdfService {
    /**
     * 生成pdf文件
     * @param outData 整合的html数据
     * @param pdfDest 生成pdf文件的路径
     * @param fontType 字体类型路径
     */
    @Override
    public void createPdf(String outData, String pdfDest, String fontType) {
        ITextRenderer renderer = new ITextRenderer();
        /**
         * 解决中文不显示
         * 需要使用simsun.ttc(新宋体),而simsunb.ttf(宋体)无法解决中文不显示问题
         */
        ITextFontResolver fontResolver = renderer.getFontResolver();
        try {
            fontResolver.addFont(fontType, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            /**
             * 解析html并生成pdf
             */
            renderer.setDocumentFromString(outData);
            renderer.layout();
            /**
             * 根据设定的路径生成对应名字的pdf文件
             */
            renderer.createPDF(new FileOutputStream(pdfDest));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
