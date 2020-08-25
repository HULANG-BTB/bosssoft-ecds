package com.bosssoft.ecds.template.service;

import com.bosssoft.ecds.template.entity.dto.NontaxBillDto;
import freemarker.template.Configuration;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

public interface PdfService {
    Configuration getConfiguration();

    String getOutData(Map<String, Object> data, String htmlName);

    File createPdf(String htmlData, String pdfDest);

    boolean createPdf(NontaxBillDto billDTO);

    boolean createPdf(NontaxBillDto billDTO, OutputStream outputStream);

    byte[] getBytesFromFile(String filename);

    /**
     * 根据票据信息生成pdf，返回OSS地址，限时访问
     * 根据代码和号码判断，不重复生成
     * 失败返回空字符串 ""
     *
     * @param billDTO 非税票据DTO
     * @param isForce 是否强制生成
     * @param expireTime 过期时间
     * @return 可访问的 URL
     */
    String getRemoteAddress(NontaxBillDto billDTO, boolean isForce,  Long expireTime);
}
