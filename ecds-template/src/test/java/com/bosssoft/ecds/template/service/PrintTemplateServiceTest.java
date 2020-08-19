package com.bosssoft.ecds.template.service;

import com.bosssoft.ecds.template.entity.dto.PrintTemplateDTO;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class PrintTemplateServiceTest {

    @Autowired
    PrintTemplateService printTemplateService;

    @Test
    void list() {
        List<PrintTemplatePO> templatePOs = printTemplateService.list();
        for (PrintTemplatePO t : templatePOs) {
            log.info(t.toString());
        }
    }

    @Test
    void listDTO() {
        List<PrintTemplateDTO> templateDTOs = printTemplateService.listAll();
        for (PrintTemplateDTO t : templateDTOs) {
            log.info(t.toString());
        }
    }

    @Test
    @Transactional
    void addTest() {
        PrintTemplateDTO templateDTO = new PrintTemplateDTO();
        templateDTO.setTemplate("模板测试");
        templateDTO.setRgnCode("01");
        templateDTO.setSortId("16");
        templateDTO.setTypeId("02");
        printTemplateService.add(templateDTO);
    }

    @Test
    void getTest() {
        PrintTemplateDTO templateDTO = printTemplateService.getDtoById(11L);
        System.out.println(templateDTO.getTemplate());
    }
}