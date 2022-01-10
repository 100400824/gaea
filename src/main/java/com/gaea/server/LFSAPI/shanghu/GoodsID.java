package com.gaea.server.LFSAPI.shanghu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gaea.server.LFSAPI.BuildSign;
import com.gaea.utls.httpclient.DoPost;
import com.gaea.utls.httpclient.FormDataVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* 下架指定token下的所有订单
* */
public class GoodsID {

    public static void main(String[] args) throws Exception {

       /* String token = "19e14d71-e89a-41ba-8e48-316ea0920074";
        xiaJia(token);*/

        double money = 300.00;
        calcMoney(money);
    }


    //订单处理费2元+技术服务费2%+转账手续费 1%，单笔服务费封顶15
    public static void calcMoney(double money){
        double chuliF = 2;
        double jishuF = 0.02;
        double zhuanzhangF = 0.01;
        double guanliF;

        guanliF = chuliF + money * jishuF + money * zhuanzhangF;

        if (guanliF >= 15.00){
            System.out.println("管理费：" + 15);
            System.out.println("商户收益：" + (money-15));
        }else {
            System.out.println("管理费：" + guanliF);
            System.out.println("商户收益：" + (money-guanliF));
        }


    }

    public static void xiaJia(String token) throws Exception {
        String key = "39726dfcfd448a1b804a1e0b736bff0c";
        String size = "0";
        size = getGoodList(token,size,key).toString();
        System.out.println(size);

        List<String> goodList = new ArrayList<String>();
        goodList = (ArrayList<String>)getGoodList(token,size,key);
        System.out.println(goodList);

        Map<String ,Object> headerMap = new HashMap<>();
        headerMap.put("login-token",token);
        headerMap.put("content-type","application/x-www-form-urlencoded");
        headerMap.put("app-version","1001");

        Map<String ,Object> questMap = new HashMap<>();

        for (String goodStr : goodList){

            questMap = new HashMap<>();
            questMap.put("good_id",goodStr);
            questMap.put("timestamp","1640345628");
            questMap.put("update_json","{\"is_visible\":0}");
            questMap.put("sign", BuildSign.getSign(questMap,key));

            FormDataVO formDataVO = new FormDataVO();
            formDataVO.setUrl("https://trade-api-test.iyingdi.com/admin/goods/update");
            formDataVO.setHeaders(headerMap);
            formDataVO.setContent(questMap);

            DoPost.postFormData(formDataVO,true);
        }
    }

    public static Object getGoodList(String token,String size,String key) throws Exception{

        Map<String ,Object> headerMap = new HashMap<>();
        headerMap.put("login-token",token);
        headerMap.put("content-type","application/x-www-form-urlencoded");
        headerMap.put("app-version","1001");

        Map<String ,Object> questMap = new HashMap<>();
        questMap.put("is_visible","1");
        questMap.put("page","1");
        questMap.put("page_size","1");
        if (!size.equals("0")){
            questMap.put("page_size",size);
        }
        questMap.put("timestamp","1640338398");
        questMap.put("sign", BuildSign.getSign(questMap,key));

        FormDataVO formDataVO = new FormDataVO();
        formDataVO.setUrl("https://trade-api-test.iyingdi.com/admin/goods/list");
        formDataVO.setHeaders(headerMap);
        formDataVO.setContent(questMap);

        String responseStr = DoPost.postFormData(formDataVO,true).toString();

        JSONObject jsonObject = JSON.parseObject(responseStr);
        JSONArray jsonArray =  jsonObject.getJSONObject("data").getJSONArray("rows");

        String dataNum = jsonObject.getJSONObject("data").getString("total");

        if (!size.equals(dataNum) ){
            return dataNum;
        }

        List<String> goodList = new ArrayList<String>();

        for (int i=0; i<jsonArray.size(); i++){
            goodList.add(jsonArray.getJSONObject(i).getString("good_id"));
        }

        return goodList;
    }


}
