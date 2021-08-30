package com.gaea.server.Test;

import java.util.*;

public class TakeCard {

    public static void main(String[] args) {
        getRandomCard();
    }

    private static void getRandomCard() {

        //初始化牌组
        Map<Integer, Card> cadMap = initCard();
        List<Card> newCardList = new ArrayList<>();

        //生成不重复的随机数，给洗牌用
        List<Integer> cardList = new ArrayList<>();
        while (cardList.size() != 52) {
            Random random = new Random();
            int i = random.nextInt(52);
            if (!cardList.contains(i)) {
                cardList.add(i);
            }
        }

        //根据随机数字进行洗牌
        System.out.println("洗牌后的的结果:");
        for (int i : cardList) {
            newCardList.add(cadMap.get(i));
            System.out.print(cadMap.get(i).getPicture() + cadMap.get(i).getNum() + "|");
        }
        System.out.println();

        //发4家牌后的结果
        Map<Integer, List<Card>> playerCardMap = new HashMap<>();
        int index = 1;
        for (int j = 1; j < 5; j++) {
            List<Card> cards = new ArrayList<>();
            for (int i = 1; i < 49; i++) {
                if (index - 4 == 0) {
                    index = 0;
                }
                if (i % 4 == index) {
                    cards.add(newCardList.get(i - 1));
                }
            }
            playerCardMap.put(j, cards);
            index++;
        }

        //打印四家牌结果
        System.out.println("四家发牌结果（每家12张牌）：");
        for (int i = 1; i < 5; i++) {
            System.out.print("玩家" + i + "->");
            for (int j = 0; j < 12; j++) {
                List<Card> cards =  playerCardMap.get(i);
                String picture = cards.get(j).getPicture();
                String num = cards.get(j).getNum();
                System.out.print(picture + num + "|");
            }
            System.out.println();
        }

    }

    //初始化扑克牌
    private static Map<Integer, Card> initCard() {
        Map<Integer, Card> cardMap = new HashMap<>();
        String[] pictureArr = {"草花", "方片", "黑桃", "红桃"};
        String[] numArr = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int i = 0;
        for (String picture : pictureArr) {
            for (String num : numArr) {
                Card card = new Card();
                card.setPicture(picture);
                card.setNum(num);
                cardMap.put(i, card);
                i++;
            }
        }
        return cardMap;
    }
}
