package com.gaea.utls;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.util.List;
import java.util.UUID;


public class AppiumManager {

    private static String getText1, getText2, sendKeysStr;

    public static void main(String[] args) {


    }

    public static void appiumManagement(AppiumDriver driver, String caseIndex, String infoValue, String position, String positionValue, String operation, String operationValue, FileWriter pfp, TakesScreenshot drivername) throws Exception {

        WebElement element;
        String cmdstr;

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

                    Loginfo.checkInfo("equals", sendKeysStr, getText1, infoValue, pfp, drivername);
                } else if (positionValue.equals("getText")) {

                    Loginfo.checkInfo("equals", getText1, getText2, infoValue, pfp, drivername);
                } else {

                    Loginfo.checkInfo("equals", positionValue, getText1, infoValue, pfp, drivername);
                }
                break;

            case "assertContains":
                Loginfo.checkInfo("contains", getText1, positionValue, infoValue, pfp, drivername);
                break;

            case "tapPoint":
                int xPoint = Integer.parseInt((positionValue.split(","))[0]);
                int yPoint = Integer.parseInt((positionValue.split(","))[1]);
                (new TouchAction(driver)).tap(xPoint, yPoint).perform();
                Thread.sleep(500);
                break;

            case "back":
                cmdstr = "adb shell input keyevent 4";
                Runtime.getRuntime().exec(cmdstr).waitFor();
                break;

            case "home":
                cmdstr = "adb shell input keyevent 3";
                Runtime.getRuntime().exec(cmdstr).waitFor();
                break;

            default:

                switch (position) {

                    case "id":
                        element = getElementWait(driver, By.id(positionValue));
                        break;

                    case "noId":
                        try {
                            element = driver.findElement(By.id(positionValue));
                            Loginfo.checkInfo("equals", "元素不存在", "元素仍然存在", infoValue, pfp, drivername);
                        } catch (Exception e) {
                            element = getElementWait(driver, By.xpath("//*"));
                        }
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

                    case "toast":
                        element = getElementWait(driver, By.xpath("//*[@class='android.widget.Toast']"));
                        break;

                    default:
                        element = getElementWait(driver, By.linkText(positionValue));
                        break;
                }

                switch (operation) {

                    case "click":
                        element.click();
                        Thread.sleep(500);
                        break;

                    case "longPress":
                        TouchAction ta = new TouchAction(driver);
                        ta.longPress(element, Integer.parseInt(operationValue)).release().perform();
                        Thread.sleep(500);
                        break;

                    case "sendkeys":
                        String gaeaUUID = "gaeaUUID";
                        String uuid = UUID.randomUUID().toString().replace("-", "").substring(1, 11);
                        if (operationValue.contains(gaeaUUID)) {
                            sendKeysStr = operationValue.replace(gaeaUUID, "") + uuid;
                            element.sendKeys(sendKeysStr);
                        } else {
                            element.sendKeys(operationValue);
                        }
                        Thread.sleep(500);
                        break;

                    case "getText":
                        getText1 = element.getText().replaceAll("[\r\n]", "");
                        break;

                    case "getText2":
                        getText2 = element.getText().replaceAll("[\r\n]", "");
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
