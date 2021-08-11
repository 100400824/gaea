package com.gaea.server.dachiyidun;

import com.gaea.server.dachiyidun.ob.BattleMark;
import com.gaea.server.dachiyidun.ob.CardBattle;

import java.util.HashMap;
import java.util.Map;

public class MarkBattle {

    public static void main(String[] args) {
        BattleMark[] battleMarks = new BattleMark[4];
        BattleMark battleMark = new BattleMark();

        battleMark.setSeatID(1);
        battleMark.setForecastNum(3);
        battleMark.setEatNum(0);
        battleMark.setDrugNum(0);
        battleMark.setScore(0);
        battleMark.setMaster(false);
        battleMarks[0] = battleMark;

        battleMark = new BattleMark();
        battleMark.setSeatID(2);
        battleMark.setForecastNum(3);
        battleMark.setEatNum(0);
        battleMark.setDrugNum(0);
        battleMark.setScore(0);
        battleMark.setMaster(false);
        battleMarks[1] = battleMark;

        battleMark = new BattleMark();
        battleMark.setSeatID(3);
        battleMark.setForecastNum(3);
        battleMark.setEatNum(0);
        battleMark.setDrugNum(0);
        battleMark.setScore(0);
        battleMark.setMaster(false);
        battleMarks[2] = battleMark;

        battleMark = new BattleMark();
        battleMark.setSeatID(4);
        battleMark.setForecastNum(3);
        battleMark.setEatNum(0);
        battleMark.setDrugNum(0);
        battleMark.setScore(0);
        battleMark.setMaster(false);
        battleMarks[3] = battleMark;

        CardBattle[] cardBattles = new CardBattle[4];
        CardBattle cardBattle = new CardBattle();

        cardBattle.setOrderId(1);
        cardBattle.setSeatId(1);
        cardBattle.setColour("Y");
        cardBattle.setNumber(5);
        cardBattles[0] = cardBattle;

        cardBattle = new CardBattle();
        cardBattle.setOrderId(2);
        cardBattle.setSeatId(2);
        cardBattle.setColour("Y");
        cardBattle.setNumber(7);
        cardBattles[1] = cardBattle;

        cardBattle = new CardBattle();
        cardBattle.setOrderId(3);
        cardBattle.setSeatId(3);
        cardBattle.setColour("p");
        cardBattle.setNumber(5.5);
        cardBattles[2] = cardBattle;

        cardBattle = new CardBattle();
        cardBattle.setOrderId(4);
        cardBattle.setSeatId(4);
        cardBattle.setColour("y");
        cardBattle.setNumber(-1);
        cardBattles[3] = cardBattle;

        BattleMark[] aa = getBattleMarks(cardBattles, RuleBattle.getWinnerMap(cardBattles),battleMarks);

        for (BattleMark dd : aa) {
            System.out.println(dd);
        }
    }

    //计算本轮结束后 分数结果 和 庄家人选
    public static  BattleMark[]  getBattleMarks(CardBattle[] cardBattles, Map<String ,Integer> winnerMap, BattleMark[] battleMarks) {
        Map<String ,Integer> markMap = getAllScore(cardBattles);
        int scoreNum , drugNum;
        for (BattleMark battleMark : battleMarks) {

            if (battleMark.getSeatID() == winnerMap.get("winner")){
                scoreNum = battleMark.getScore() + markMap.get("allScore");
                drugNum = battleMark.getDrugNum() + markMap.get("drugNum");
                battleMark.setEatNum(battleMark.getEatNum()+1);
                battleMark.setDrugNum(drugNum);
                battleMark.setScore(scoreNum);
            }

            if (battleMark.getSeatID() == winnerMap.get("master")){
                battleMark.setMaster(true);
            }else {
                battleMark.setMaster(false);
            }

        }
        return battleMarks;
    }

    //计算牌组中的分数总和、巫毒总和
    public static Map<String ,Integer> getAllScore(CardBattle[] cardBattles) {
        Map<String ,Integer> markMap  = new HashMap<>();
        int allScore = 0;
        int drugNum = 0;
        for (CardBattle cardBattle : cardBattles) {
            if (cardBattle.getNumber() == -1) {
                markMap.put("allScore",0);
                markMap.put("drugNum",0);
                return markMap;
            }
        }
        for (CardBattle cardBattle : cardBattles) {
            int newScore = 0;
            if (cardBattle.getNumber() != 5.5) {
                newScore = (int)cardBattle.getNumber();
            }
            if (newScore == 5 ) {
                allScore += newScore;
            }else if (newScore == 10) {
                allScore += newScore;
            }else if (cardBattle.getNumber() == 5.5) {
                drugNum++;
                if (cardBattle.getColour().equals("p")) {
                    drugNum++;
                }
            }
        }
        markMap.put("allScore",allScore);
        markMap.put("drugNum",drugNum);
        return markMap;
    }

}
