package com.gaea.utls.httpclient;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DoGet {

    private static final CookieStore cookieStore = new BasicCookieStore();
    private static final CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionTimeToLive(5000, TimeUnit.MILLISECONDS).build();

    public static void main(String[] args) throws Exception {

        String url = "https://hsreplay.net/analytics/query/list_decks_by_opponent_win_rate_v2/?GameType=RANKED_WILD&LeagueRankRange=PLATINUM&Region=ALL&TimeRange=LAST_30_DAYS&PilotExperience=ALL";

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("cookie", "GA1.2.224394321.1645526998; _gid=GA1.2.1061133998.1645526998; __qca=P0-458259882-1645526999145; csrftoken=qNmGMkj3BwM7oR6sEH5xmhrKk0CFigEBDhvW8cthNLplDvVoZjEmsuyKiT2zAEwd; sessionid=36vk8uyfzibklh88ybkyvnuzezqcbfew; cf_chl_2=985e1767e7057dd; cf_chl_prog=x13; cf_clearance=XY9NJkcNMBlTCovR3ME9JzXnXfn73yVb8dGAIE_JC_A-1645600646-0-150; _gat=1; rank-range=DIAMOND_THROUGH_LEGEND");
        headerMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36");

        RequestVO requestVO = new RequestVO();
        requestVO.setUrl(url);
        requestVO.setHeaders(headerMap);

        getURL(requestVO, true);

    }

    public static Object getURL(RequestVO requestVO, Boolean runIs) throws Exception {

        HttpClientContext httpClientContext = HttpClientContext.create();
        httpClientContext.setCookieStore(cookieStore);

        //添加请求地址
        HttpGet httpGet = new HttpGet(requestVO.getUrl());

        //添加请求头信息
        for (String key : requestVO.getHeaders().keySet()) {
            httpGet.setHeader(key, requestVO.getHeaders().get(key).toString());
        }

        //判断是否请求
        if (runIs) {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String resultEntity = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(resultEntity);
            return resultEntity;
        }

        return httpGet;
    }


}
