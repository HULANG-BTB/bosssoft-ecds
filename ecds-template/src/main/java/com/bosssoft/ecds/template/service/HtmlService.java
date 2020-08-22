package com.bosssoft.ecds.template.service;

import com.bosssoft.ecds.template.entity.dto.NontaxBillDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 显示模板，用来从模板引擎中生成页面
 */

@Service
public interface HtmlService {

    String genHtml(Map<String, Object> data, String templateString);

    /**
     * 为非税票据的 DTO 和模板生成对应的 html 代码
     * @param billDTO 非税票据 DTO
     * @param templateString 非税票据模板
     * @return html字符串
     */
    String genBillHtml(NontaxBillDto billDTO, String templateString);
}
