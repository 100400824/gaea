package com.gaea.server.LFSAPI.ThreadPool;

import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.TimeUnit;

// 实现Runnable接口，作为线程的实现类
class MyThread implements Runnable {

    private static final CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionTimeToLive(5000, TimeUnit.MILLISECONDS).build();

    private final HttpPost httpPost;

    public MyThread(HttpPost httpPost) {
        this.httpPost = httpPost;
    }

    @Override
    public void run() {

        for (int i = 0; i < 1; i++) {
            try {
                System.out.println(System.currentTimeMillis());
                CloseableHttpResponse response = httpClient.execute(httpPost);
                String resultEntity = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(resultEntity);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

}

