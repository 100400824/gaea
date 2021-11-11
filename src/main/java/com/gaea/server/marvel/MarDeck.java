package com.gaea.server.marvel;

import com.gaea.server.LFSAPI.BuildSign;
import com.gaea.utls.FileManage;
import com.gaea.utls.publicTool.ExcelTest;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MarDeck {

    private static CookieStore cookieStore = new BasicCookieStore();
    private static CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionTimeToLive(5000, TimeUnit.MILLISECONDS).build();
    private static HttpGet httpGet;
    private static HttpPost httpPost;
    private static CloseableHttpResponse response;
    private static String resultEntity;

    private static String[] marvelCardExcel, marvelSkillExcel;

    public static void main(String[] args) throws Exception {

        postFormData();
    }

    public static void postFormData() throws Exception {

        //漫威套卡创建
        String key = "9a1603fcbf889305df7b59c5561dd926";

        HttpClientContext httpClientContext = HttpClientContext.create();
        httpClientContext.setCookieStore(cookieStore);

        String url = "https://tool-test.iyingdi.com/marvel/battle-web/deck-create";
        httpPost = new HttpPost(url);

        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Login-Token", "49048e46-ec0c-4446-b2a9-3de8420c8842");
        httpPost.setHeader("Platform", "pc");
        httpPost.setHeader("tool-id", "marvel");

        getCaseValue("sheet1", FileManage.marvelPath);

        for (int i = 1; i < marvelCardExcel.length; i++) {

            Map<String, String> resultMap;
            resultMap = MarvelRequest.getMarvelResult(marvelCardExcel[i], marvelSkillExcel[i]);
            ArrayList<NameValuePair> p = new ArrayList<>();

            int index = 1;
            for (String mapKey : resultMap.keySet()) {

                if (mapKey.equals("-1")) {
                    p.add(new BasicNameValuePair("camp_neutral", "-1"));
                    p.add(new BasicNameValuePair("camp_neutral_info", resultMap.get("-1")));
                } else {
                    p.add(new BasicNameValuePair("camp" + index, mapKey));
                    p.add(new BasicNameValuePair("camp" + index + "_info", resultMap.get(mapKey)));
                    index++;
                }
            }

            p.add(new BasicNameValuePair("timestamp", "1635823905"));
            p.add(new BasicNameValuePair("sign", BuildSign.getSign(BuildSign.pToMap(p), key)));

            httpPost.setEntity(new UrlEncodedFormEntity(p, "UTF-8"));
            response = httpClient.execute(httpPost);
            resultEntity = EntityUtils.toString(response.getEntity(), "utf-8");

            System.out.println(resultEntity);
            Thread.sleep(1000);

        }


    }

    public static CloseableHttpClient createSSLClientDefault() {

        try {

            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();

        } catch (KeyManagementException e) {

            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        } catch (KeyStoreException e) {

            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    private static void getCaseValue(String sheetName, String casePath) throws Exception {

        marvelCardExcel = ExcelTest.getCell(casePath, sheetName, 1);
        marvelSkillExcel = ExcelTest.getCell(casePath, sheetName, 2);
    }
}
