package com.bosssoft.ecds.template.entity;

import lombok.Data;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

@Data
public class Excel {
    private OPCPackage pkg;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public Excel(String path, int index) throws InvalidFormatException, IOException {
        this.pkg=OPCPackage.open(path);
        this.workbook=new XSSFWorkbook(pkg);
        //获取第一个sheet
        this.sheet=workbook.getSheetAt(index);
    }

    public Excel(InputStream is, int index) throws InvalidFormatException, IOException {
        this.pkg = OPCPackage.open(is);
        this.workbook = new XSSFWorkbook(pkg);
        this.sheet = workbook.getSheetAt(index);
    }

    public void close() throws IOException {
        this.workbook.close();
        this.pkg.close();
    }

}
