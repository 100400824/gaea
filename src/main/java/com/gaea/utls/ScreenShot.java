package com.gaea.utls;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenShot {


    public static void doScreentShot(TakesScreenshot drivername, String filename) {

        File scrFile = drivername.getScreenshotAs(OutputType.FILE);

        try {

            FileUtils.copyFile(scrFile, new File(FileManage.screenShot + FileManage.xx + filename.replace("/", "") + ".png"));

        } catch (IOException e) {

            // TODO Auto-generated catch block
            System.out.println("Can't save screenshot");
            e.printStackTrace();
        }
    }
}