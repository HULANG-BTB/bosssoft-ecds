package com.bosssoft.ecds.template.util.excel;

import com.bosssoft.ecds.template.entity.Excel;
import com.bosssoft.ecds.template.entity.MergeResult;
import com.bosssoft.ecds.template.entity.XMLItem;
import lombok.Data;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExcelUtils {
    /**
     * 获取excel对应的三个对象
     * @param path
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static Excel getExcel(String path, int index) throws IOException, InvalidFormatException {
        return new Excel(path,index);
    }

    public static Excel getExcel(InputStream is, int index) throws IOException, InvalidFormatException {
        return new Excel(is, index);
    }

    /**
     * 对资源进行释放
     * @param excel
     * @throws IOException
     */
    public static void close(Excel excel) throws IOException {
        excel.close();
    }

    /**
     * 传入单元格对象，获取单元格的列宽
     * @param cell
     * @return
     */
    public static int getWidth(XSSFCell cell) {
        return (int)((cell.getSheet().getColumnWidthInPixels(cell.getColumnIndex())/7.5)*2.2862);
    }

    /**
     * 传入单元格对象，获取单元格的行高
     * @param cell
     * @return
     */
    public static int getHeight(XSSFCell cell) {
        return (int)(cell.getRow().getHeightInPoints()*0.3612);
    }

    /**
     * 传入单元格对象，获取单元格的水平对齐方式
     * @param cell
     * @return
     */
    public static String getAlignment(XSSFCell cell) {
       return cell.getCellStyle().getAlignmentEnum().toString();
    }

    /**
     * 传入单元格对象，获取单元格的垂直对齐方式
     * @param cell
     * @return
     */
    public static String getVerticalAlignment(XSSFCell cell) {
        return cell.getCellStyle().getVerticalAlignmentEnum().toString();
    }

    /**
     * 传入单元格对象，获取单元格的字体名称
     * @param cell
     * @return
     */
    public static String getFontName(XSSFCell cell) {
        return cell.getCellStyle().getFont().getFontName();
    }

    /**
     * 传入单元格对象，获取单元格字体大小
     * @param cell
     * @return 返回值为字号大小
     */
    public static int getFontHeight(XSSFCell cell) {
       return (int)cell.getCellStyle().getFont().getFontHeightInPoints();
    }


    /**
     * 传入单元格对象，获取单元格字体颜色
     * @param cell
     * @return 返回值为字符串 每两位为一个值分别为A R G B，16进制
     */
    public static String getFontColor(XSSFCell cell) {
        return cell.getCellStyle().getFont().getXSSFColor().getARGBHex();
    }

    /**
     * 传入单元格对象，获取单元格背景填充色
     * @param cell
     * @return 返回值为字符串 每两位为一个值分别为A R G B，16进制
     */
    public static String getBackgroundColor(XSSFCell cell) {
        return cell.getCellStyle().getFillForegroundXSSFColor().getARGBHex();
    }

    /**
     * 传入ARGB的字符串形式，返回一个数组列表为[255,255,255,255]的格式表示的ARGB
     * @param str
     * @return 返回值为 一个列表，内容分别是A R G B
     */
    public static List<Integer> getARGBList(String str) {
        List<Integer> list = new ArrayList<>();
        if (str.length()!=8){
            return list;
        }
        list.add(Integer.parseInt(str.substring(0,2),16));
        list.add(Integer.parseInt(str.substring(2,4),16));
        list.add(Integer.parseInt(str.substring(4,6),16));
        list.add(Integer.parseInt(str.substring(6,8),16));
        return list;
    }

    /**
     * 获取单元格内容
     * @param cell
     * @return
     */
    public static String getContent(XSSFCell cell) {
        if (cell.getStringCellValue()==""){
            return null;
        } else {
            return cell.getStringCellValue();
        }
    }

    /**
     * 判断该单元格是否为合并单元格
     * @param sheet 传入sheet对象
     * @param row 传入对应的单元格的行数可以用cell.getRowIndex()获取
     * @param column 传入对应的单元个的列数可以用cell.getColumnIndex()获取
     * @return 返回一个结果对象，第一个参数是是否为合并单元格，后四个参数分别是起始行数，
     * 结束行数，起始列数，结束列数，如果不是合并单元格则全为0，返回的下标经处理均为1为起始，excel的起始是0
     */
    public static MergeResult isMergedRegion(Sheet sheet, int row , int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return new MergeResult(true,firstRow+1,lastRow+1,firstColumn+1,lastColumn+1);
                }
            }
        }
        return new MergeResult(false,0,0,0,0);
    }

    public static MergeResult[][] mergeFlag(Excel excel, List<Integer> hightList, List<Integer> widthList) {
        int rowNum = excel.getSheet().getLastRowNum()+1;
        int coloumNum=excel.getSheet().getRow(0).getPhysicalNumberOfCells();

        MergeResult mergeFlag[][] = new MergeResult[rowNum][coloumNum];

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < coloumNum; j++) {
                if(i == 0) {
                    widthList.add(getWidth(excel.getSheet().getRow(i).getCell(j)));
                }
                if(j == 0) {
                    hightList.add(getHeight(excel.getSheet().getRow(i).getCell(j)));
                }
                MergeResult mergeResult = ExcelUtils.isMergedRegion(excel.getSheet(), i, j);
                if (mergeResult.getIsMerge()) {
                    if (i == mergeResult.getStartRow() - 1 && j == mergeResult.getStartColumn() - 1) {
                        mergeFlag[i][j] = mergeResult;
                    } else {
                        mergeFlag[i][j] = new MergeResult(true, 0, 0, 0, 0);
                    }
                } else {
                    mergeFlag[i][j] = new MergeResult(false,0,0,0,0);
                }
            }
        }
        return mergeFlag;
    }

    public static boolean mergeSkip(MergeResult mergeFlag) {
        if (mergeFlag.getIsMerge()&&mergeFlag.getStartRow()==0&&mergeFlag.getEndRow()==0&&mergeFlag.getStartColumn()==0&&mergeFlag.getEndColumn()==0){
            return true;
        } else {
            return false;
        }
    }

    public static XMLItem createItem(XSSFCell cell, MergeResult mergeResult, int newRowFlag, Integer i){
        XMLItem xmlItem = new XMLItem();
        //FFFFC000   FF92D050   FF00B0F0
        if ("FFFFC000".equals(getBackgroundColor(cell))){
            xmlItem.setParent("/ecds/header");
        }
        if ("FF92D050".equals(getBackgroundColor(cell))){
            xmlItem.setParent("/ecds/body");
        }
        if ("FF00B0F0".equals(getBackgroundColor(cell))){
            xmlItem.setParent("/ecds/foot");
        }
        if(((Character)ExcelUtils.getContent(cell).charAt(0)).hashCode() == 64){
            xmlItem.setIsData("true");
            xmlItem.setContent(getContent(cell).substring(1));
        } else if (((Character)ExcelUtils.getContent(cell).charAt(0)).hashCode() == 35){
            String str = getContent(cell);
            String[] strlist= str.substring(1).split("\\.");
            xmlItem.setPrefix(strlist[0]);
            xmlItem.setIsData("true");
            xmlItem.setContent(strlist[1]);
            xmlItem.setParent("/ecds/body/for");
        } else {
            xmlItem.setIsData("false");
            xmlItem.setContent(getContent(cell));
        }
        if (mergeResult.getIsMerge()){
            xmlItem.setColspan(mergeResult.getEndColumn()-mergeResult.getStartColumn()+1);
            xmlItem.setRowspan(mergeResult.getEndRow()-mergeResult.getStartRow()+1);
        } else {
            xmlItem.setColspan(1);
            xmlItem.setRowspan(1);
        }
        xmlItem.setAlignment(getAlignment(cell));
        xmlItem.setVerticalAlignment(getVerticalAlignment(cell));
        xmlItem.setFontName(getFontName(cell));
        xmlItem.setFontHeight(getFontHeight(cell));
        xmlItem.setFontColor(getFontColor(cell));
        if(newRowFlag==0){
            xmlItem.setIsNewRow(i.toString());
        } else {
            xmlItem.setIsNewRow("false");
        }
        return xmlItem;
    }
}
