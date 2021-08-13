package com.gaea.server.lfsAPP;

import com.gaea.Report.ReportHtml;
import com.gaea.utls.*;
import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.io.FileWriter;

public class LFSandroid {

    private static String[] caseIndex, info, position, positionValue, operation, operationValue;

    private static String[] runSceneName, runtauts;

    public static void main(String[] args) throws Exception {

        doAppium();
    }
    public static void doAppium() throws Exception {

        File fp = new File(FileManage.reportPath);

        FileWriter pfp = new FileWriter(fp, true);

        if(fp.exists()) {
            OperationFile.clearFile(FileManage.reportPath);
        }

        runSceneCase(pfp);

        pfp.write("\r\n");

        pfp.close();

        ReportHtml reportHtml = new ReportHtml();

        reportHtml.doHtmlReport(caseNum(FileManage.lfsCasePath));
    }


    public static void runSceneCase(FileWriter pfp) throws Exception {

        String sceneManagerName = "sceneManager";

        int sceneNum = ExcelTest.getRows(FileManage.lfsCasePath, sceneManagerName);

        getScene(sceneManagerName, FileManage.lfsCasePath);

        AppiumDriver driver = DeviceInit.initDriver();

        for (int r = 1; r < sceneNum; r++) {

            if (runtauts[r].equals("run")) {
                Loginfo.printScene("" + r, runSceneName[r],pfp);
                runScene(driver, runSceneName[r],pfp);
            }
        }

        //打印失败用例数
        Loginfo.printErrorNum(Loginfo.errorNum,pfp);
    }

    //需要运行的场景内容
    private static void runScene(AppiumDriver driver, String runSceneName,FileWriter pfp) throws Exception {

        int rows = ExcelTest.getRows(FileManage.lfsCasePath, runSceneName);

        getCaseValue(runSceneName, FileManage.lfsCasePath);

        for (int i = 1; i < rows; i++) {

            try {
                AppiumManager.appiumManagement(driver, caseIndex[i], info[i], position[i], positionValue[i], operation[i], operationValue[i],pfp);
            } catch (Exception e) {
                Loginfo.errorNum++;
                System.out.println("aa:" + info[i]);
                Loginfo.pringSceneError(runSceneName, caseIndex[i], info[i], "" + e,pfp);
                break;
            }
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

    private static int caseNum(String casePath) throws Exception {

        String[] isRun = ExcelTest.getCell(casePath, "sceneManager", 2);
        int index = 0;
        for (String is : isRun) {

            if (is.equals("run")) {

                index++;
            }
        }
        return index;
    }
}
