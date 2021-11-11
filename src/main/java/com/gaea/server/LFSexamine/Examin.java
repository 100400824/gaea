package com.gaea.server.LFSexamine;

import com.gaea.utls.web.ChromeDriver;
import com.gaea.utls.publicTool.GetTime;
import com.gaea.utls.publicTool.Mail;
import com.gaea.utls.web.SeleniumManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Examin {

    public static void main(String[] args) throws Exception {

        String url = "https://www-test.iyingdi.com/tz/login";
        String userName = "86_13300000005";
        String passWord = "123123";
        String minute = "1";
        String sendToEmal = "peiwei.zhang@gaea.com";
        doExamin(url, userName, passWord, minute, sendToEmal);
    }

    public static void doExamin(String url, String userName, String passWord, String minute, String sendToEmal) throws Exception {

        WebDriver driver = ChromeDriver.getChromeDriver();

        driver.manage().window().maximize();

        initExamin(driver, url, userName, passWord);

        int index = 0;

        while (index <= 3) {

            try {

                String description = GetTime.getNowTime(GetTime.dateFormat1);

                SeleniumManager.getElementWait(driver, By.xpath("//textarea")).clear();

                SeleniumManager.getElementWait(driver, By.xpath("//textarea")).sendKeys(description);

                SeleniumManager.getElementWait(driver, By.xpath("(//span[contains(text(),'确认')])[1]")).click();

                String addStr = SeleniumManager.getElementWait(driver, By.xpath("//textarea")).getAttribute("value");

                System.out.println("添加的个性签名：" + addStr);

                //等待刷新页面
                Thread.sleep(1000L * 60 * Integer.parseInt(minute));

                driver.navigate().refresh();
                Thread.sleep(5000);

                String getDescription;
                int getNum = 0;

                //刷新页面后获取最新的个性签名
                do {
                    getDescription = SeleniumManager.getElementWait(driver, By.xpath("//textarea")).getAttribute("value");
                    getNum++;

                } while (getDescription.equals("") && getNum <= 10);

                //触发报警邮件
                if (!getDescription.equals(description)) {

                    driver.navigate().refresh();
                    System.out.println("期望：" + description);
                    System.out.println("实际：" + getDescription);

                    if (driver.getCurrentUrl().contains("tz/setting") && !getDescription.equals("")) {

                        String content = description + "个性签名变更，已等待" + minute + "分钟审核未通过。";

                        Mail.toEmail("审核平台监控报警邮件", content, sendToEmal);

                    } else if ((index + 1) <= 3) {
                        System.out.println("期望：" + "tz/setting");
                        System.out.println("实际：" + driver.getCurrentUrl());

                        driver.quit();

                        driver = ChromeDriver.getChromeDriver();

                        driver.manage().window().maximize();

                        initExamin(driver, url, userName, passWord);

                        String myEmal = "peiwei.zhang@gaea.com";

                        Mail.toEmail("监控程序异常提示" + (index + 1), "个性签名监控程序异常，请上线检查。", myEmal);
                    }

                    index++;

                } else {

                    index = 0;
                }

            } catch (Exception e) {

                index++;

                driver.quit();

                driver = ChromeDriver.getChromeDriver();

                driver.manage().window().maximize();

                initExamin(driver, url, userName, passWord);

                String myEmal = "peiwei.zhang@gaea.com";

                Mail.toEmail("监控程序异常提示" + (index + 1), "个性签名修改程序异常，请上线检查。", myEmal);
            }

        }

    }

    //登录-进入个人中心-资料设置
    public static void initExamin(WebDriver driver, String url, String userName, String passWord) throws Exception {

        driver.get(url);

        Thread.sleep(2000);

        SeleniumManager.getElementWait(driver, By.xpath("(//input[@placeholder='请选择'])[2]")).click();

        Thread.sleep(1000);

        SeleniumManager.getElementWait(driver, By.xpath("//span[contains(text(),'账号密码登录')]")).click();

        SeleniumManager.getElementWait(driver, By.name("username")).sendKeys(userName);

        SeleniumManager.getElementWait(driver, By.name("password")).sendKeys(passWord);

        SeleniumManager.getElementWait(driver, By.xpath("//span[@class='el-checkbox__inner']")).click();

        SeleniumManager.getElementWait(driver, By.xpath("//button[@type='submit']")).click();

        //进入资料设置
        SeleniumManager.getElementWait(driver, By.xpath("//div[@class=' el-dropdown-selfdefine']")).click();

        SeleniumManager.getElementWait(driver, By.xpath("//span[contains(text(),'资料设置')]")).click();
    }

}
