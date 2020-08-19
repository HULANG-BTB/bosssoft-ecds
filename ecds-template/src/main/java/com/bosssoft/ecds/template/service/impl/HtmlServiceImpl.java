package com.bosssoft.ecds.template.service.impl;

import com.bosssoft.ecds.template.entity.dto.NontaxBillDto;
import com.bosssoft.ecds.template.service.HtmlService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Service
public class HtmlServiceImpl implements HtmlService {

    @Override
    public String genHtml(Map<String, Object> dataMap, String templateString) {
        // 传入字符串模板
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("stringTemplate", templateString);

        Configuration cfg = new Configuration(Configuration.getVersion());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateLoader(stringLoader);

        String html = null;
        try {
            Template template = cfg.getTemplate("stringTemplate");
            Writer writer = new StringWriter();
            template.process(dataMap, writer);
            writer.flush();
            html = writer.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        return html;
    }

    @Override
    public String genBillHtml(NontaxBillDto billDTO, String templateString) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("billDTO", billDTO);
        return genHtml(dataMap, templateString);
    }
}
