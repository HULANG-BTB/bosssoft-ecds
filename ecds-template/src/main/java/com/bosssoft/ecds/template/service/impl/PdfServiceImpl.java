package com.bosssoft.ecds.template.service.impl;

import com.bosssoft.ecds.template.dto.NontaxBillDTO;
import com.bosssoft.ecds.template.service.PdfService;
import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Service
public class PdfServiceImpl implements PdfService {

    @Value("${fontType}")
    private String fontType;

    /**
     * pdf生成存放地址
     */
    @Value("${pdfDest}")
    private String defaultPdfDest;

    /**
     * 设置freemarker配置信息
     */
    @Override
    public Configuration getConfiguration() {
        Configuration cfg = new Configuration(Configuration.getVersion());
        /**
         * 将默认编码设置为utf-8，解决前端页面中的中文乱码问题
         */
        cfg.setDefaultEncoding("UTF-8");
        /**
         * 设置模板加载路径
         */
        cfg.setClassForTemplateLoading(this.getClass(),"/templates");
        return cfg;
    }

    /**
     * 获取前端数据与html模板整合到一起的数据，并转成字符串的形式
     * @param data 需要整合的数据
     * @param htmlName html模板地址
     * @return
     */
    @Override
    public String getOutData(Map<String, Object> data, String htmlName) {
        Configuration cfg = getConfiguration();
        Writer writer = new StringWriter();
        /**
         * 整合的数据
         */
        String outData = null;
        try {
            /**
             * 获取html模板
             */
            Template template = cfg.getTemplate(htmlName);
            /**
             * 将map中的数据与html模板整合到一起
             */
            template.process(data, writer);
            writer.flush();
            outData = writer.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outData;
    }

    /**
     * 生成pdf文件
     * @param data 传输的数据
     * @param htmlName ftl模板路径
     * @param pdfDest 生成pdf文件的路径
     */
    @Override
    public void createPdf(Map<String, Object> data, String htmlName, String pdfDest) {
        String outData = getOutData(data, htmlName);
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

    /**
     * 生成pdf文件
     * @param billDTO
     */
    @Override
    public void createPdf(NontaxBillDTO billDTO) {
        Map<String, Object> data = new HashMap<>();
        data.put("billDTO",billDTO);
        String htmlName = getHtmlName(billDTO.getBillCode());
        String pdfDest = defaultPdfDest + getPdfDest(billDTO.getBillCode(), billDTO.getSerialCode());
        createPdf(data, htmlName, pdfDest);
    }

    /**
     * 设置ftl模板名
     * @param code 票据代码
     * @return
     */
    private String getHtmlName(String code){
        return code + ".ftl";
    }

    /**
     * 设置生成的pdf文件名
     * @param code 票据代码
     * @param serial 票号
     * @return
     */
    private String getPdfDest(String code, String serial){
        return code + serial + ".pdf";
    }
}
