package com.gaea.server.dachiyidun.ob;

public class CardInfo {
    public static void main(String[] args) {

        String srcStr = "https://image.gaeamobile.net/image/20210521/154428/red_1.jpg";
        getCardInfo(srcStr);

    }

    public static Card getCardInfo(String srcStr) {

        int index = srcStr.split("/").length - 1;
        srcStr = srcStr.split("/")[index];
        String colour;
        double num;
        switch (srcStr) {
            case "red_1.jpg":
                colour = "r";
                num = 1;
                return setCard(colour, num);
            case "red_2.jpg":
                colour = "r";
                num = 2;
                return setCard(colour, num);
            case "red_3.jpg":
                colour = "r";
                num = 3;
                return setCard(colour, num);
            case "red_4.jpg":
                colour = "r";
                num = 4;
                return setCard(colour, num);
            case "red_5.jpg":
                colour = "r";
                num = 5;
                return setCard(colour, num);
            case "red_5_5.jpg":
                colour = "r";
                num = 5.5;
                return setCard(colour, num);
            case "red_6.jpg":
                colour = "r";
                num = 6;
                return setCard(colour, num);
            case "red_7.jpg":
                colour = "r";
                num = 7;
                return setCard(colour, num);
            case "red_8.jpg":
                colour = "r";
                num = 8;
                return setCard(colour, num);
            case "red_9.jpg":
                colour = "r";
                num = 9;
                return setCard(colour, num);
            case "red_10.jpg":
                colour = "r";
                num = 10;
                return setCard(colour, num);
            case "yellow_1.jpg":
                colour = "y";
                num = 1;
                return setCard(colour, num);
            case "yellow_2.jpg":
                colour = "y";
                num = 2;
                return setCard(colour, num);
            case "yellow_3.jpg":
                colour = "y";
                num = 3;
                return setCard(colour, num);
            case "yellow_4.jpg":
                colour = "y";
                num = 4;
                return setCard(colour, num);
            case "yellow_5.jpg":
                colour = "y";
                num = 5;
                return setCard(colour, num);
            case "yellow_5_5.jpg":
                colour = "y";
                num = 5.5;
                return setCard(colour, num);
            case "yellow_6.jpg":
                colour = "y";
                num = 6;
                return setCard(colour, num);
            case "yellow_7.jpg":
                colour = "y";
                num = 7;
                return setCard(colour, num);
            case "yellow_8.jpg":
                colour = "y";
                num = 8;
                return setCard(colour, num);
            case "yellow_9.jpg":
                colour = "y";
                num = 9;
                return setCard(colour, num);
            case "yellow_10.jpg":
                colour = "y";
                num = 10;
                return setCard(colour, num);
            case "blue_1.jpg":
                colour = "b";
                num = 1;
                return setCard(colour, num);
            case "blue_2.jpg":
                colour = "b";
                num = 2;
                return setCard(colour, num);
            case "blue_3.jpg":
                colour = "b";
                num = 3;
                return setCard(colour, num);
            case "blue_4.jpg":
                colour = "b";
                num = 4;
                return setCard(colour, num);
            case "blue_5.jpg":
                colour = "b";
                num = 5;
                return setCard(colour, num);
            case "blue_5_5.jpg":
                colour = "b";
                num = 5.5;
                return setCard(colour, num);
            case "blue_6.jpg":
                colour = "b";
                num = 6;
                return setCard(colour, num);
            case "blue_7.jpg":
                colour = "b";
                num = 7;
                return setCard(colour, num);
            case "blue_8.jpg":
                colour = "b";
                num = 8;
                return setCard(colour, num);
            case "blue_9.jpg":
                colour = "b";
                num = 9;
                return setCard(colour, num);
            case "blue_10.jpg":
                colour = "b";
                num = 10;
                return setCard(colour, num);
            case "green_1.jpg":
                colour = "g";
                num = 1;
                return setCard(colour, num);
            case "green_2.jpg":
                colour = "g";
                num = 2;
                return setCard(colour, num);
            case "green_3.jpg":
                colour = "g";
                num = 3;
                return setCard(colour, num);
            case "green_4.jpg":
                colour = "g";
                num = 4;
                return setCard(colour, num);
            case "green_5.jpg":
                colour = "g";
                num = 5;
                return setCard(colour, num);
            case "green_5_5.jpg":
                colour = "g";
                num = 5.5;
                return setCard(colour, num);
            case "green_6.jpg":
                colour = "g";
                num = 6;
                return setCard(colour, num);
            case "green_7.jpg":
                colour = "g";
                num = 7;
                return setCard(colour, num);
            case "green_8.jpg":
                colour = "g";
                num = 8;
                return setCard(colour, num);
            case "green_9.jpg":
                colour = "g";
                num = 9;
                return setCard(colour, num);
            case "green_10.jpg":
                colour = "g";
                num = 10;
                return setCard(colour, num);
            case "taopao.jpg":
                colour = "p";
                num = 0;
                return setCard(colour, num);
            case "lveduo.jpg":
                colour = "p";
                num = 31;
                return setCard(colour, num);
            case "no_5_5.jpg":
                colour = "p";
                num = 5.5;
                return setCard(colour, num);
            case "maes_tao.jpg":
                colour = "p";
                num = 0;
                return setCard(colour, num);
            case "maes_lve.jpg":
                colour = "p";
                num = 31;
                return setCard(colour, num);
            case "huanlong.jpg":
                colour = "p";
                num = 32;
                return setCard(colour, num);
            case "bianwa.jpg":
                colour = "p";
                num = -1;
                return setCard(colour, num);
            case "lve_tao.jpg":
                colour = "p";
                num = 0;
                return setCard(colour, num);
            default:
                System.out.println(srcStr + "未找到！！！！！！！！！！！！！！！！！！！");
                colour = "000";
                num = 0;
                return setCard(colour, num);
        }
    }

    public static Card setCard(String colour, double num) {
        Card card = new Card();
        card.setColour(colour);
        card.setNumber(num);
//        System.out.println(card);
        return card;
    }
}
