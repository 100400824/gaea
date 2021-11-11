package com.gaea.server.Test;

import com.gaea.utls.publicTool.ScreenShot;
import com.gaea.utls.web.ChromeDriver;
import com.gaea.utls.web.SeleniumManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class pashuju {

    public static void main(String[] args) throws Exception {

        WebDriver driver = ChromeDriver.getChromeDriver();
        driver.manage().window().maximize();

        int i=1;

        try {

            while (true) {
                i++;
                driver.get("https://www.iyingdi.com/tz/post/5142656");
                Thread.sleep(2000);
                SeleniumManager.getElementWait(driver,By.xpath("//*[@id=\"__layout\"]/div/div[4]/div[1]")).click();
                driver.findElement(By.xpath("//*[@class='comments-area']/div[2]/div/div[2]"));
            }
        }catch (Exception e){

            System.out.println(i);
            ScreenShot.doScreentShot((TakesScreenshot) driver,""+System.currentTimeMillis());
        }



    }



}
