package com.bosssoft.ecds.utils;


import com.bosssoft.ecds.exception.CustomException;
import com.bosssoft.ecds.response.CommonCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author 吴志鸿
 * @date 2020/8/19
 * @description
 */
@Slf4j
public class ExcelUtil {

    public static Sheet importExcelWithSimple(MultipartFile file) {

        //得到工作空间
        Workbook workbook = null;
        try {
            workbook = getWorkbookByInputStream(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //得到工作表
        Sheet sheet = getSheetByWorkbook(workbook, 0);
        if (sheet.getRow(2000) != null) {
            throw new RuntimeException("系统已限制单批次导入必须小于或等于2000笔！");
        }
        return sheet;
    }

    public static void validCellValue(Sheet sheet, Row row, int colNum, String errorHint) {
        if ("".equals(getCellValue(sheet, row, colNum - 1))) {
            throw new CustomException(CommonCode.FAIL, "校验 :第" + (row.getRowNum() + 1) + "行" + colNum + "列" + errorHint + "不能为空");
        }
    }

    public static String getCellValue(Sheet sheet, Row row, int column) {
        if (sheet == null || row == null) {
            return "";
        }

        return getCellValue(row.getCell(column));
    }

    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                String numberStr = null;
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date tempValue = cell.getDateCellValue();
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                    numberStr = simpleFormat.format(tempValue);
                }else {
                    Number number = cell.getNumericCellValue();
                    numberStr = String.valueOf(number);
                }

                if (numberStr.endsWith(".0")) {
                    numberStr = numberStr.replace(".0", "");//取整数
                }

                return numberStr;
            case STRING:
                return cell.getStringCellValue().trim();
            case FORMULA://公式
                return "";
            case BLANK:
                return "";
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                break;
        }

        return "";
    }

    public static boolean isBlankRow(Row row) {
        if (row == null) {
            return true;
        }

        Iterator<Cell> iter = row.cellIterator();
        while (iter.hasNext()) {
            Cell cell = iter.next();
            if (cell == null) {
                continue;
            }

            String value = getCellValue(cell);
            if (!isNULLOrBlank(value)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNULLOrBlank(Object obj) {
        if (obj != null && !"".equals(obj.toString())) {
            return false;
        }

        return true;
    }

    private static Sheet getSheetByWorkbook(Workbook workbook, int index) {
        Sheet sheet = workbook.getSheetAt(index);
        if (null == sheet) {
            sheet = workbook.createSheet();
        }

        sheet.setDefaultRowHeightInPoints(20);//行高
        sheet.setDefaultColumnWidth(20);//列宽

        return sheet;
    }

    private static Workbook getWorkbookByInputStream(InputStream iStream, String fileName) {
        Workbook workbook = null;

        try {
            if (null == fileName) {
                return null;
            }

            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(iStream);
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(iStream);
            } else {
                throw new IOException("The document type don't support");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (iStream != null) {
                try {
                    iStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return workbook;
    }
}
