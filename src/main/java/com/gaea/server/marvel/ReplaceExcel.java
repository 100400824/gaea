package com.gaea.server.marvel;

import com.gaea.utls.publicTool.ExcelTest;

import java.util.Arrays;

public class ReplaceExcel {

    public static void main(String[] args) throws Exception {

        String xpath1 = "C:\\Users\\Administrator\\Desktop\\gaea\\漫威对决\\1210\\card-1201.xlsx";
        String xpath2 = "C:\\Users\\Administrator\\Desktop\\gaea\\漫威对决\\1210\\card-1209.xlsx";
        String sheetName = "card1";
        getExcelValue(xpath1, xpath2, sheetName);

    }

    public static void getExcelValue(String xpath1, String xpath2, String sheetName) throws Exception {

        int rows = ExcelTest.getRows(xpath1, sheetName);

        for (int i = 0; i < rows; i++) {

            String[] valueArr1 = ExcelTest.getRows(xpath1, sheetName, i, 17);
            String[] valueArr2 = ExcelTest.getRows(xpath2, sheetName, i, 17);

            String str1 = Arrays.asList(valueArr1).toString();
            String str2 = Arrays.asList(valueArr2).toString();

            boolean isP = str1.equals(str2);

            if (!isP) {
                if (valueArr1[16].equals("1星")) {
                    System.out.println(i + 1);
                    System.out.println(str1);
                    System.out.println(str2);
                }
            }

        }
    }


}
