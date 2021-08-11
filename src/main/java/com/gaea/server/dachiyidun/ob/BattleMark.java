package com.gaea.server.dachiyidun.ob;

import lombok.Data;

@Data
public class BattleMark {

    //用户ID
    private int seatID;
    //庄
    private Boolean master;
    //预测
    private int forecastNum;
    //吃墩
    private int eatNum;
    //巫毒
    private int drugNum;
    //分数
    private int score;
}
