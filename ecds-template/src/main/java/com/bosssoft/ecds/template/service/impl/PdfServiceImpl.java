package com.bosssoft.ecds.template.service.impl;

import com.bosssoft.ecds.template.entity.dto.NontaxBillDto;
import com.bosssoft.ecds.template.entity.dto.PrintTemplateDto;
import com.bosssoft.ecds.template.service.HtmlService;
import com.bosssoft.ecds.template.service.PdfService;
import com.bosssoft.ecds.template.service.PrintTemplateService;
import com.bosssoft.ecds.template.util.AliyunOSSUtil;
import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    /**
     * iTextPDF 需要的字体文件
     */
    @Value("${fontType}")
    private String fontType;

    /**
     * pdf生成存放地址
     */
    @Value("${pdfDest}")
    private String defaultPdfDest;

    @Autowired
    AliyunOSSUtil ossUtil;

    @Autowired
    PrintTemplateService printTemplateService;

    @Autowired
    HtmlService htmlService;

    /**
     * 设置freemarker配置信息
     */
    @Override
    public Configuration getConfiguration() {
        Configuration cfg = new Configuration(Configuration.getVersion());
        // 将默认编码设置为utf-8，解决前端页面中的中文乱码问题
        cfg.setDefaultEncoding("UTF-8");
        // 设置模板加载路径
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        return cfg;
    }

    /**
     * 获取前端数据与html模板整合到一起的数据，并转成字符串的形式
     *
     * @param data    需要整合的数据
     * @param ftlName html模板地址
     */
    @Override
    public String getOutData(Map<String, Object> data, String ftlName) {
        Configuration cfg = getConfiguration();
        Writer writer = new StringWriter();
        // 整合的数据
        String outData = null;
        try {
            // 获取html模板
            Template template = cfg.getTemplate(ftlName);
            // 将map中的数据与html模板整合到一起
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
     *
     * @param htmlData html文本
     * @param pdfDest  生成pdf文件的路径
     */
    @Override
    public File createPdf(String htmlData, String pdfDest) {
        File file = new File(pdfDest);
        File dir = new File(file.getParent());
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            createPdf(htmlData, outputStream);
            log.info("createPdf: {}", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 生成pdf文件
     *
     * @param billDTO
     */
    @Override
    public boolean createPdf(NontaxBillDto billDTO) {
        /*Map<String, Object> data = new HashMap<>();
        data.put("billDTO", billDTO);
        String htmlName = getHtmlName(billDTO.getBillCode());
        String outData = getOutData(data, htmlName);*/
        String pdfDest = defaultPdfDest + getPdfDest(billDTO.getBillCode(), billDTO.getSerialCode());

        // 获取前6位的票据代码，用于查询模板
        String billCode = billDTO.getBillCode().substring(0, 6);
        PrintTemplateDto printTemplateDto = printTemplateService.getByBillCode(billCode);
        if (printTemplateDto==null) return false;
        String template = printTemplateDto.getTemplate();
        String htmlData = htmlService.genBillHtml(billDTO, template);

        createPdf(htmlData, pdfDest);
        return true;
    }

    @Override
    public boolean createPdf(NontaxBillDto billDTO, OutputStream outputStream) {
        /*Map<String, Object> data = new HashMap<>();
        data.put("billDTO", billDTO);
        String htmlName = getHtmlName(billDTO.getBillCode());
        String outData = getOutData(data, htmlName);*/

        String billCode = billDTO.getBillCode().substring(0, 6);
        PrintTemplateDto printTemplateDto = printTemplateService.getByBillCode(billCode);
        if (printTemplateDto==null) return true;
        String template = printTemplateDto.getTemplate();
        String htmlData = htmlService.genBillHtml(billDTO, template);

        createPdf(htmlData, outputStream);
        return true;
    }

    @Override
    public byte[] getBytesFromFile(String filename) {
        String filePath = defaultPdfDest + filename + ".pdf";
        File file = new File(filePath);
        byte[] bytes = new byte[0];
        if (file.exists()) {
            try {
                bytes = FileCopyUtils.copyToByteArray(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public String getRemoteAddress(NontaxBillDto billDTO, boolean isForce, Long expireTime) {
        String pdfFileName = getPdfDest(billDTO.getBillCode(), billDTO.getSerialCode());
        String path = "boss-bill/" + pdfFileName;

        if (isForce || !ossUtil.isExist(path)) {
            log.info("{}文件不存在，生成。", pdfFileName);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            boolean success = createPdf(billDTO, outputStream);
            if (!success) {
                return "";
            }
            byte[] pdfBytes = outputStream.toByteArray();

            // 文件上传
            ossUtil.upload(path, new ByteArrayInputStream(pdfBytes));
        }

        // 默认1个月的文件访问时间
        if (expireTime==null) {
            expireTime = 30 * 24 * 60 * 60 * 1000L;
        }

        URL url = ossUtil.temporaryUrl(path, expireTime);

        return url.toString();
    }

    /**
     * 设置ftl模板名
     *
     * @param code 票据代码
     * @return
     */
    private String getHtmlName(String code) {
        return code + ".ftl";
    }

    /**
     * 设置生成的pdf文件名
     *
     * @param code   票据代码
     * @param serial 票号
     * @return
     */
    private String getPdfDest(String code, String serial) {
        return code + serial + ".pdf";
    }

    private void createPdf(String outData, OutputStream outputStream) {
        ITextRenderer renderer = new ITextRenderer();
        /**
         * 解决中文不显示
         * 需要使用simsun.ttc(新宋体),而simsunb.ttf(宋体)无法解决中文不显示问题
         */
        ITextFontResolver fontResolver = renderer.getFontResolver();
        try {
            fontResolver.addFont(this.fontType, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            /**
             * 解析html并生成pdf
             */
            renderer.setDocumentFromString(outData);
            renderer.layout();
            /**
             * 根据设定的路径生成对应名字的pdf文件
             */
            renderer.createPDF(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getHtmlData() {
        // todo
        return null;
    }
}
