package com.gaea.server.dachiyidun;

import com.gaea.server.dachiyidun.ob.Card;
import com.gaea.server.dachiyidun.ob.CardBattle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RuleBattle {

    public static void main(String[] args) {
        CardBattle[] cardBattleArray = new CardBattle[4];

        CardBattle cardBattle = new CardBattle();

        cardBattle.setOrderId(4);
        cardBattle.setSeatId(1);
        cardBattle.setColour("p");
        cardBattle.setNumber(5.5);
        cardBattle.setMasterColour("g");
        cardBattle.setDomainColour("g");
        cardBattleArray[0] = cardBattle;

        cardBattle = new CardBattle();
        cardBattle.setOrderId(1);
        cardBattle.setSeatId(2);
        cardBattle.setColour("g");
        cardBattle.setNumber(6);
        cardBattle.setMasterColour("g");
        cardBattle.setDomainColour("g");
        cardBattleArray[1] = cardBattle;

        cardBattle = new CardBattle();
        cardBattle.setOrderId(2);
        cardBattle.setSeatId(3);
        cardBattle.setColour("b");
        cardBattle.setNumber(6);
        cardBattle.setMasterColour("g");
        cardBattle.setDomainColour("g");
        cardBattleArray[2] = cardBattle;

        cardBattle = new CardBattle();
        cardBattle.setOrderId(3);
        cardBattle.setSeatId(4);
        cardBattle.setColour("p");
        cardBattle.setNumber(0);
        cardBattle.setMasterColour("g");
        cardBattle.setDomainColour("g");
        cardBattleArray[3] = cardBattle;

        System.out.println(getWinnerMap(cardBattleArray));

    }

    //计算本轮 吃墩 和 庄家
    public static Map<String, Integer> getWinnerMap(CardBattle[] cardBattleArray) {

        Map<String, Integer> winnerMap = new HashMap<>();

        //判断是否存在变蛙
        Card card = new Card();
        card.setColour("p");
        card.setNumber(-1);
        int frogFlag = isContains(cardBattleArray, card);

        //对本轮牌组按照牌的大小进行排序  主色+20  庄色+10
        double[] cardNumArray = new double[4];
        int index = 0;
        for (CardBattle cardBattle : cardBattleArray) {
            if (frogFlag == -1) {
                if (cardBattle.getDomainColour().equals(cardBattle.getColour())) {
                    cardNumArray[index] = cardBattle.getNumber() + 20;
                } else if (cardBattle.getMasterColour().equals(cardBattle.getColour()) && !cardBattle.getMasterColour().equals("w")) {
                    cardNumArray[index] = cardBattle.getNumber() + 10;
                } else if (!cardBattle.getMasterColour().equals(cardBattle.getDomainColour()) && (cardBattle.getColour().equals("p") && cardBattle.getNumber() == 5.5)) {//无色5.5 根据庄色和主色 是否存在差异 进行加分计算
                        cardNumArray[index] = cardBattle.getNumber() + 10;
                } else if (cardBattle.getMasterColour().equals(cardBattle.getDomainColour()) && (cardBattle.getColour().equals("p") && cardBattle.getNumber() == 5.5)) {
                        cardNumArray[index] = cardBattle.getNumber() + 20;
                } else {
                    cardNumArray[index] = cardBattle.getNumber();
                }
                index++;
            } else {
                cardNumArray[index] = cardBattle.getNumber();
                index++;
            }

        }

        Arrays.sort(cardNumArray);
        double maxNum = cardNumArray[cardNumArray.length - 1];
        double minNum = cardNumArray[0];
      /*  System.out.println("maxNum:" + maxNum);
        System.out.println("minNum:" + minNum);*/

        //存在变蛙的情况下，考虑是否都是功能牌，和非全部是功能牌
        if (frogFlag != -1) {
            for (int i = 0; i <= 3; i++) {
                if (cardNumArray[i] != 0 && cardNumArray[i] != -1 && cardNumArray[i] != 31 && cardNumArray[i] != 32) {
                    minNum = cardNumArray[i];
                    break;
                } else {
                    minNum = -1;
                }
            }
        }

        //找到本轮吃墩玩家的seatID，相同分数先出的算吃墩
        for (int orderID = 1; orderID <= 4; orderID++) {
            for (CardBattle cardBattle : cardBattleArray) {
                if (cardBattle.getOrderId() == orderID) {
                    if (frogFlag == -1) {
                        double lastNum;

                        if (cardBattle.getDomainColour().equals(cardBattle.getColour())) {
                            lastNum = cardBattle.getNumber() + 20;
                        } else if ( cardBattle.getMasterColour().equals(cardBattle.getColour()) && !cardBattle.getMasterColour().equals("w") ) {
                            lastNum = cardBattle.getNumber() + 10;
                        } else {
                            lastNum = cardBattle.getNumber();
                        }

                        //无色5.5 根据庄色和主色 是否存在差异 进行加分计算
                        if (!cardBattle.getMasterColour().equals(cardBattle.getDomainColour())) {
                            if (cardBattle.getColour().equals("p") && cardBattle.getNumber() == 5.5) {
                                lastNum = cardBattle.getNumber() + 10;
                            }
                        } else if (cardBattle.getMasterColour().equals(cardBattle.getDomainColour())) {
                            if (cardBattle.getColour().equals("p") && cardBattle.getNumber() == 5.5) {
                                lastNum = cardBattle.getNumber() + 20;
                            }
                        }

                        if (lastNum == maxNum) {
                            winnerMap.put("winner", cardBattle.getSeatId());
                            winnerMap.put("master", cardBattle.getSeatId());
                            orderID = 5;
                            break;
                        }
                    } else if (cardBattle.getNumber() == minNum) {
                        winnerMap.put("winner", cardBattle.getSeatId());
                        winnerMap.put("master", cardBattle.getSeatId());
                        orderID = 5;
                        break;
                    }
                }
            }
        }

        //如果存在功能牌：巫毒（p 5.5）则庄家给其所有的的SeatId
        card = new Card();
        card.setColour("p");
        card.setNumber(5.5);
        if (frogFlag == -1) {
            if (isContains(cardBattleArray, card) != -1) {
                winnerMap.put("master", isContains(cardBattleArray, card));
            }
        }

        return winnerMap;
    }

    //判断牌组中是否存在制定的牌，如果有，返回seatID,如果无返回-1
    public static Integer isContains(CardBattle[] cardBattles, Card card) {
        for (CardBattle cardBattle : cardBattles) {
            if (cardBattle.getColour().equals(card.getColour()) &&
                    cardBattle.getNumber() == card.getNumber()) {
                return cardBattle.getSeatId();
            }
        }
        // -1代表本轮卡牌中不存在此张卡牌
        return -1;
    }


}
