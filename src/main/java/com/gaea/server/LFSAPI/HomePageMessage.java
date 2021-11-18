package com.gaea.server.LFSAPI;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageMessage {

    public static CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionTimeToLive(5000, TimeUnit.MILLISECONDS).build();

    public static void main(String[] args) throws Exception{

        homePageMessageCheck();
    }

    public static void homePageMessageCheck() throws Exception {

        String url = "https://api.iyingdi.com/web/feed/recommend-content-list";

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
        httpPost.setHeader("Login-Token","a3849cd1-4466-4dae-a491-805d526412b9");
        httpPost.setHeader("Platform","pc");

        ArrayList<NameValuePair> p = new ArrayList<>();
        p.add(new BasicNameValuePair("client_guest_id", "15334543"));
        p.add(new BasicNameValuePair("refresh_action", "1"));
        p.add(new BasicNameValuePair("size", "30"));
        p.add(new BasicNameValuePair("timestamp", "1636690642"));
        p.add(new BasicNameValuePair("sign", "96972f36b152d565c9bb450cc07d3ce3"));
        httpPost.setEntity(new UrlEncodedFormEntity(p,"UTF-8"));

        List<String> list = new ArrayList<>();
        for (int i=1; i<=50; i++){
            HttpResponse response = httpClient.execute(httpPost);
            String resultEntity = EntityUtils.toString(response.getEntity(), "utf-8");
            CheckRepeat.checkID(list,resultEntity);
        }

    }
}
