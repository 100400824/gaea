package com.gaea.server.Test.fly;

public class Test {

    public static void main(String[] args) {

        int index = 0;
        for (int i = 1; i <= 1000; i++) {
            if (i % 3 == 0) {
                index++;
            }
        }
        System.out.println(index);
        /*
        * 1 + 3 + 3 + 3 = 10
        * 5*10 + 2
        *1,2,3...20
        *A-B=13
        *10 11 12 13 14
        *
        *
        * */
    }

}
