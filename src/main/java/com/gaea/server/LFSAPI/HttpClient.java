package com.gaea.server.LFSAPI;

import com.gaea.utls.ExcelTest;
import com.gaea.utls.FileManage;
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
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    private static CookieStore cookieStore = new BasicCookieStore();
    private static CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionTimeToLive(5000, TimeUnit.MILLISECONDS).build();
    private static HttpGet httpGet;
    private static HttpPost httpPost;
    private static CloseableHttpResponse response;
    private static String resultEntity;

    private static String[] context, img, rich;

    public static void main(String[] args) throws Exception {

        addData("55");
    }

    public static String addData(String id) throws Exception{

        getCaseValue("添加内容", FileManage.shenheADDcontextCasePath);

        int num = 0;
        for (int i=1; i<=1; i++) {
            postFormData(context[i].replaceAll("\r|\n", ""),img[i],rich[i] ,id);
            num ++;
        }
        System.out.println("发送数量："+num);
        return  "" + num;
    }

    public static void postFormData(String text,String img,String rich,String id) throws Exception{
        HttpClientContext httpClientContext = HttpClientContext.create();
        httpClientContext.setCookieStore(cookieStore);

        String url = "http://context-review-test.gaeamobile.net/api/context-check";
        httpPost = new HttpPost(url);

        httpPost.setHeader("Login-Token","nologin");
        httpPost.setHeader("Platform","pc");
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        String context = "{\"img\":\""+img+"\",\"content\":\""+text+"\",\"rich\":\""+rich+"\"}";
        long ticketID = Calendar.getInstance().getTimeInMillis();
        ArrayList<NameValuePair> p = new ArrayList<>();
        p.add(new BasicNameValuePair("app_id", "41"));
        p.add(new BasicNameValuePair("event_id",id));
        p.add(new BasicNameValuePair("event_context",context));
        p.add(new BasicNameValuePair("ticket_id","" + ticketID));
        p.add(new BasicNameValuePair("sign",BuildSign.getSign(BuildSign.pToMap(p))));

        httpPost.setEntity(new UrlEncodedFormEntity(p,"UTF-8"));

        response = httpClient.execute(httpPost);

        resultEntity = EntityUtils.toString(response.getEntity(), "utf-8");


//        System.out.println(response);
//        System.out.println(p);
        System.out.println(resultEntity);

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

        context = ExcelTest.getCell(casePath, sheetName, 1);
        img = ExcelTest.getCell(casePath, sheetName, 2);
        rich = ExcelTest.getCell(casePath, sheetName, 3);
    }
}
