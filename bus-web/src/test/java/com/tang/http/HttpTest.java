package com.tang.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.*;

/**
 * Http测试类
 *
 * @author tang
 * @since 2020-11.21-20:04
 */
public class HttpTest {

    @Test
    public void test_https_url() throws IOException, CertificateException, CRLException {
        URL url = new URL("https://www.heyefu.cn");
        System.out.println("---- use HttpsURLConnection ----");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.connect();
        System.out.println("responseCode: " + conn.getResponseCode());
        System.out.println(JSON.toJSONString(conn.getHeaderFields()));
        Certificate[] certs = conn.getServerCertificates();
        System.out.println("certs number: " + certs.length);
        final X509CRL crl = getCrl();
        for (Certificate cert : certs) {
            // System.out.println("Certificate is: " + cert);
            if (cert instanceof X509Certificate) {
                try {
                    X509CRLEntry revokedCertificate = crl.getRevokedCertificate((X509Certificate) cert);
                    System.out.println("revoke: " + revokedCertificate);
                    ((X509Certificate) cert).checkValidity();
                } catch (CertificateExpiredException | CertificateNotYetValidException cee) {
                    System.out.println("Certificate is expired");
                }
            }
        }
        getResponseBody(conn);
    }

    private X509CRL getCrl() throws CertificateException, IOException, CRLException {
        CertificateFactory cf = CertificateFactory.getInstance("X509");
        String crlPath = "/Users/tangan/Downloads/DigiCertGlobalRootCA.crl";
        X509CRL crl;
        try (InputStream in = new FileInputStream(new File(crlPath))) {
            crl = (X509CRL) cf.generateCRL(in);
            return crl;
        }
    }

    private String getResponseBody(HttpsURLConnection conn) throws IOException {
        try (InputStream in = conn.getInputStream()) {
            StringBuilder result = new StringBuilder();
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes)) != -1) {
                result.append(new String(bytes, 0, len));
            }
            return result.toString();
        }
    }

    @Test
    public void test_use_httpclient() {
        // 设置拦截器，获取服务器证书
        HttpResponseInterceptor interceptor = (response, context) -> {
            ManagedHttpClientConnection routedConnection = (ManagedHttpClientConnection) context.getAttribute(HttpCoreContext.HTTP_CONNECTION);
            SSLSession sslSession = routedConnection.getSSLSession();
            final Certificate[] peerCertificates = sslSession.getPeerCertificates();
            System.out.println("cert length from http client: " + peerCertificates.length);
        };
        // 自定义客户端，加入拦截器
        try (CloseableHttpClient httpClient = HttpClients.custom().addInterceptorFirst(interceptor).build()) {
            HttpGet httpGet = new HttpGet("https://www.heyefu.cn");
            String responseBody = httpClient.execute(httpGet, httpResponse -> {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status < 200 || status >= 300) {
                    // ... handle unsuccessful request
                    System.out.println("request error");
                }
                HttpEntity entity = httpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            });
            // ... do something with response
            System.out.println(responseBody);
        } catch (IOException e) {
            // ... handle IO exception
            e.printStackTrace();
        }
    }
}