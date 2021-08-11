package com.gaea.server.dachiyidun;

import com.gaea.server.dachiyidun.ob.BattleMark;

public class InitOB {
    public static void main(String[] args) {

    }

    public static BattleMark[] initMarkBattles(){

        BattleMark[] battleMarks = new BattleMark[4];
        BattleMark battleMark = new BattleMark();

        battleMark.setSeatID(1);
        battleMark.setForecastNum(0);
        battleMark.setEatNum(0);
        battleMark.setDrugNum(0);
        battleMark.setScore(0);
        battleMark.setMaster(false);
        battleMarks[0] = battleMark;

        battleMark = new BattleMark();
        battleMark.setSeatID(2);
        battleMark.setForecastNum(0);
        battleMark.setEatNum(0);
        battleMark.setDrugNum(0);
        battleMark.setScore(0);
        battleMark.setMaster(false);
        battleMarks[1] = battleMark;

        battleMark = new BattleMark();
        battleMark.setSeatID(3);
        battleMark.setForecastNum(0);
        battleMark.setEatNum(0);
        battleMark.setDrugNum(0);
        battleMark.setScore(0);
        battleMark.setMaster(false);
        battleMarks[2] = battleMark;

        battleMark = new BattleMark();
        battleMark.setSeatID(4);
        battleMark.setForecastNum(0);
        battleMark.setEatNum(0);
        battleMark.setDrugNum(0);
        battleMark.setScore(0);
        battleMark.setMaster(false);
        battleMarks[3] = battleMark;

        return battleMarks;
    }
}
