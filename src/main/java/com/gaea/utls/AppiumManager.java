package com.gaea.utls;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.util.List;
import java.util.UUID;


public class AppiumManager {

    private static String getText, sendKeysStr;

    public static void main(String[] args) {


    }

    public static void appiumManagement(AppiumDriver driver, String caseIndex, String infoValue, String position, String positionValue, String operation, String operationValue, FileWriter pfp) throws Exception {

        WebElement element;

        switch (position) {

            case "sleep":
                Thread.sleep(Integer.parseInt(positionValue));
                break;

            case "swipe":
                String[] swipeInfo = positionValue.split(",");
                driver.swipe(Integer.parseInt(swipeInfo[0]), Integer.parseInt(swipeInfo[1]), Integer.parseInt(swipeInfo[2]), Integer.parseInt(swipeInfo[3]), Integer.parseInt(swipeInfo[4]));
                break;

            case "assert":
                if (positionValue.equals("sendkeys")) {
                    Loginfo.checkInfo("equals", sendKeysStr, getText, infoValue, pfp);
                } else {
                    Loginfo.checkInfo("equals", positionValue, getText, infoValue, pfp);
                }
                break;

            case "assertContains":
                Loginfo.checkInfo("contains", getText, positionValue, infoValue, pfp);
                break;

            case "tapPoint":
                int xPoint = Integer.parseInt((positionValue.split(","))[0]);
                int yPoint = Integer.parseInt((positionValue.split(","))[1]);
                (new TouchAction(driver)).tap(xPoint, yPoint).perform();
                break;

            default:

                switch (position) {

                    case "noId":
                        try {
                            element = getElementWait(driver, By.id(positionValue));
                            Loginfo.checkInfo("equals", "元素不存在", "元素仍然存在", infoValue, pfp);
                        }catch (Exception e) {
                            element = getElementWait(driver, By.xpath("//*"));
                        }
                        break;

                    case "id":
                        element = getElementWait(driver, By.id(positionValue));
                        break;

                    case "className":
                        element = getElementWait(driver, By.className(positionValue));
                        break;

                    case "xpath":
                        element = getElementWait(driver, By.xpath(positionValue));
                        break;

                    case "xpathLast":
                        List elements = getElementsWait(driver, By.xpath(positionValue));
                        element = (WebElement) elements.get(elements.size() - 1);
                        break;

                    default:
                        element = getElementWait(driver, By.xpath(positionValue));
                        break;
                }

                switch (operation) {

                    case "click":
                        element.click();
                        break;

                    case "tapLong":
                        TouchAction ta = new TouchAction(driver);
                        ta.longPress(element, Integer.parseInt(operationValue)).release().perform();
                        break;

                    case "sendkeys":
                        String gaeaUUID = "gaeaUUID";
                        String uuid = UUID.randomUUID().toString().replace("-", "");
                        if (operationValue.contains(gaeaUUID)) {
                            sendKeysStr = operationValue.replace(gaeaUUID, "") + uuid;
                            element.sendKeys(sendKeysStr);
                        } else {
                            element.sendKeys(operationValue);
                        }
                        break;

                    case "getText":
                        getText = element.getText();
                        break;

                    case "clear":
                        element.clear();
                        break;
                }
                break;
        }

        Loginfo.printLog(caseIndex, infoValue, pfp);

    }

    //间隔500ms查询一次元素信息
    private static WebElement getElementWait(AppiumDriver driver, By by) throws Exception {
        /*WebDriverWait wait = new WebDriverWait(driver, 6);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));*/
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

    //间隔500ms查询一次元素信息
    private static List getElementsWait(AppiumDriver driver, By by) throws Exception {
        /*WebDriverWait wait = new WebDriverWait(driver, 6);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));*/
        for (int i = 1; i < 60; i++) {
            try {
                return driver.findElements(by);
            } catch (Exception e) {
                Thread.sleep(100);
                i++;
            }
        }

        return driver.findElements(by);
    }
}
