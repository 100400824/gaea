package com.gaea.server.dachiyidun;

import com.gaea.server.dachiyidun.ob.BattleMark;
import com.gaea.server.dachiyidun.ob.CardBattle;
import com.gaea.server.dachiyidun.ob.CardInfo;
import com.gaea.utls.FileManage;
import com.gaea.utls.publicTool.GetTime;
import com.gaea.utls.publicTool.OperationFile;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class WebDachiyidun {

    private static WebDriver driver;
    public static String xx = File.separator;
    public static int errorCount;
    public static String filePath = System.getProperty("user.dir") + xx;
    public static String fileName = GetTime.getNowTime(GetTime.dateFormat5) + ".txt";


    public static void main(String[] args) throws Exception {
        String userID = "14000082";//14000034  20188337
        String url = "https://activity.iyingdi.com/gameTest/demo.html";
        doDCYD(url,userID, "2");
    }

    public static void doDCYD(String url,String userID, String runCount) throws Exception {

        OperationFile.write(filePath + fileName, fileName + "大吃一墩对战，自动化测试日志");

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

            driver = new ChromeDriver(options);

        } else if (osName.contains("Windows")) {

            System.out.println("windows下启动selenium");

            // chrome 驱动的位置
            System.setProperty("webdriver.chrome.driver", FileManage.chromeDriver);

            driver = new ChromeDriver();

        } else if (osName.contains("M")) {

            System.out.println("Mac下启动selenium");

            // chrome 驱动的位置
            System.setProperty("webdriver.chrome.driver", FileManage.chromeDriverMac);

            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();


/*        System.setProperty("webdriver.chrome.driver", FileManage.chromeDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();*/
        driver.get(url);
        Thread.sleep(2000);

        //完整登录流程
        xpathText("登录").click();
        className("user_id").sendKeys(userID);//14000034  20188337
        className("btn_login_confirm").click();
        Thread.sleep(1000);

        //设置比赛次数
        int runNum = 1;
        try {
            runNum = Integer.parseInt(runCount);
        } catch (Exception e) {
        }

        for (int battleCount = 1; battleCount <= runNum; battleCount++) {
            //匹配
            className("btn_pipei").click();

            //等待创建房间
            String roomName = id("mian-room").getText();
            while (roomName.equals("")) {
                Thread.sleep(2000);
                roomName = id("mian-room").getText();
            }

            System.out.println();
            String newline = "\r\n";
            OperationFile.write(filePath + fileName, newline);
            String roomInfo = "房间号" + roomName;
            System.out.println(roomInfo);
            OperationFile.write(filePath + fileName, roomInfo);

            System.out.println("当前第" + battleCount + "局比赛：");
            OperationFile.write(filePath + fileName, "当前第" + battleCount + "局比赛：");

            //预测
            int i = 1;
            while (i == 1) {
                Thread.sleep(2000);
                try {
                    className("dun_num").sendKeys("4");
                    className("btn_confirm").click();
                    i++;
                } catch (Exception ignored) {
                }
            }

            //初始化记分牌信息
            BattleMark[] markBattlesInit = InitOB.initMarkBattles();

            //每局获取对战卡牌信息
            for (int orderId = 1; orderId <= 13; orderId++) {

                String battleCountInfo = "当前第" + orderId + "回合";
                System.out.println();
                System.out.println(battleCountInfo);
                newline = "\r\n";
                OperationFile.write(filePath + fileName, newline);
                OperationFile.write(filePath + fileName, battleCountInfo);

                //获取庄家seatID
                Thread.sleep(3333);
                int ff = 1;
                String masterID = "";
                String domainColour = "";
                while (1 == ff) {
                    try {
                        masterID = xpath("(//*[@style='display: inline;']/../..)[2]").getAttribute("seat-id");
                        domainColour = id("domin-color").getAttribute("class").toLowerCase();
                        ff++;
                    } catch (Exception ignored) {
                    }
                }

                String masterIDInfo = "庄家seatid:" + masterID;
                System.out.println(masterIDInfo);
                OperationFile.write(filePath + fileName, masterIDInfo);

                //获取本轮卡组信息
                CardBattle[] cardBattles;
                cardBattles = getCardInfo(masterID);
                for (CardBattle cardBattle : cardBattles) {
                    cardBattle.setDomainColour(domainColour);

                    System.out.println(cardBattle);
                    OperationFile.write(filePath + fileName, cardBattle.toString());
                }

                //计算得分
                BattleMark[] myMarks = MarkBattle.getBattleMarks(cardBattles, RuleBattle.getWinnerMap(cardBattles), markBattlesInit);
                int k = 1;
                for (BattleMark battleMark : myMarks) {
                    //开具时获取各seatID的预测数量
                    if (orderId == 1) {
                        String pcYuce = xpath("(//*[@seat-id='" + k + "']/div/ul/li/span)[1]").getText();
                        battleMark.setForecastNum(Integer.parseInt(pcYuce));
                        myMarks[k - 1].setForecastNum(Integer.parseInt(pcYuce));
                        k++;
                    }
                    String battleMarkInfo = "测试计算比分：" + battleMark;
                    System.out.println(battleMarkInfo);
                    OperationFile.write(filePath + fileName, battleMarkInfo);
                }

                Thread.sleep(1000);

                if (orderId == 13) {
                    Thread.sleep(1000);
                    JavascriptExecutor js_gold = (JavascriptExecutor) driver;
                    String js = "var elem = document.getElementsByClassName('pop_match_result')[0];"
                            + "elem.parentNode.removeChild(elem);  ";
                    js_gold.executeScript(js);
                }

                Thread.sleep(1000);

                //获取PC当前计算分数
                BattleMark[] pcMarks = getBattleMark();
                //比对测试计算的比分与PC计算的比分是否一致
                checkMark(myMarks, pcMarks);
            }

            Thread.sleep(7000);

            String errorCountInfo = "当前总错误次数：" + errorCount;
            System.out.println(errorCountInfo);
            OperationFile.write(filePath + fileName, errorCountInfo);

            driver.navigate().refresh();
            Thread.sleep(5000);
        }
    }

    //比对测试计算的比分与PC计算的比分是否一致
    private static void checkMark(BattleMark[] myMarks, BattleMark[] pcMarks) throws Exception {

        for (int i = 0; i < 4; i++) {

            if (!myMarks[i].equals(pcMarks[i])) {
                String info1 = "本局比分存在异议：";
                String info2 = "测试计算比分为：" + myMarks[i];
                String info3 = "系统计算比分为：" + pcMarks[i];
                OperationFile.write(filePath + fileName, info1);
                OperationFile.write(filePath + fileName, info2);
                OperationFile.write(filePath + fileName, info3);
                System.out.println(info1);
                System.out.println(info2);
                System.out.println(info3);
                errorCount++;
            } else {
                String info4 = "测试与系统计算结果一致";
                System.out.println(info4);
                OperationFile.write(filePath + fileName, info4);
            }
        }
    }


    //记录每轮战局的记分牌信息
    private static BattleMark[] getBattleMark() throws IOException {

        BattleMark[] pcMarks = InitOB.initMarkBattles();
        String masterID = "";
        for (int i = 0; i < 4; i++) {
            String pcYuce = xpath("(//*[@seat-id='" + (i + 1) + "']/div/ul/li/span)[1]").getText();
            String pcCd = xpath("(//*[@seat-id='" + (i + 1) + "']/div/ul/li/span)[2]").getText();
            String pcWd = xpath("(//*[@seat-id='" + (i + 1) + "']/div/ul/li/span)[3]").getText();
            String pcFs = xpath("(//*[@seat-id='" + (i + 1) + "']/div/ul/li/span)[4]").getText();

            while (masterID.equals("")) {
                try {
                    masterID = xpath("(//*[@style='display: inline;']/../..)[2]").getAttribute("seat-id");
                } catch (Exception e) {
                    masterID = xpath("(//*[@style='display: inline;']/../..)[1]").getAttribute("seat-id");
                }
            }

            pcMarks[i].setForecastNum(Integer.parseInt(pcYuce));
            pcMarks[i].setEatNum(Integer.parseInt(pcCd));
            pcMarks[i].setDrugNum(Integer.parseInt(pcWd));
            pcMarks[i].setScore(Integer.parseInt(pcFs));
            try {
                if (pcMarks[i].getSeatID() == Integer.parseInt(masterID)) {
                    pcMarks[i].setMaster(true);
                }
            } catch (Exception ignored) {
            }
            String pcMarksInfo = "pc记分牌->" + pcMarks[i];
            System.out.println(pcMarksInfo);
            OperationFile.write(filePath + fileName, pcMarksInfo);
        }

        return pcMarks;
    }

    //每局出牌 并 记录每张牌
    private static CardBattle[] getCardInfo(String masterID) {

        CardBattle[] cardBattles = new CardBattle[4];
        String masterColour = "";
        int orderId = 1;
        String imgURL;
        while (orderId <= 4) {
            //根据庄家seatID找到出的牌
            String classNameStr = getClassName(masterID);
            try {
                if (classNameStr.equals("box-my")) {
                    //自己出牌
                    String xpathStr = "(//*[@class='card active'])[1]/img";
                    imgURL = xpath(xpathStr).getAttribute("src");
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpathStr)));
                    if (imgURL.contains("lve_tao.jpg")) {
                        Thread.sleep(500);
                        js.executeScript("arguments[0].click();", driver.findElement(By.className("btn_escaper")));
                        js.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpathStr)));
                    }
                } else {
                    String xpathStr = "(//*[@class='" + classNameStr + "']/img)[1]";
                    imgURL = xpath(xpathStr).getAttribute("src");
                }

                //获取每轮的庄色
                if (masterColour.equals("") || masterColour.equals("p")) {
                    masterColour = "";
                    while (masterColour.equals("")) {
//                        masterColour = id("user-color").getAttribute("class").toLowerCase();
                        masterColour = CardInfo.getCardInfo(imgURL).getColour();
                        Thread.sleep(100);
                    }
                }


                //设置对战卡牌组信息
                CardBattle cardBattle = new CardBattle();
                cardBattle.setOrderId(orderId);
                cardBattle.setSeatId(Integer.parseInt(masterID));
                cardBattle.setColour(CardInfo.getCardInfo(imgURL).getColour());
                cardBattle.setNumber(CardInfo.getCardInfo(imgURL).getNumber());
