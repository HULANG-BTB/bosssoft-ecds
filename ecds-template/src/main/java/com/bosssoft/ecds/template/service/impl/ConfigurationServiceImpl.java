package com.bosssoft.ecds.template.service.impl;

import com.bosssoft.ecds.template.service.ConfigurationService;
import freemarker.template.Configuration;
import org.springframework.stereotype.Service;

/**
 * 设置freemarker配置信息
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {
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
}
