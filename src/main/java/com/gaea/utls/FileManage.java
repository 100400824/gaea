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

    //case存放地址
    public static String lfsCasePath = homePath + xx + "LFS.xlsx";

    //报告存放地址
    public static String reportPath = homePath + xx + "LFSreportAdroid.txt";

    //报告压缩后文件存放地址
    public static String zipFilePath = homePath;

    //图片存放地址
    public static String imgFilePath = homePath + xx + "img";


}