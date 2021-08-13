package com.gaea.server.lfsAPP;

import com.gaea.utls.AppiumManager;
import com.gaea.utls.ExcelTest;
import com.gaea.utls.FileManage;
import com.gaea.utls.Loginfo;
import io.appium.java_client.AppiumDriver;

public class LFSandroid {

    private static String[] caseIndex, info, position, positionValue, operation, operationValue;

    private static String[] runSceneName, runtauts;

    public static void main(String[] args) throws Exception {

        doAppium();
    }

    public static void doAppium() throws Exception {

        String sceneManagerName = "sceneManager";

        int sceneNum = ExcelTest.getRows(FileManage.lfsCasePath, sceneManagerName);

        getScene(sceneManagerName,FileManage.lfsCasePath);

        AppiumDriver driver = DeviceInit.initDriver();

        for (int r = 1; r < sceneNum; r++) {

            if (runtauts[r].equals("run")) {
                Loginfo.printScene(""+r,runSceneName[r]);
                runScene(driver,runSceneName[r]);
            }
        }
        //打印失败用例数
        Loginfo.printErrorNum(Loginfo.errorNum);

    }

    //需要运行的场景
    private static void runScene(AppiumDriver driver ,String runSceneName) throws Exception {

        int rows = ExcelTest.getRows(FileManage.lfsCasePath, runSceneName);

        getCaseValue(runSceneName, FileManage.lfsCasePath);

        for (int i = 1; i < rows; i++) {

            AppiumManager.appiumManagement(driver, caseIndex[i], info[i], position[i], positionValue[i], operation[i], operationValue[i]);
        }

    }

    private static void getScene(String sheetName, String casePath) throws Exception {

        runSceneName = ExcelTest.getCell(casePath, sheetName, 1);
        runtauts = ExcelTest.getCell(casePath, sheetName, 2);
    }

    private static void getCaseValue(String sheetName, String casePath) throws Exception {

        caseIndex = ExcelTest.getCell(casePath, sheetName, 0);
        info = ExcelTest.getCell(casePath, sheetName, 1);
        position = ExcelTest.getCell(casePath, sheetName, 2);
        positionValue = ExcelTest.getCell(casePath, sheetName, 3);
        operation = ExcelTest.getCell(casePath, sheetName, 4);
        operationValue = ExcelTest.getCell(casePath, sheetName, 5);
    }

}
