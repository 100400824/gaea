package com.gaea.server.LFSexamine;

import com.gaea.utls.web.ChromeDriver;
import com.gaea.utls.web.SeleniumManager;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckHomePage {

    final static String NUM = "num";
    final static String ATTIBUTE = "href";

    public static void main(String[] args) throws Exception {
        /*
        目前支持以下页面文章（帖子）查重
        首页
        炉石传说
        */
        System.out.println(checkPageInfo("炉石传说", 20));
    }

    public static String checkPageInfo(String caseName, int runtime) throws Exception {

        XpathOb xpathOb = new XpathOb();
        initData(caseName, xpathOb);

        List<String> list = new ArrayList<>();
        WebDriver driver = ChromeDriver.getChromeDriver();
        driver.manage().window().maximize();
        String url = "https://www.iyingdi.com/";
        driver.get(url);

        //控制登录
        loginIyingdi(driver);

        if (caseName.equals("炉石传说")) {
            //控制跳转到炉石标签
            driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[1]/div/div/div[3]/div[1]/div/ul/li[1]")).click();
            Thread.sleep(4000);
        }

        String id;
        String handle = driver.getWindowHandle();
        int pageIndex = 1;

        for (int countNum = 1; countNum <= runtime; countNum++) {

            WebElement element = SeleniumManager.getElementWait(driver, By.xpath(xpathOb.getInfoCountXpath()));
            int allCount = element.findElements(By.xpath("//div[@class=\"block\"]")).size();
            System.out.println(allCount);

            String jumpXpath = xpathOb.getJumpXpath();
            jumpXpath = jumpXpath.replace(NUM, String.valueOf(allCount));
            SeleniumManager.getElementWait(driver, By.xpath(jumpXpath)).click();

            //句柄跳转
            for (String closeHandle : driver.getWindowHandles()) {
                if (!closeHandle.equals(handle)) {
                    driver.switchTo().window(closeHandle);
                    driver.close();
                }
            }
            driver.switchTo().window(handle);

            //获取文章地址
            for (int i = pageIndex; i <= allCount; i++) {

                String xpath1 = xpathOb.getHrXpath1();
                String xpath2 = xpathOb.getHrXpath2();

                try {
                    xpath1 = xpath1.replace(NUM, String.valueOf(i));
                    id = SeleniumManager.getElementWait(driver, By.xpath(xpath1)).getAttribute(ATTIBUTE);
                } catch (Exception e) {
                    xpath2 = xpath2.replace(NUM, String.valueOf(i));
                    id = SeleniumManager.getElementWait(driver, By.xpath(xpath2)).getAttribute(ATTIBUTE);
                }

                if (list.contains(id)) {
                    System.out.println(list.indexOf(id) + 1 + "和" + i + "篇文章重复");
                    System.out.println("重复的文章地址->" + id);
                    System.out.println("xpath地址" + jumpXpath);
                    return list.indexOf(id) + 1 + "和" + i + "篇文章重复" + "\n" + "重复的文章地址->" + id;
                } else {
                    System.out.println(id);
                    list.add(id);
                }
                pageIndex++;
            }
        }
        return caseName + "页面下拉" + runtime + "次，未发现重复文章&帖子";
    }

    public static void loginIyingdi(WebDriver driver) throws Exception {

        SeleniumManager.getElementWait(driver, By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div/div[2]/div/a")).click();
        Thread.sleep(2000);
        SeleniumManager.getElementWait(driver, By.xpath("(//input[@placeholder='请选择'])[2]")).click();
        Thread.sleep(1000);
        SeleniumManager.getElementWait(driver, By.xpath("//span[contains(text(),'账号密码登录')]")).click();
        SeleniumManager.getElementWait(driver, By.name("username")).sendKeys("86_18612977127");
        SeleniumManager.getElementWait(driver, By.name("password")).sendKeys("aa123123");
        SeleniumManager.getElementWait(driver, By.xpath("//span[@class='el-checkbox__inner']")).click();
        SeleniumManager.getElementWait(driver, By.xpath("//button[@type='submit']")).click();
        Thread.sleep(3000);
    }

    public static void initData(String caseName, XpathOb xpathOb) {

        List<String> list = new ArrayList<>();
        switch (caseName) {

            case "首页":
                list.add("//*[@id=\"__layout\"]/div/div[3]/div/section/div[2]/div[1]/div");
                list.add("//*[@id=\"__layout\"]/div/div[3]/div/section/div[2]/div[1]/div/div[" + NUM + "]");
                list.add("//*[@id=\"__layout\"]/div/div[3]/div/section/div[2]/div[1]/div/div[" + NUM + "]/a");
                list.add("//*[@id=\"__layout\"]/div/div[3]/div/section/div[2]/div[1]/div/div[" + NUM + "]/div/a");
                break;

            case "炉石传说":
                list.add("//*[@id=\"__layout\"]/div/div[3]/div/div/div[2]/div[1]/div[2]/div");
                list.add("//*[@id=\"__layout\"]/div/div[3]/div/div/div[2]/div[1]/div[2]/div/div[" + NUM + "]");
                list.add("//*[@id=\"__layout\"]/div/div[3]/div/div/div[2]/div[1]/div[2]/div/div[" + NUM + "]/a");
                list.add("//*[@id=\"__layout\"]/div/div[3]/div/section/div[2]/div[1]/div/div[" + NUM + "]/div/a");
                break;
        }

        xpathOb.setInfoCountXpath(list.get(0));
        xpathOb.setJumpXpath(list.get(1));
        xpathOb.setHrXpath1(list.get(2));
        xpathOb.setHrXpath2(list.get(3));
    }

}

@Data
class XpathOb {
    private String infoCountXpath;
    private String jumpXpath;
    private String hrXpath1;
    private String hrXpath2;
}