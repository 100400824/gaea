package com.gaea.server.Test;

public class Yongbing {

    public static void main(String[] args) throws Exception {

        String str1 = "testa/testb/testc/testd/teste/testf//testg/testh/";
        String strEnd = "";
        for (int i = 1; i <= 100; i++) {
            strEnd = strEnd + str1 + i + ",";
        }
        System.out.print(strEnd);

    }

    public static int fei(int num) {

        if (num == 1 || num == 2) {
            return 1;
        }

        return fei(num - 1) + fei(num - 2);
    }


}
