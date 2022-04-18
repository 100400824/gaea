package com.gaea.utls.publicTool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.json.JSONArray;

public class DoJson {

    public static void main(String[] args) {


    }

    public static String[] getArray(String jsonStr,String keyWord) {

        JSONObject jsonObject = JSON.parseObject(jsonStr);

        com.alibaba.fastjson.JSONArray family = jsonObject.getJSONArray("data");

        System.out.println(family);

        JSONArray jsonArray = new JSONArray(family);

        int arrLength = jsonArray.length();

        System.out.println(arrLength);

        String[] arr = new String[arrLength];

        for (int i=0; i<arrLength;i++){
            arr[i] = jsonArray.getJSONObject(i).get(keyWord).toString();
        }

        return arr;
    }
}
