package com.gaea.utls;

import java.io.FileWriter;

public class Loginfo {

    public static int errorNum = 0;

    public static void main(String[] args) {

    }

    public static void pringSceneError(String sceneName, String caseID, String caseInfo, String errorInfo, FileWriter pfp) throws Exception {

        String str = sceneName + "-场景运行失败：第" + caseID + "条用例-" + caseInfo;

        System.out.println(str);
        System.out.println(errorInfo);

        pfp.write(str + "\r\n");
//        pfp.write(errorInfo+ "。\r\n");

    }


    public static void printErrorNum(int caseErrorNum, FileWriter pfp) throws Exception {

        String str = "失败用例数量：" + caseErrorNum;

        System.out.println();
        System.out.println(str);

        pfp.write("\r\n");
        pfp.write(str + "。\r\n");
    }

    public static void printScene(String index, String msg, FileWriter pfp) throws Exception {

        String str = "场景" + index + "、" + msg;

        System.out.println();
        System.out.println("场景" + index + "、" + msg);

        pfp.write("\r\n");
        pfp.write(str + "。\r\n");

    }

    public static void printLog(String index, String msg, FileWriter pfp) throws Exception {
        String str = index + "." + msg;
        System.out.println(index + "." + msg);
        pfp.write(str + "。\r\n");
    }

    //校验期望结果
    public static void checkInfo(String checkType, String str1, String str2, String info, FileWriter pfp) throws Exception {

        switch (checkType) {

            case "equals":
                if (!str1.equals(str2)) {
                    checkValue(str1, str2, info, pfp);
                }
                break;

            case "contains":
                if (!str1.contains(str2)) {
                    checkValue(str1, str2, info, pfp);
                }
                break;

            default:
                checkValue("校验类型错误", "校验类型错误!", info, pfp);
                break;
        }


    }

    private static void checkValue(String str1, String str2, String info, FileWriter pfp) throws Exception {

        String stra = info + "失败";
        String strb = "期望结果：" + str1 + "，实际结果：" + str2;
        errorNum++;

        System.out.println(info + "失败");
        System.out.println("期望结果：" + str1 + "，实际结果：" + str2);

        pfp.write(stra + "。\r\n");
        pfp.write(strb + "。\r\n");


    }

}
