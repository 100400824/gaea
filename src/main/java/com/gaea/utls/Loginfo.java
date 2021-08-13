package com.gaea.utls;

public class Loginfo {

    public static int errorNum = 0;

    public static void main(String[] args) {

    }

    public static void printErrorNum(int caseErrorNum) {
        System.out.println();
        System.out.println("失败用例数量：" + caseErrorNum);
    }

    public static void printScene(String index, String msg) {
        System.out.println();
        System.out.println("场景" + index + "、" + msg);

    }

    public static void printLog(String index, String msg) {
        System.out.println(index + "." + msg);
    }

    public static void checkInfo(String str1, String str2, String info) {

        if (!str1.equals(str2)) {
            errorNum++;
            System.out.println(info + "失败");
            System.out.println("期望结果：" + str1 + "，实际结果：" + str2);
        }
    }

}
