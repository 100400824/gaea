package com.gaea.server.prophesy;

import com.gaea.utls.publicTool.GetTime;

public class InitNum {

    public static void initOB() throws Exception{

        Num num = new Num();
        num.setNum(2);
        num.setCreateDate(GetTime.strToDate("2021-01-01",GetTime.dateFormat6));
        num.setWeek(2);
    }

}
