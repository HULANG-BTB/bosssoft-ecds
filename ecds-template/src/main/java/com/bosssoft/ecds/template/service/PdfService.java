package com.bosssoft.ecds.template.service;

import com.bosssoft.ecds.template.dto.NontaxBillDTO;
import freemarker.template.Configuration;

import java.util.Map;

public interface PdfService {
    Configuration getConfiguration();

    String getOutData(Map<String, Object> data, String htmlName);

    void createPdf(Map<String, Object> data, String htmlName, String pdfDest);

    void createPdf(NontaxBillDTO billDTO);
}
