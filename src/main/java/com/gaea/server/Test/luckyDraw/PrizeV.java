package com.gaea.server.Test.luckyDraw;

import lombok.Data;

@Data
public class PrizeV {

    //奖品等级名称 S/A/B
    private String prizeName;

    //奖品等级对应级别 1/2/3
    private int prizeLevel;

    //奖品数量 1/2/3/4/5
    private  int prizeNum;

    //*是否中First true/false
    private  Boolean isFirst;

    //*中First概率是多少  double 0.50
    private  double firstProbability;

    //*是否中Half   true/false
    private  Boolean isHalf;

    //*中Half概率是多少   double 0.50
    private double halfProbability;

    //*是否中Last   true/false
    private Boolean isLast;

    //*中Last概率是多少   double 0.50
    private double lastProbability;
}
