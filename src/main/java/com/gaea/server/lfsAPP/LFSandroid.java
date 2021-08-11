package com.gaea.server.lfsAPP;

import com.gaea.utls.AppiumManager;
import com.gaea.utls.ExcelTest;
import com.gaea.utls.FileManage;
import io.appium.java_client.AppiumDriver;

public class LFSandroid {

    private static String[] caseName, info, position, positionValue, operation, operationValue;

    public static void main(String[] args) throws Exception {

        AppiumDriver driver = DeviceInit.initDriver();

        int rows = ExcelTest.getRows(FileManage.lfsCasePath, "旅法师营地登录");

        getValue("旅法师营地登录",FileManage.lfsCasePath);

        for (int i = 1; i < rows; i++) {

            AppiumManager.appiumManagement(driver,info[i],position[i],positionValue[i],operation[i],operationValue[i]);
        }

    }

    private static void getValue(String sheetName, String casePath) throws Exception {

        caseName = ExcelTest.getCell(casePath, sheetName, 0);

        info = ExcelTest.getCell(casePath, sheetName, 1);

        position = ExcelTest.getCell(casePath, sheetName, 2);

        positionValue = ExcelTest.getCell(casePath, sheetName, 3);

        operation = ExcelTest.getCell(casePath, sheetName, 4);

        operationValue = ExcelTest.getCell(casePath, sheetName, 5);

    }

}
