package com.bosssoft.ecds.template.util.excel;

import com.bosssoft.ecds.template.entity.XMLItem;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;

public class XMLUtils {

    public static Document init() {
        Document document = DocumentHelper.createDocument();// 建立document对象，用来操作xml文件
        Element ecdsElement = document.addElement("ecds");// 建立根节点
        Element headerElement = ecdsElement.addElement("header");
        Element bodyElement = ecdsElement.addElement("body");
//        Element forElement = bodyElement.addElement("for");
//        forElement.addAttribute("prefix", "");
        Element footElement = ecdsElement.addElement("foot");
        Element styleElement = ecdsElement.addElement("style");
        return document;
    }

    public static void setItem(Element itemElement, XMLItem xmlItem) {
        itemElement.addAttribute("isdata", xmlItem.getIsData());
        itemElement.addAttribute("isnewrow", xmlItem.getIsNewRow());
        if ("false".equals(xmlItem.getIsData())){
            itemElement.addAttribute("datasource", "null");
            itemElement.setText(xmlItem.getContent());
        } else {
            itemElement.addAttribute("datasource", xmlItem.getContent());
        }
        itemElement.addAttribute("colspan", xmlItem.getColspan().toString());
        itemElement.addAttribute("rowspan", xmlItem.getRowspan().toString());
        itemElement.addAttribute("alignment", xmlItem.getAlignment());
        itemElement.addAttribute("verticalalignment", xmlItem.getVerticalAlignment());
        itemElement.addAttribute("fontname", xmlItem.getFontName());
        itemElement.addAttribute("fontheight", xmlItem.getFontHeight().toString());
        itemElement.addAttribute("fontcolor", xmlItem.getFontColor());

    }

    public static void addItem(Document document, String xpath, XMLItem xmlItem) {
        Element element = (Element)document.selectSingleNode(xpath);
        if("/ecds/body/for".equals(xpath)){
            element.attribute("prefix").setValue(xmlItem.getPrefix());
        }
        Element itemElement = element.addElement("item");
        setItem(itemElement, xmlItem);
    }

    public static void addStyleItem(Document document, List<Integer> hightList, List<Integer> widthList){
        Element element = (Element)document.selectSingleNode("/ecds/style");
        for (Integer i = 0; i < hightList.size(); i++) {
            Element heightElement = element.addElement("height");
            heightElement.addAttribute("index", i.toString());
            heightElement.setText(hightList.get(i).toString());
        }
        for (Integer i = 0; i < widthList.size(); i++) {
            Element widthElement = element.addElement("width");
            widthElement.addAttribute("index", i.toString());
            widthElement.setText(widthList.get(i).toString());
        }
    }

    public static void write(Document document, String path) throws IOException {
        XMLWriter writer = new XMLWriter(new FileWriter(new File(path)));
        writer.write(document);
        writer.close();
    }

    public static void write(Document document, Writer writer) throws IOException {
        XMLWriter xmlWriter = new XMLWriter(writer);
        xmlWriter.write(document);
        writer.close();
    }

    public static Document read(String path) throws IOException, DocumentException {
        // 获取文档对象
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new FileInputStream(path));
        return doc;
    }

    public static Document read(Reader reader) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(reader);
    }

}
