package com.gaea.server.marvel;


import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class MakeJson {

    public static void main(String[] args) {
        String jsonStr = "{\"1\":\"131072,431017,431021,131208,131178,231042,431018,431022,131071,431020\",\"-1\":\"431042,131140,231101,431097,431097,431097,131151,131151,131151,131151,131156,131159,131159,131159,131159,131128,131128,131130,131165\",\"4\":\"131057,131057,131057,131057,131059,131059,131060,131062,131062,131062,131062,131215,131051,431016,431016,431016,431016,131065,131065,131065,131065,431056,131056,131056,131056,131056\",\"5\":\"131011,131011,131011,131011,131204,231005,131016,131002,131004,331031,131022,131022,131022,131022,131023,131008\"}";
        getCards(jsonStr);

    }

    public static Map<String ,MarvelCard[]> getCards(String jsonStr) {

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Map<String ,MarvelCard[]> map = new HashMap<>();

        for (String heroID : jsonObject.keySet()) {

            String[] cardArr = jsonObject.getString(heroID).split(",");
            Set<String> cardSet = new HashSet<>(Arrays.asList(cardArr));
            MarvelCard[] marvelCards = new MarvelCard[10];

            int i=0;
            for (String cardSetName : cardSet) {
                int num = 0;
                for (String cardArrName : cardArr) {
                    if (cardSetName.equals(cardArrName)) {
                        num++;
                    }
                }
                MarvelCard marvelCard = new MarvelCard();
                marvelCard.setHeroId(heroID);
                marvelCard.setCardId(cardSetName);
                marvelCard.setCardNum(num);
                marvelCards[i] = marvelCard;
                i++;
            }
            map.put(heroID,marvelCards);
        }
        return map;
    }

}
