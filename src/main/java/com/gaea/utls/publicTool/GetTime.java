package com.gaea.utls.publicTool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {

    public static final String dateFormat1 = "yyyy-MM-dd HH:mm:ss";
    public static final String dateFormat2 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String dateFormat3 = "yyyy-MM-dd-HH-mm-ss";
    public static final String dateFormat4 = "yyyy-MM-dd-HH-mm";
    public static final String dateFormat5 = "MM月dd日HH时mm分";
    public static final String dateFormat6 = "yyyy-MM-dd";

    public static void main(String[] args) {
//        getNowTime(GetTime.dateFormat2);
//        getTimeDifference("2020-09-19 22:48:55.260", "2020-09-19 22:48:56.259");
//        System.out.println(getNowTime(dateFormat5));
    }

    public static Date strToDate(String dateValue,String dateFormat) throws Exception{

        SimpleDateFormat ft = new SimpleDateFormat(dateFormat);

        return ft.parse(dateValue);
    }

    public static String getNowTime(String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(new Date());
    }

    public static String getTimeDifference(String startTime, String endTime) {

        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        long between = 0;
        try {
            Date begin = dfs.parse(startTime);
            Date end = dfs.parse(endTime);
            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                - min * 60 * 1000 - s * 1000);
        if (s == 0 && min == 0) {
            return ms + "毫秒";
        } else if (min == 0) {
            return s + "秒" + ms + "毫秒";
        } else {
            return day + "天" + hour + "小时" + min + "分" + s + "秒" + ms + "毫秒";
        }

    }


}
