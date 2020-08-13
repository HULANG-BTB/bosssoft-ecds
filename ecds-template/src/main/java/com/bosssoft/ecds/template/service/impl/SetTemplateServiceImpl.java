package com.bosssoft.ecds.template.service.impl;

import com.bosssoft.ecds.template.service.SetTemplateService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * 整合freemarker模板信息
 */
@Service
public class SetTemplateServiceImpl implements SetTemplateService {
    /**
     * 获取前端数据与html模板整合到一起的数据，并转成字符串的形式
     * @param data 需要整合的数据
     * @param cfg freemarker配置信息
     * @param htmlName html模板地址
     * @return
     */
    @Override
    public String getOutData(Map<String, Object> data, Configuration cfg, String htmlName) {
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
}
