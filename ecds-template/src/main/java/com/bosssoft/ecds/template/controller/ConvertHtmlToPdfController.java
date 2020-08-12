package com.bosssoft.ecds.template.controller;

import com.bosssoft.ecds.template.dto.BillItemDTO;
import com.bosssoft.ecds.template.dto.NontaxBillDTO;
import com.bosssoft.ecds.template.service.ConfigurationService;
import com.bosssoft.ecds.template.service.CreatePdfService;
import com.bosssoft.ecds.template.service.SetTemplateService;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConvertHtmlToPdfController {

    /**
     * 字体存放地址（新宋体）
     */
    @Value("${fontType}")
    private String fontType;

    /**
     * pdf生成存放地址
     */
    @Value("${pdfDest}")
    private String pdfDest;

    /**
     * html模板获取地址
     */
    @Value("${htmlName}")
    private String htmlName;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private SetTemplateService setTemplateService;
    
    @Autowired
    private CreatePdfService createPdfService;
    
    @RequestMapping("/test")
    @ResponseBody
    public void testCreatePdf(){
        Map<String ,Object> data = new HashMap<>();
        List<BillItemDTO> list = new ArrayList<>();
        BillItemDTO billItem = new BillItemDTO();
        billItem.setItemCode("123");
        billItem.setItemName("啊啊啊");
        billItem.setUnits("本");
        billItem.setQuantity("12");
        billItem.setStandardName("无");
        billItem.setAmount("6000");
        list.add(billItem);
        list.add(billItem);

        data.put("billCode","123456");
        data.put("checkCode","fdasfads");
        data.put("date","2020-08-10");
        data.put("items",list);

        Configuration cfg = configurationService.getConfiguration();
        String outData = setTemplateService.getOutData(data, cfg, htmlName);
        createPdfService.createPdf(outData, pdfDest, fontType);
    }

    @PostMapping(value = "/createPdf1")
    @ResponseBody
    public Object createPdf(String billCode, String checkCode){
        Map<String,Object> data = new HashMap<>();
        data.put("billCode",billCode);
        data.put("checkCode",checkCode);
        System.out.println(billCode);
        Configuration cfg = configurationService.getConfiguration();
        String outData = setTemplateService.getOutData(data, cfg, htmlName);
        createPdfService.createPdf(outData, pdfDest, fontType);
        return null;
    }


    @PostMapping(value = "/createPdf2")
    @ResponseBody
    public Object createPdf(@RequestBody NontaxBillDTO billDTO){
        Map<String,Object> data = new HashMap<>();
        data.put("billDTO",billDTO);
        Configuration cfg = configurationService.getConfiguration();
        String outData = setTemplateService.getOutData(data, cfg, htmlName);
        createPdfService.createPdf(outData, pdfDest, fontType);
        return billDTO;
    }
}
