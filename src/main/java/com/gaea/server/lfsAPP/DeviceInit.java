package com.gaea.server.lfsAPP;

import com.gaea.utls.ExcelTest;
import com.gaea.utls.FileManage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class DeviceInit {

    private static String[] dveiceMessage;

    public static void main(String[] args) throws Exception {


    }

    public static AppiumDriver initDriver() throws Exception {

        System.out.println("初始化appium服务器V1.0.1。。。");

        String sheetName = "deviceManager";

        getDeviceValue(sheetName, FileManage.lfsCasePath);

        String dveiceID = dveiceMessage[1];
        String apkPath = dveiceMessage[2];
        String packgeName = dveiceMessage[3];
        String avtivetyName = dveiceMessage[4];

        File app = new File(apkPath);

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //放开注释重新安装APP
//        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsoluteFile());

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, dveiceID);

        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,packgeName);

        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, avtivetyName);

        capabilities.setCapability("newCommandTimeout", 600);//设置连接超时时间

        capabilities.setCapability("unicodeKeyboard", true);//使用 Unicode 输入法

        capabilities.setCapability("resetKeyboard", true);  //重置输入法到原有状态

        return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    private static void getDeviceValue(String sheetName, String casePath) throws Exception {

        dveiceMessage = ExcelTest.getCell(casePath, sheetName, 2);
    }


}
