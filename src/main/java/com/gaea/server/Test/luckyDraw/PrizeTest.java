package com.gaea.server.Test.luckyDraw;

import java.util.*;

public class PrizeTest {

    public static void main(String[] args) {

        PrizeV prizeS = new PrizeV();
        prizeS.setPrizeName("S赏");
        prizeS.setPrizeLevel(1);
        prizeS.setPrizeNum(2);
        prizeS.setIsFirst(true);
        prizeS.setFirstProbability(0.50);
        prizeS.setIsHalf(true);
        prizeS.setHalfProbability(0.50);
        prizeS.setIsLast(true);
        prizeS.setLastProbability(0.50);

        PrizeV prizeA = new PrizeV();
        prizeA.setPrizeName("A赏");
        prizeA.setPrizeLevel(2);
        prizeA.setPrizeNum(6);
        prizeA.setIsFirst(true);
        prizeA.setFirstProbability(0.50);
        prizeA.setIsHalf(true);
        prizeA.setHalfProbability(0.50);
        prizeA.setIsLast(true);
        prizeA.setLastProbability(0.50);

        ArrayList<PrizeV> prizes = new ArrayList<>();
        prizes.add(prizeS);
        prizes.add(prizeA);

        Boolean isRule = false;
        ruleTest(prizes, isRule);

    }

    /*
    传入
    1奖品对象数组
    2中奖规则
    输出
    1奖品分布图形*/
    public static void ruleTest(List<PrizeV> prizes, Boolean isRule) {

        if (isRule) {
            System.out.println("hello world");
        } else {
            noRulePize(prizes);
        }

    }

    //有规则排序
    public static void doRulePrize(List<PrizeV> prizeVS) {


    }

    //无规则排序
    public static void noRulePize(List<PrizeV> prizes) {

        ArrayList<String> prizeNameList = new ArrayList<>();
        for (PrizeV prizeV : prizes) {
            for (int i = 1; i <= prizeV.getPrizeNum(); i++) {
                prizeNameList.add(prizeV.getPrizeName());
            }

        }
        Collections.shuffle(prizeNameList);

        boolean isNext = true;

        while (isNext) {
            isNext = isStop(prizeNameList);
        }

        System.out.println("随机后：" + prizeNameList);
    }

    public static Boolean isStop(ArrayList<String> prizeNameList) {

        Map<String, Integer> map = doSomeRule(prizeNameList);

        if (2 == map.get("S赏") || 0 == map.get("S赏")) {
            Collections.shuffle(prizeNameList);
            System.out.println(123);
            return true;
        }
        return false;
    }

    public static Map<String, Integer> doSomeRule(ArrayList<String> prizeNameList) {

        int listSize = prizeNameList.size() / 2;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < listSize; i++) {
            String keyStr = prizeNameList.get(i);
            if (map.containsKey(keyStr)) {
                int oldNum = map.get(keyStr);
                map.put(keyStr, oldNum + 1);
            } else {
                map.put(keyStr, 1);
            }
        }
        return map;
    }


    //生成车位中First赏、Half赏、Last赏
    public static void setPirzeSpecial(List<PrizeV> prizes, Boolean isFirst) {
        WinV winv = new WinV();


        int prizesLength = prizes.size();


    }


}
