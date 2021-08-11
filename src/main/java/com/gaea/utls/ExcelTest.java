package com.gaea.utls;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelTest {

	/**
	 * @throws Exception
	 * @return获得EXCEL表中的元素
	 * 
	 */

	public static void main(String[] args) throws Exception {

	}

	@SuppressWarnings("resource")
	public static int getRows(String filePath, String sheet) throws Exception {

		String path = filePath;

		XSSFWorkbook excelWBook;

		XSSFSheet excelWSheet;

		FileInputStream ExcelFile = new FileInputStream(path);

		excelWBook = new XSSFWorkbook(ExcelFile);

		excelWSheet = excelWBook.getSheet(sheet);

		int rows = excelWSheet.getPhysicalNumberOfRows();

		return rows;

	}

	@SuppressWarnings("resource")
	public static String[] getCell(String filePath, String sheet, int getCell) throws Exception {

		String path = filePath;

		String cellValue = null;

		XSSFWorkbook excelWBook;

		XSSFSheet excelWSheet;

		FileInputStream ExcelFile = new FileInputStream(path);

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
			} catch (Exception e) {

			}

			rowAll[i] = cellValue;
		}

		return rowAll;
	}
	
	@SuppressWarnings("resource")
	public static String[] getRows(String filePaht, String sheet, int cell, int column) throws Exception {

		String path = filePaht;

		String cellValue = null;

		XSSFWorkbook excelWBook;

		XSSFSheet excelWSheet;

		FileInputStream ExcelFile = new FileInputStream(path);

		excelWBook = new XSSFWorkbook(ExcelFile);

		excelWSheet = excelWBook.getSheet(sheet);

		String[] rowAll = new String[column];

		for (int i = 0; i < column; i++) {

			try {

				cellValue = excelWSheet.getRow(cell).getCell(i).getStringCellValue();

			} catch (Exception e) {

				// cellValue = "" + (int)
				// excelWSheet.getRow(1).getCell(i).getNumericCellValue();

				System.out.println(e);
			}

			rowAll[i] = cellValue;
		}

		return rowAll;

	}

}
