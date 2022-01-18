package com.gaea.server.Test;

import com.gaea.utls.publicTool.FileDone;
import com.gaea.utls.web.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class pashuju {

    public static void main(String[] args) throws Exception {

        WebDriver driver = ChromeDriver.getChromeDriver();
        driver.manage().window().maximize();
        String url = "https://scryfall.com/sets";
        driver.get(url);
        Thread.sleep(3000);

        for (int i = 1; i <= 999; i++) {
            String xpath = "//*[@id=\"js-checklist\"]/tbody/tr[" + i + "]/td[1]/a";
            String value = driver.findElement(By.xpath(xpath)).getAttribute("innerHTML");
            String[] valueArr = value.split("\r|\n|\r\n");
            List<String> valueList = Arrays.asList(valueArr);

            String writeXpath = "C:\\Users\\Administrator\\Desktop\\svg1";

            String name = valueList.get(valueList.size() - 1)
                    .replace(" ", "")
                    .replace("</small>", "")
                    .replace("<small>", "")
                    .replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "")
                    + ".png";

            System.out.println(name);

            FileDone.write(writeXpath + "\\" + name
                    , valueList.get(1));

        }


    }


}
