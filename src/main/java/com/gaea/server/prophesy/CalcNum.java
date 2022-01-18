package com.gaea.server.prophesy;

import com.gaea.utls.publicTool.FileDone;
import com.gaea.utls.web.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CalcNum {

    final static String NUM = "NUM";

    public static void main(String[] args) throws Exception {

        getNum();
    }

    public static void getNum() throws Exception {

        String path = System.getProperty("user.dir");

        String url = "https://www.cjcp.com.cn/kaijiang/ssqmingxi_" + NUM + ".html";

        WebDriver driver = ChromeDriver.getChromeDriver();

        driver.manage().window().maximize();

        driver.get(url);

        for (int num = 1; num <= 139; num++) {

            driver.get(url.replace(NUM, "" + num));

            for (int i = 1; i <= 20; i++) {
                StringBuilder ballNum = new StringBuilder();
                for (int j = 1; j <= 7; j++) {
                    String xpathValue = "(//*[@class='kjjg_hm_bg'])[" + i + "]/img[" + j + "]";
                    String nums = driver.findElement(By.xpath(xpathValue)).getAttribute("src");
                    nums = nums.split("/")[nums.split("/").length-1].substring(15,17);
                    System.out.print(nums+",");
                    ballNum.append(nums).append(",");
                }
                FileDone.write(path + "/ballNum.txt", ballNum.toString());
                System.out.println();
            }

        }


    }
}
