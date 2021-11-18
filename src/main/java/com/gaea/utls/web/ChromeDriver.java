package com.gaea.utls.web;

import com.gaea.utls.FileManage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;

public class ChromeDriver {

    private static WebDriver driver;

    public static WebDriver getChromeDriver() {

        Properties props = System.getProperties(); //获得系统属性集

        String osName = props.getProperty("os.name"); //操作系统名称

        //当前运行系统非windows不启动浏览器
        if (osName.contains("Linux")) {

            System.out.println("linux下启动selenium");

            // chrome 驱动的位置
            System.setProperty("webdriver.chrome.driver", FileManage.chromeDriverLinux);

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless");

            options.addArguments("window-size=1920x1080");

            return new org.openqa.selenium.chrome.ChromeDriver(options);

        } else if (osName.contains("Windows")) {

            System.out.println("windows下启动selenium");

            // chrome 驱动的位置
            System.setProperty("webdriver.chrome.driver", FileManage.chromeDriver);

            return new org.openqa.selenium.chrome.ChromeDriver();

        } else if (osName.contains("M")) {

            System.out.println("Mac下启动selenium");

            // chrome 驱动的位置
            System.setProperty("webdriver.chrome.driver", FileManage.chromeDriverMac);

            return new org.openqa.selenium.chrome.ChromeDriver();

        } else {

            return driver;
        }
    }
}
