package com.gaea.utls.httpclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DoPost {

    private static final CookieStore cookieStore = new BasicCookieStore();
    private static final CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionTimeToLive(5000, TimeUnit.MILLISECONDS).build();

    public static Object postFormData(FormDataVO formDataVO, Boolean runIs) throws Exception {

        HttpClientContext httpClientContext = HttpClientContext.create();
        httpClientContext.setCookieStore(cookieStore);

        //添加请求地址
        HttpPost httpPost = new HttpPost(formDataVO.getUrl());

        //添加请求头信息
        for (String key : formDataVO.getHeaders().keySet()) {
            httpPost.setHeader(key, formDataVO.getHeaders().get(key).toString());
        }

        //添加请求参数
        ArrayList<NameValuePair> p = new ArrayList<>();
        for (String key : formDataVO.getContent().keySet()) {
            p.add(new BasicNameValuePair(key, formDataVO.getContent().get(key).toString()));
        }

        httpPost.setEntity(new UrlEncodedFormEntity(p, "UTF-8"));

        //判断是否请求
        if (runIs) {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String resultEntity = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(resultEntity);
            return  resultEntity;
        }

        return httpPost;
    }

}
