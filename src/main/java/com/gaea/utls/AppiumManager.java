package com.gaea.utls;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AppiumManager {

    private static String strText;

    public static void main(String[] args) {


    }

    public static void appiumManagement(AppiumDriver driver, String infoValue, String position, String positionValue, String operation, String operationValue) throws Exception {

        WebElement element;

        switch (position) {

            case "Thread":
                Thread.sleep(Integer.parseInt(positionValue));
                break;

            case "swipe":
                String[] swipeInfo = positionValue.split(",");
                driver.swipe(Integer.parseInt(swipeInfo[0]), Integer.parseInt(swipeInfo[1]), Integer.parseInt(swipeInfo[2]), Integer.parseInt(swipeInfo[3]), Integer.parseInt(swipeInfo[4]));
                break;

            case "assert":
                checkInfo(positionValue, strText, infoValue);
                break;

            default:

                switch (position) {

                    case "id":
                        element = getElementWait(driver, By.id(positionValue));
                        break;

                    case "className":
                        element = getElementWait(driver, By.className(positionValue));
                        break;

                    default:
                        element = getElementWait(driver, By.className(positionValue));
                        break;
                }

                switch (operation) {

                    case "click":
                        element.click();
                        break;

                    case "sendkeys":
                        element.sendKeys(operationValue);
                        break;

                    case "getText":
                        strText = element.getText();
                        break;

                    case "clear":
                        element.clear();
                        break;
                }
                break;
        }

    }

    private static void checkInfo(String str1, String str2, String info) {

        if (str1.equals(str2)) {

            System.out.println(info + "通过");
        } else {

            System.out.println(info + "失败");
            System.out.println("期望结果：" + str1 + "，实际结果：" + str2);
        }

    }


    private static WebElement getElementWait(AppiumDriver driver, By by) {

        WebDriverWait wait = new WebDriverWait(driver, 5);

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }
}
