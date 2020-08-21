package com.bosssoft.ecds.template.util.excel;

import com.bosssoft.ecds.template.entity.XMLItem;

import java.util.List;

public class FtlUtils {
    public static String init(List<Integer> widthList) {
        String  str = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"font-family: SimSun\">\n" +
                "    <table style=\"border-collapse:collapse;margin: 0 auto;\">\n" +
                "        <tr style=\"height: 0px;\">\n";
        for(int i = 0; i < widthList.size(); i++) {
            str += "            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: "+((Integer)(widthList.get(i)*5)).toString()+"px;\"></td>\n";
        }
        return str;
    }

    public static String createTd(XMLItem item , String styleStr, String contentStr){
        return "            <td colspan=\""+item.getColspan()+"\" rowspan=\""+item.getRowspan()+"\" style=\"text-align: "+item.getAlignment()+";font-size: "+(item.getFontHeight()+4)+"px;"+styleStr+"\" valign=\""+item.getVerticalAlignment()+"\">"+contentStr+"</td>\n";
    }

    public static String itemToFtl(XMLItem item, List<Integer> heightList, int startFlag, int endFlag, String DTOName) {
        String str = "";
        String styleStr = "";
        String contentStr="";
        if("true".equals(item.getIsData())) {
            if (item.getPrefix()==null) {
                contentStr+="${("+DTOName+"."+item.getContent()+")!' '}";
            } else {
                contentStr+="${(item."+item.getContent()+")!' '}";
            }
        } else {
            contentStr+=item.getContent();
        }
        if ("false".equals(item.getIsNewRow())){}else{
            if(startFlag == 1 && endFlag == 0) {
                str += "            </tr>\n" +
                        "        </#list>\n" +
                        "        <tr>\n";
            } else if(startFlag == 1 && endFlag ==1) {
                str += "        </tr>\n" +
                        "        <#list "+DTOName+"."+item.getPrefix()+" as item>\n" +
                        "            <tr>\n";
            } else {
                str += "        </tr>\n        <tr>\n";
            }
            styleStr += "height: "+heightList.get(Integer.parseInt(item.getIsNewRow()))*5+"px;";
        }
        if ("body".equals(item.getParent()) || "for".equals(item.getParent())){
            styleStr += "border:2px solid black;";
            str += createTd(item, styleStr, contentStr);
        } else {
            str += createTd(item, styleStr, contentStr);
        }
        return str;
    }

    public static String end(){
        return "        </tr>\n" +
                "    </table>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
}
