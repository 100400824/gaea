package com.gaea.server.Test;

public class LevelGold {

    public static void main(String[] args) {

        int sum = 0;

        for (int i=1; i<=20; i++){

            sum = sum + getGold(12);
        }

        System.out.println(sum);
    }

    //1-10递增100  11-20递增200  21-30递增300
    public static int getGold(int levelNum) {

        if (levelNum == 1) {
            return 100;

        } else if (levelNum >= 1 && levelNum <= 10) {

            return (getGold(levelNum - 1) + 100);

        }else if (levelNum >= 11 && levelNum <= 20) {

            return (getGold(levelNum - 1) + 200);

        }else {

            return 0;
        }

    }


}
