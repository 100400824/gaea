package com.gaea.utls;

import java.io.File;


public class FileManage {

    final static String homePath = System.getProperty("user.dir");

    public static String xx = File.separator;

    //chrome驱动
    public static String chromeDriver = homePath + xx + "chromedriver32.exe";

    public static String chromeDriverLinux = homePath + xx + "chromedriverLinux";

    public static String chromeDriverMac = homePath + xx + "chromedriver";

    public static String screenShot = homePath + xx + "img";

    //旅法师case存放地址
    public static String lfsCasePath = homePath + xx + "LFS.xlsx";

    //漫威对局卡组周报
    public static  String marvelPath = homePath + xx + "卡组周报.xlsx";

    //审核平台临时添加数据
    public static String shenheADDcontextCasePath = homePath + xx + "审核平台.xlsx";

    //报告存放地址
    public static String reportPath = homePath + xx + "LFSreportAdroid.txt";

    //报告压缩后文件存放地址
    public static String zipFilePath = homePath;

    //图片存放地址
    public static String imgFilePath = homePath + xx + "img";


}