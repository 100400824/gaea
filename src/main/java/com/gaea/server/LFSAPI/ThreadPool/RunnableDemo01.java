package com.gaea.server.LFSAPI.ThreadPool;

import com.gaea.utls.httpclient.DoPost;
import com.gaea.utls.httpclient.FormDataVO;

import java.util.HashMap;
import java.util.Map;

public class RunnableDemo01 {
    public static void main(String[] args) throws Exception {

        FormDataVO formDataVO1 = new FormDataVO();

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerMap.put("Login-Token", "436bc70a-8ac7-43da-8cb5-9366d2c88dce");
        headerMap.put("Platform", "pc");

        Map<String, Object> contentMap = new HashMap<>();
        contentMap.put("tag_id", "17");
        contentMap.put("timestamp", "1638432872");
        contentMap.put("sign", "cca7a37ebc7c392f38a24297440b7690");

        formDataVO1.setUrl("https://api.iyingdi.com/web/feed/top-content");
        formDataVO1.setHeaders(headerMap);
        formDataVO1.setContent(contentMap);


        FormDataVO formDataVO2 = new FormDataVO();

        Map<String, Object> headerMap2 = new HashMap<>();
        headerMap2.put("Content-Type", "application/x-www-form-urlencoded");
        headerMap2.put("Login-Token", "436bc70a-8ac7-43da-8cb5-9366d2c88dce");
        headerMap2.put("Platform", "pc");

        Map<String, Object> contentMap2 = new HashMap<>();
        contentMap2.put("tag_id", "18");
        contentMap2.put("timestamp", "1638433324");
        contentMap2.put("sign", "91058501cfdd3dfaeac98ffc4bb8b0da");

        formDataVO2.setUrl("https://api.iyingdi.com/web/feed/top-content");
        formDataVO2.setHeaders(headerMap2);
        formDataVO2.setContent(contentMap2);

        MyThread mt1 = new MyThread(DoPost.postFormData(formDataVO1, false));
        MyThread mt2 = new MyThread(DoPost.postFormData(formDataVO2, false));
        Thread t1 = new Thread(mt1);
        Thread t2 = new Thread(mt2);
        t1.start();
        t2.start();
    }

}
