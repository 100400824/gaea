package com.gaea.server.marvel;

import java.util.HashMap;
import java.util.Map;

public class MarvelRequest {


    public static void main(String[] args) {
        String cardJsonStr = "{\"1\":\"131072,431017,431021,131208,131178,231042,431018,431022,131071,431020\",\"-1\":\"431042,131140,231101,431097,431097,431097,131151,131151,131151,131151,131156,131159,131159,131159,131159,131128,131128,131130,131165\",\"4\":\"131057,131057,131057,131057,131059,131059,131060,131062,131062,131062,131062,131215,131051,431016,431016,431016,431016,131065,131065,131065,131065,431056,131056,131056,131056,131056\",\"5\":\"131011,131011,131011,131011,131204,231005,131016,131002,131004,331031,131022,131022,131022,131022,131023,131008\"}";
        String skillStr = "[\"6310225\",\"6310051\",\"6310053\",\"6310239\",\"6310240\",\"6310236\",\"6310044\",\"6310045\",\"6310230\",\"6310011\",\"6310013\",\"6310015\"]";

        getMarvelResult(cardJsonStr,skillStr);
    }

    public static Map<String, String> getMarvelResult(String cardJsonStr,String skillStr) {

        skillStr = skillStr.replace("\"", "").replace("[", "").replace("]", "");
        Map<String, String[]> skillMap = new HashMap<>();
        Map<String, MarvelCard[]> marvelCardMap = MakeJson.getCards(cardJsonStr);
        String[] skillArr = skillStr.split(",");
        Map<String, String> resultMap = new HashMap<>();

        int i = 0;
        for (String key : marvelCardMap.keySet()) {
            if (!key.equals("-1")) {
                String[] skills = new String[4];
                for (int j = 0; j <= 3; j++) {
                    skills[j] = skillArr[i];
                    i++;
                }
                skillMap.put(key, skills);
            }
        }

        for (String key : marvelCardMap.keySet()) {

            String campInfoSkill = ExcelData.campInfoSkill;
            String campInfoCard = ExcelData.campInfoCard;

            if (!key.equals("-1")) {
                int skillIndex = 1;
                for (String skillValue : skillMap.get(key)) {
                    campInfoSkill = campInfoSkill.replaceFirst("skillID" + skillIndex, skillValue);
                    skillIndex++;
                }
            } else {
                campInfoSkill = "{\"skill\":[],";
            }

            int cardIndex = 0;
            for (MarvelCard cardValue : marvelCardMap.get(key)) {
                cardIndex++;
                if (cardIndex == 1) {
                    campInfoCard = campInfoCard.replace("cardID01" , cardValue.getCardId());
                    campInfoCard = campInfoCard.replace("cardNum01" , "" + cardValue.getCardNum());
                } else {
                    campInfoCard = campInfoCard.replace("cardID" + cardIndex, cardValue.getCardId());
                    campInfoCard = campInfoCard.replace("cardNum" + cardIndex, "" + cardValue.getCardNum());
                }

            }

        /*    System.out.println(key);
            System.out.println(campInfoSkill + campInfoCard);*/

            resultMap.put(key, campInfoSkill + campInfoCard);
        }

        return resultMap;
    }


}
