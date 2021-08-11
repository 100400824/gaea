package com.gaea.server.dachiyidun.ob;

import lombok.Data;

@Data
public class Card {

    //牌色 r=红色 y=黄色 g=绿色 b=蓝色 p=功能牌
    private String colour;

    //牌号  -1=变娃 0=逃跑  1-10 常规卡牌  庄色 11-20  主色 21-30  31=掠夺 32=唤龙
    private double number;
}
