package com.gaea.server.LFSAPI.shanghu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gaea.server.LFSAPI.BuildSign;
import com.gaea.utls.httpclient.DoPost;
import com.gaea.utls.httpclient.FormDataVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/*
 * 下架指定token下的所有订单
 * */
public class MiaoSha {

    public static String token = "937b2bca-b540-4479-8f5d-9aaeb781557d";
    public static String key = "39726dfcfd448a1b804a1e0b736bff0c";

    public static void main(String[] args) throws Exception {

        String startdate = "2022-04-14 19:10:00";
        String enddate = "2022-04-14 19:30:00";
        String startTime = dateToStamp(startdate);
        String endTime = dateToStamp(enddate);

        String name = "验收测试用，呸！！";
        String activityId = "seckill_zLOIpn22P7_20283018";
        String goodId = "areward_5orOWQ5bdP_20283018_seckill";

//        createActivity(name,startTime,endTime);
        addGoods(activityId,goodId);
//        updateActive(activityId);
//        deleteGood(activityId,goodId);
        getList();
    }


    //查询秒杀活动列表
    public static void getList() throws Exception {

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("login-token", token);
        headerMap.put("content-type", "application/x-www-form-urlencoded");

        Map<String, Object> questMap = new HashMap<>();
        questMap.put("page", "1");
        questMap.put("page_size", "10");
        questMap.put("timestamp", "1649902795");
        questMap.put("sign", BuildSign.getSign(questMap, key));

        FormDataVO formDataVO = new FormDataVO();
        formDataVO.setUrl("https://trade-api-test.iyingdi.com/admin/seckill-activity/list");
        formDataVO.setHeaders(headerMap);
        formDataVO.setContent(questMap);

        DoPost.postFormData(formDataVO, true);
    }

    //创建秒杀活动
    public static void createActivity(String name,String starTime,String endTime) throws Exception {

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("login-token", token);
        headerMap.put("content-type", "application/x-www-form-urlencoded");

        Map<String, Object> questMap = new HashMap<>();
        questMap.put("name", name);
        questMap.put("img", "https://pic.iyingdi.com/trade_card_admin/20283018/c94f71b2a1a8411c95acac0bee9b64d5-0000.png");
        questMap.put("start_time", starTime);
        questMap.put("end_time", endTime);
        questMap.put("timestamp", "1649902795");
        questMap.put("sign", BuildSign.getSign(questMap, key));

        FormDataVO formDataVO = new FormDataVO();
        formDataVO.setUrl("https://trade-api-test.iyingdi.com/admin/seckill-activity/create");
        formDataVO.setHeaders(headerMap);
        formDataVO.setContent(questMap);

        DoPost.postFormData(formDataVO, true);
    }

    //秒杀活动添加商品
    public static void addGoods(String activityId, String goodId) throws Exception {

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("login-token", token);
        headerMap.put("content-type", "application/x-www-form-urlencoded");

        Map<String, Object> questMap = new HashMap<>();
        questMap.put("activity_id", activityId);
        questMap.put("good_id", goodId);
        questMap.put("timestamp", "1649902795");
        questMap.put("sign", BuildSign.getSign(questMap, key));

        FormDataVO formDataVO = new FormDataVO();
        formDataVO.setUrl("https://trade-api-test.iyingdi.com/admin/seckill-activity/add-good");
        formDataVO.setHeaders(headerMap);
        formDataVO.setContent(questMap);

        DoPost.postFormData(formDataVO, true);
    }

    //上架秒杀活动
    public static void updateActive(String activityId) throws Exception {

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("login-token", token);
        headerMap.put("content-type", "application/x-www-form-urlencoded");

        Map<String, Object> questMap = new HashMap<>();
        questMap.put("activity_id", activityId);
        questMap.put("update_json", "{\"is_visible\":1}");
        questMap.put("timestamp", "1649902795");
        questMap.put("sign", BuildSign.getSign(questMap, key));

        FormDataVO formDataVO = new FormDataVO();
        formDataVO.setUrl("https://trade-api-test.iyingdi.com/admin/seckill-activity/update");
        formDataVO.setHeaders(headerMap);
        formDataVO.setContent(questMap);

        DoPost.postFormData(formDataVO, true);
    }

    //删除商品
    public static void deleteGood(String activityId, String goodId) throws Exception {

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("login-token", token);
        headerMap.put("content-type", "application/x-www-form-urlencoded");

        Map<String, Object> questMap = new HashMap<>();
        questMap.put("activity_id", activityId);
        questMap.put("good_id", goodId);
        questMap.put("timestamp", "1649902795");
        questMap.put("sign", BuildSign.getSign(questMap, key));

        FormDataVO formDataVO = new FormDataVO();
        formDataVO.setUrl("https://trade-api-test.iyingdi.com/admin/seckill-activity/delete-good");
        formDataVO.setHeaders(headerMap);
        formDataVO.setContent(questMap);

        DoPost.postFormData(formDataVO, true);
    }

    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime()/1000;
        res = String.valueOf(ts);
        return res;
    }

}
