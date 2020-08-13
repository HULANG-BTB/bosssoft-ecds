package com.bosssoft.ecds.template.service;

import java.io.OutputStream;

public interface CreatePdfService {
    void createPdf(String outData, String pdfDest, String fontType);
    void createPdf(String outData, String fontType, OutputStream outputStream);
}
