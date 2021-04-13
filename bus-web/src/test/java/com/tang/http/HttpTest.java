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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Http测试类
 *
 * @author tang
 * @since 2020-11.21-20:04
 */
public class HttpTest {
    private static final Logger logger = LoggerFactory.getLogger(HttpTest.class);

    @Test(expected = FileNotFoundException.class)
    public void test_https_url() throws IOException, CertificateException, CRLException {
        URL url = new URL("https://login-beta.huawei.com/");
        System.out.println("---- use HttpsURLConnection ----");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        // conn.connect();
        System.out.println("responseCode: " + conn.getResponseCode());
        System.out.println(JSON.toJSONString(conn.getHeaderFields()));
        Certificate[] certs = conn.getServerCertificates();
        System.out.println("certs number: " + certs.length);
        final X509CRL crl = getCrl();
        for (Certificate cert : certs) {
            // System.out.println("Certificate is: " + cert);
            if (cert instanceof X509Certificate) {
                try {
                    X509Certificate x509Certificate = (X509Certificate) cert;
                    System.out.println("cert sn: " + x509Certificate.getSerialNumber());
                    X509CRLEntry revokedCertificate = crl.getRevokedCertificate(x509Certificate.getSerialNumber());
                    if (revokedCertificate != null) {
                        logger.error("this cert is revoked");
                    }
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
        // String crlPath = "/Users/tangan/Downloads/DigiCertGlobalRootCA.crl";
        // String crlPath = "/Users/tangan/Downloads/ssl/test.crl";
        String path = "D:\\codes\\work\\eService_FE\\module_Service\\cloud-transfer\\src\\test\\resources\\client.crl";
        // String crlPath = "/Users/tangan/Downloads/ssl/test.crl";
        String crlPath = path;
        X509CRL crl;
        try (InputStream in = new FileInputStream(new File(crlPath))) {
            crl = (X509CRL) cf.generateCRL(in);
            final Set<? extends X509CRLEntry> revokedCertificates = crl.getRevokedCertificates();
            System.out.println("revoke size: " + revokedCertificates.size());
            revokedCertificates.forEach(cer -> {
                System.out.println(cer.getSerialNumber());
            });
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
            ManagedHttpClientConnection routedConnection = (ManagedHttpClientConnection) context
                .getAttribute(HttpCoreContext.HTTP_CONNECTION);
            SSLSession sslSession = routedConnection.getSSLSession();
            final Certificate[] peerCertificates = sslSession.getPeerCertificates();
            System.out.println("cert length from http client: " + peerCertificates.length);
        };
        // 自定义客户端，加入拦截器
        try (CloseableHttpClient httpClient = HttpClients.custom().addInterceptorFirst(interceptor).build()) {
            // HttpGet httpGet = new HttpGet("https://www.heyefu.cn");
            HttpGet httpGet = new HttpGet("https://127.0.0.1/");
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
            Callable callable;
        }
    }
}