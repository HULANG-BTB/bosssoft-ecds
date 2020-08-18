package com.bosssoft.ecds.template.service;

import com.bosssoft.ecds.template.entity.dto.NontaxBillDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 显示模板，用来从模板引擎中生成页面
 */

@Service
public interface HtmlService {

    String genHtml(Map<String, Object> data, String templateString);

    String genBillHtml(NontaxBillDTO billDTO, String templateString);
}
