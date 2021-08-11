package com.gaea.server.dachiyidun.ob;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class CardBattle extends Card {

    //出牌顺序id
    private int orderId;
    //玩家ID
    private int seatId;
    //主色
    private String domainColour;
    //庄色
    private String masterColour;

}
