package com.bosssoft.ecds.template.util.excel;

import cn.hutool.core.io.file.FileWriter;
import com.bosssoft.ecds.template.entity.Excel;
import com.bosssoft.ecds.template.entity.MergeResult;
import com.bosssoft.ecds.template.entity.XMLItem;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SampleUtils {
    public static void excelToXml(String excelPath, String xmlPath) throws IOException, InvalidFormatException {
        excelToXml(new FileInputStream(excelPath), new java.io.FileWriter(xmlPath));
    }

    public static void excelToXml(InputStream inputStream, Writer writer) throws IOException, InvalidFormatException {
        Excel excel = ExcelUtils.getExcel(inputStream,0);
        int rowNum = excel.getSheet().getLastRowNum()+1;
        int coloumNum=excel.getSheet().getRow(0).getPhysicalNumberOfCells();
        List<Integer> hightList = new ArrayList<>();
        List<Integer> widthList = new ArrayList<>();
        Document document = XMLUtils.init();
        MergeResult[][] mergeFlag = ExcelUtils.mergeFlag(excel, hightList, widthList);
        XMLUtils.addStyleItem(document,hightList,widthList);
        // Element element = (Element) document.selectSingleNode("/ecds/body/item[@isdata='false']");
        int forFlag=0;
        int newRowFlag = 0;
        for (int i = 0; i < rowNum; i++) {
            newRowFlag = 0;
            for (int j = 0; j < coloumNum; j++) {
                if(ExcelUtils.mergeSkip(mergeFlag[i][j])){
                    continue;
                }
                if(forFlag == 0 && ((Character)ExcelUtils.getContent(excel.getSheet().getRow(i).getCell(j)).charAt(0)).hashCode() == 35) {
                    Element forElement = ((Element) document.selectSingleNode("/ecds/body")).addElement("for");
                    forElement.addAttribute("prefix", "");
                    forFlag=1;
                }
                XMLItem item = ExcelUtils.createItem(excel.getSheet().getRow(i).getCell(j), mergeFlag[i][j], newRowFlag, i);
                if(newRowFlag == 0 && j >= 0){
                    newRowFlag = 1;
                }
                XMLUtils.addItem(document,item.getParent(),item);
            }
        }
        XMLUtils.write(document,writer);
    }

    public static String xmlToFtl(String xmlPath, String DTOName, String ftlPath) throws IOException, DocumentException {
        String ftlStr = xmlToFtl(new FileReader(xmlPath), DTOName);
        FileWriter writer = new FileWriter(ftlPath);
        writer.write(ftlStr);
        return ftlStr;
    }

    public static String xmlToFtl(Reader reader, String DTOName) throws IOException, DocumentException {
        String ftlStr = "";
        Document document = XMLUtils.read(reader);

        List<Integer> widthList = new ArrayList<>();
        List<Element> widthElements = document.selectNodes("//width");
        for(int i = 0; i < widthElements.size(); i++) {
            widthList.add(Integer.parseInt(widthElements.get(i).getText()));
        }
        List<Integer> heightList = new ArrayList<>();
        List<Element> heightElements = document.selectNodes("//height");
        for(int i = 0; i < heightElements.size(); i++) {
            heightList.add(Integer.parseInt(heightElements.get(i).getText()));
        }

        ftlStr += FtlUtils.init(widthList);
        List<Element> itemElements = document.selectNodes("//item");

        int startFlag = 0;
        int endFlag = 0;
        for(int i = 0; i<itemElements.size(); i++) {
            XMLItem xmlItem = new XMLItem();
            xmlItem.setIsNewRow(itemElements.get(i).attribute("isnewrow").getValue());
            xmlItem.setColspan(Integer.parseInt(itemElements.get(i).attribute("colspan").getValue()));
            xmlItem.setRowspan(Integer.parseInt(itemElements.get(i).attribute("rowspan").getValue()));
            if("GENERAL".equals(itemElements.get(i).attribute("alignment").getValue())) {
                xmlItem.setAlignment("left");
            } else {
                xmlItem.setAlignment(itemElements.get(i).attribute("alignment").getValue().toLowerCase());
            }
            if("GENERAL".equals(itemElements.get(i).attribute("alignment").getValue())) {
                xmlItem.setAlignment("center");
            } else {
                xmlItem.setVerticalAlignment(itemElements.get(i).attribute("verticalalignment").getValue().toLowerCase());
            }
            xmlItem.setFontHeight(Integer.parseInt(itemElements.get(i).attribute("fontheight").getValue()));
            xmlItem.setParent(itemElements.get(i).getParent().getName());
            if ("true".equals(itemElements.get(i).attribute("isdata").getValue())) {
                xmlItem.setIsData("true");
                xmlItem.setContent(itemElements.get(i).attribute("datasource").getValue());
            } else {
                xmlItem.setIsData("false");
                xmlItem.setContent(itemElements.get(i).getText());
            }
            endFlag = 0;
            if ("for".equals(itemElements.get(i).getParent().getName())) {
                startFlag = 1;
                endFlag = 1;
                xmlItem.setPrefix(itemElements.get(i).getParent().attribute("prefix").getValue());
            }
            ftlStr += FtlUtils.itemToFtl(xmlItem, heightList, startFlag, endFlag, DTOName);
            if(startFlag==1 && endFlag == 0) {
                startFlag=0;
            }
        }
        ftlStr += FtlUtils.end();
        return ftlStr;
    }

    public static Configuration getConfiguration() {
        Configuration cfg = new Configuration(Configuration.getVersion());
        /**
         * 将默认编码设置为utf-8，解决前端页面中的中文乱码问题
         */
        cfg.setDefaultEncoding("UTF-8");
        /**
         * 设置模板加载路径
         */
//        cfg.setClassForTemplateLoading(SampleUtils.class,"/templates");
        try {
            cfg.setDirectoryForTemplateLoading(new File("C:/Users/GYM/Desktop/终版"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cfg;
    }
    public static String getOutData(Map<String, Object> data, String ftlName) {
        Configuration cfg = getConfiguration();
        Writer writer = new StringWriter();
        /**
         * 整合的数据
         */
        String outData = null;
        try {
            /**
             * 获取html模板
             */
            Template template = cfg.getTemplate(ftlName);
            /**
             * 将map中的数据与html模板整合到一起
             */
            template.process(data, writer);
            writer.flush();
            outData = writer.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outData;
    }
}
