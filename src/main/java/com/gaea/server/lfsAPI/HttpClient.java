package com.gaea.server.lfsAPI;

import org.apache.http.Header;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    private static CookieStore cookieStore = new BasicCookieStore();
    private static CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionTimeToLive(6000, TimeUnit.MILLISECONDS).build();
    private static HttpGet httpGet;
    private static HttpPost httpPost;
    private static CloseableHttpResponse response;
    private static String resultEntity;

    public static void main(String[] args) throws Exception {

        postFormData();
    }

    public static void postFormData() throws Exception{
        HttpClientContext httpClientContext = HttpClientContext.create();
        httpClientContext.setCookieStore(cookieStore);

        String url = "https://api-test.iyingdi.com/web/user/login";
        httpPost = new HttpPost(url);

        httpPost.setHeader("Login-Token","nologin");
        httpPost.setHeader("Platform","pc");
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");

        List<NameValuePair> p = new ArrayList();
        p.add(new BasicNameValuePair("password", "123123a"));
        p.add(new BasicNameValuePair("timestamp","1626951229"));
        p.add(new BasicNameValuePair("type","password"));
        p.add(new BasicNameValuePair("username","86_18612977127"));
        p.add(new BasicNameValuePair("sign","a24fe9a32bc5b0b3ee4fc7d9f80d9d9a"));

        httpPost.setEntity(new UrlEncodedFormEntity(p,"UTF-8"));

        response = httpClient.execute(httpPost);

        resultEntity = EntityUtils.toString(response.getEntity(), "utf-8");

        for (Header a :httpPost.getAllHeaders()) {
            System.out.println(a);
        }

        System.out.println(response);
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

}
