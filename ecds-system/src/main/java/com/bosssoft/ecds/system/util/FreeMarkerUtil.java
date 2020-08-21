package com.bosssoft.ecds.system.util;


import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @description: FreeMarker生成模板
 * @author: lin.wanning
 * @date: Created in 2020/8/21 11:20
 * @modified By:
 */
public class FreeMarkerUtil {
    private final static String baseFilePath = "/templates/";

    /**
     * @param templateContent 模板文件内容
     * @param model           数据模型
     * @return java.lang.String
     * @description 根据模板文件内容，数据模型生成内容
     * @author lin.wanning
     * @date 2020/8/21
     */
    public static String generateTemplate(String templateContent, Map model) {
        //创建配置对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //创建模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template", templateContent);
        //向configuration配置模板加载器
        configuration.setTemplateLoader(stringTemplateLoader);
        //获取模板
        try {
            Template template = configuration.getTemplate("template");
            //调用api进行静态化
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param templateName
     * @return java.lang.String
     * @description 根据模板名称获取模板内容
     * @author lin.wanning
     * @date 2020/8/21
     */
    public static String getTemplate(String templateName) {
        if (StringUtils.isEmpty(templateName)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(baseFilePath);
        sb.append(templateName);
        ClassPathResource classPathResource = new ClassPathResource(sb.toString());
        String content = null;
        try {
            InputStream is = classPathResource.getInputStream();
            //从流中取数据
            content = IOUtils.toString(is, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
