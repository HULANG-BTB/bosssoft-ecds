package com.bosssoft.ecds.template.service;

import com.bosssoft.ecds.template.entity.dto.PrintTemplateDto;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePo;
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
        List<PrintTemplatePo> templatePOs = printTemplateService.list();
        for (PrintTemplatePo t : templatePOs) {
            log.info(t.toString());
        }
        assertNotNull(templatePOs);
    }

    @Test
    void listDTO() {
        List<PrintTemplateDto> templateDTOs = printTemplateService.listAll();
        for (PrintTemplateDto t : templateDTOs) {
            log.info(t.toString());
        }
        assertNotNull(templateDTOs);
    }

    @Test
    @Transactional
    void addTest() {
        PrintTemplateDto templateDTO = new PrintTemplateDto();
        templateDTO.setTemplate("模板测试");
        templateDTO.setRgnCode("01");
        templateDTO.setSortId("16");
        templateDTO.setTypeId("02");
        boolean res = printTemplateService.add(templateDTO);
        assert res;
    }

    @Test
    void getTest() {
        PrintTemplateDto templateDTO = printTemplateService.getDtoById(11L);
        System.out.println(templateDTO.getTemplate());
        assertNotNull(templateDTO);
    }

    //@Test
    void selectOne(){
        String billcode = "011602";
        PrintTemplateDto templateDTO = printTemplateService.getByBillCode(billcode);
        System.out.println(templateDTO);
        assertNotNull(templateDTO);
    }

    @Test
    @Transactional
    void setDefault() {
        Long id = 17L;
        boolean res = printTemplateService.setDefault(id);
        assertTrue(res);
    }
}