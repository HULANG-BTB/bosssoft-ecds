package com.bosssoft.ecds.template.service;

import com.bosssoft.ecds.template.entity.dto.PrintTemplateDto;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
class PrintTemplateServiceTest {

    @Autowired
    PrintTemplateService printTemplateService;

    @Test
    void list() {
        List<PrintTemplatePo> templatePOs = printTemplateService.list();
        for (PrintTemplatePo t : templatePOs) {
            log.info(t.toString());
        }
    }

    @Test
    void listDTO() {
        List<PrintTemplateDto> templateDTOs = printTemplateService.listAll();
        for (PrintTemplateDto t : templateDTOs) {
            log.info(t.toString());
        }
    }

    @Test
    @Transactional
    void addTest() {
        PrintTemplateDto templateDTO = new PrintTemplateDto();
        templateDTO.setTemplate("模板测试");
        templateDTO.setRgnCode("01");
        templateDTO.setSortId("16");
        templateDTO.setTypeId("02");
        printTemplateService.add(templateDTO);
    }

    @Test
    void getTest() {
        PrintTemplateDto templateDTO = printTemplateService.getDtoById(11L);
        System.out.println(templateDTO.getTemplate());
    }
}