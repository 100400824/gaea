package com.gaea.server.lfsAPI;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;

public class HttpClientFactory {

    private static CloseableHttpClient client;

    public static HttpClient getHttpsClient() throws Exception {

        if (client != null) {
            return client;
        }
        SSLContext sslcontext = SSLContexts.custom().useSSL().build();
        sslcontext.init(null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        client = HttpClients.custom().setSSLSocketFactory(factory).build();

        return client;
    }

    public static void releaseInstance() {
        client = null;
    }
}