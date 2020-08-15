package com.bosssoft.ecds.template.service;

import com.bosssoft.ecds.template.dto.NontaxBillDTO;
import com.bosssoft.ecds.template.util.ResponseBody;
import freemarker.template.Configuration;

import java.io.File;
import java.util.Map;

public interface PdfService {
    Configuration getConfiguration();

    String getOutData(Map<String, Object> data, String htmlName);

    File createPdf(String htmlData, String pdfDest);

    void createPdf(NontaxBillDTO billDTO);

    byte[] getBytesFromFile(String filename);

    /**
     * 根据票据信息生成pdf，返回OSS地址，限时访问
     * 根据代码和号码判断，不重复生成
     * @param billDTO 非税票据DTO
     * @param isForce 是否强制生成
     * @return 可访问的 URL
     */
    String getRemoteAddress(NontaxBillDTO billDTO, boolean isForce);
}