//                cardBattle.setMasterColour(masterColour);
                cardBattles[orderId - 1] = cardBattle;

                System.out.println(imgURL);
                OperationFile.write(filePath + fileName, imgURL);

                //对战最后一人出牌后的拿庄色
                if (orderId == 4) {
                    for (int i = 0; i < 4; i++) {
                        cardBattles[i].setMasterColour(masterColour);
                    }
                }

                int seatId = Integer.parseInt(masterID) + 1;

                if (seatId > 4) {
                    seatId = seatId - 4;
                }
                masterID = "" + seatId;
//                System.out.println(orderId + "---" + imgURL);
                orderId++;
            } catch (Exception ignored) {
            }
        }
        return cardBattles;
    }

    //根据seatID 计算 className
    private static String getClassName(String seatId) {
        String[] seatids = new String[4];
        String[] boxNames = new String[4];
        Map<String, String> seatIdClassNameMap = new HashMap<>();
        for (int i = 1; i <= 4; i++) {
            String xpathStr = "(//*[@class='box']/div)[" + i + "]";
            seatids[i - 1] = xpath(xpathStr).getAttribute("seat-id");
            boxNames[i - 1] = xpath(xpathStr).getAttribute("seat-position");
            seatIdClassNameMap.put(seatids[i - 1], boxNames[i - 1]);
        }
        return seatIdClassNameMap.get(seatId);
    }


    //通过XPATH TEXT方式定位元素
    private static WebElement xpathText(String text) {
        return driver.findElement(By.xpath("//*[text()=\"" + text + "\"]"));
    }

    //通过XPATH TEXT方式定位元素
    private static WebElement xpath(String str) {
        return driver.findElement(By.xpath(str));
    }

    private static WebElement className(String className) {
        return driver.findElement(By.className("" + className + ""));
    }

    private static WebElement id(String id) {
        return driver.findElement(By.id(id));
    }


}
