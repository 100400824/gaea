package com.gaea.utls.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumManager {

    public static WebElement getElementWait(WebDriver driver, By by) throws Exception {

        Thread.sleep(100);

        for (int i = 1; i < 60; i++) {

            try {
                return driver.findElement(by);

            } catch (Exception e) {

                Thread.sleep(100);
                i++;
            }
        }
        return driver.findElement(by);
    }
}
