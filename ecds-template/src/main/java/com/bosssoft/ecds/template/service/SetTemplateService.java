package com.bosssoft.ecds.template.service;

import freemarker.template.Configuration;

import java.util.Map;

public interface SetTemplateService {
    String getOutData(Map<String, Object> data, Configuration cfg, String htmlName);
}
