package com.gaea.server.Test;

public class Yongbing {

    public static void main(String[] args) throws Exception {

        //ABC
        String[] arr = {"A", "B", "C", "D"};
        int i=0;
        for (String e1 : arr) {
            for (String e2 : arr) {
                for (String e3 : arr) {
                    if (!e1.equals(e2) && !e1.equals(e3) && !e2.equals(e3)) {
                        System.out.println(e1 + e2 +e3);
                        i++;
                    }
                }
            }
        }
        System.out.println(i);
    }

}
