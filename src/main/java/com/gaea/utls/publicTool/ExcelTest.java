package com.gaea.utls.publicTool;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelTest {


    public static void main(String[] args) throws Exception {

    }

    public static int getRows(String filePath, String sheet) throws Exception {

        XSSFWorkbook excelWBook;

        XSSFSheet excelWSheet;

        FileInputStream ExcelFile = new FileInputStream(filePath);

        excelWBook = new XSSFWorkbook(ExcelFile);

        excelWSheet = excelWBook.getSheet(sheet);

        return excelWSheet.getPhysicalNumberOfRows();

    }

    //获取每一列元素
    public static String[] getCell(String filePath, String sheet, int getCell) throws Exception {

        String cellValue = null;

        XSSFWorkbook excelWBook;

        XSSFSheet excelWSheet;

        FileInputStream ExcelFile = new FileInputStream(filePath);

        excelWBook = new XSSFWorkbook(ExcelFile);

        excelWSheet = excelWBook.getSheet(sheet);

        int rows = excelWSheet.getPhysicalNumberOfRows();

        String[] rowAll = new String[rows];

        for (int i = 0; i < rows; i++) {

            try {
                try {

                    cellValue = excelWSheet.getRow(i).getCell(getCell).getStringCellValue();

                } catch (Exception e) {

                    cellValue = "" + (int) excelWSheet.getRow(i).getCell(getCell).getNumericCellValue();
                }
            } catch (Exception ignored) {

            }

            rowAll[i] = cellValue;
        }

        return rowAll;
    }

    //获取每一行的元素，column控制从第1列开始到第N列
    public static String[] getRows(String filePaht, String sheet, int cell, int column) throws Exception {

        String cellValue = null;

        XSSFWorkbook excelWBook;

        XSSFSheet excelWSheet;

        FileInputStream ExcelFile = new FileInputStream(filePaht);

        excelWBook = new XSSFWorkbook(ExcelFile);

        excelWSheet = excelWBook.getSheet(sheet);

        String[] rowAll = new String[column];

        for (int i = 0; i < column; i++) {

            try {
                try {
                    cellValue = excelWSheet.getRow(cell).getCell(i).getStringCellValue();

                } catch (Exception e) {
                    cellValue = "" + (int) excelWSheet.getRow(cell).getCell(i).getNumericCellValue();
                }
            }catch (Exception e){}

            rowAll[i] = "" + cellValue;
        }

        return rowAll;

    }

}
