package com.gaea.server.Test.luckyDraw;

import lombok.Data;

@Data
public class WinV {

    //奖品序号 1/2/3
    private int prizeNum;

    //奖品名称  S赏/A赏
    private String prizeName;

    //是否First赏
    private Boolean isFirst;

    //是否Half赏
    private Boolean isHalf;

    //是否last赏
    private Boolean isLast;
}
