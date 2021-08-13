package com.gaea.server.lfsAPP;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class DeviceInit {

    public static void main(String[] args) throws Exception {


    }

    public static AppiumDriver initDriver() throws Exception {

        System.out.println("初始化appium服务器。。。");

        String dveiceID = "SJE0217726000926";

        String apkPath = "C:\\Users\\Administrator\\Desktop\\gaea\\旅法师营地\\0806\\iyd_v8.3.1_2021-08-06_debug_baidu.apk";

        String packgeName = "com.gonlan.iplaymtg";

        String avtivetyName = "com.gonlan.iplaymtg.common.StartActivity";

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

        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        AndroidDriver androidDriver = (AndroidDriver) driver;

        return driver;

    }


}
