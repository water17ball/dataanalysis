package cn.wp.bigdata.dataanalysis.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * http client util
 */
@Slf4j
public class HttpClientUtils {

    /**
     * 多个
     */
    @Setter
    @Getter
    private String name = "default";
    /**
     * 多域名共享同一httpClient时需要额外配置路由。格式：host:port，仅配置host默认port=80
     */
    @Setter
    @Getter
    private List<String> hostPorts;

    /**
     * http client connectTimeout ms
     */
    @Setter
    @Getter
    private int connectTimeout = 10 * 1000;
    /**
     * http client socketTimeout ms
     */
    @Setter
    @Getter
    private int socketTimeout = 30 * 1000;

    /**
     * pool manager maxTotal, 连接上限
     * <pre>基本没什么用</pre>
     */
    @Setter
    @Getter
    private int maxTotal = 1024;
    /**
     * pool manager defaultMaxPerRoute；
     * 此配置最影响性能。池子里打开连接的数量
     */
    @Setter
    @Getter
    private int defaultMaxPerRoute = 128;
    /**
     * http client keeAlive ms
     */
    @Setter
    @Getter
    private long keepAlive = 30 * 1000;

    @Getter
    private CloseableHttpClient httpClient;

    /**
     * 根据配置创建一个可关闭的httpClient。带有连接池管理，因此保持一个对象即可。千万不可创建太多，会占用掉所有端口，将服务器打死。
     *
     * @return CloseableHttpClient
     */
    private CloseableHttpClient createHttpClient() {
        // 连接池
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(keepAlive, TimeUnit.MILLISECONDS);

        manager.setMaxTotal(maxTotal);
        // 默认最大连接数，最影响性能。可使用 netstat -a|grep host查看连接情况，大量连接情况下，应该与此值一致。
        manager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        // 多域名需要额外配置连接限制，否则会产生大量连接，迅速耗尽端口
        if (hostPorts != null) {
            for (String hostPort : hostPorts) {
                HTTPUrlInfo urlInfo = parseUrl(hostPort);
                manager.setMaxPerRoute(new HttpRoute(new HttpHost(urlInfo.host, urlInfo.port)), defaultMaxPerRoute);
            }
        }

        // 防止某些情况下 keepLive 不生效
        ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(
                    HttpResponse response,
                    HttpContext context) {
                long tKeepAlive = super.getKeepAliveDuration(response, context);
                if (tKeepAlive == -1) {
                    // Keep connections alive 5 seconds if a keep-alive value
                    // has not be explicitly set by the server
                    tKeepAlive = keepAlive;
                }
                return tKeepAlive;
            }
        };
        return HttpClients.custom()
                .setConnectionManager(manager)
                .setKeepAliveStrategy(keepAliveStrat)
                .build();
    }

    class HTTPUrlInfo {
        String host;
        int port;
    }

    private HTTPUrlInfo parseUrl(String url) {
        HTTPUrlInfo result = null;
        String[] arr = url.split(":");
        switch (arr.length) {
            case 0:
                break;
            case 1:
                result = new HTTPUrlInfo();
                result.host = arr[0];
                result.port = 80;
                break;
            default:
                result = new HTTPUrlInfo();
                result.host = arr[0];
                result.port = Integer.valueOf(arr[1]);
                break;

        }
        return result;
    }

    /**
     * 初始化httpClient
     */
    public void init() {
        httpClient = createHttpClient();
        log.info("httpClient init. name={} ,hostPorts={} " +
                        ",connectTimeout={} ,socketTimeout={} " +
                        ",maxTotal={}, defaultMaxPerRoute={}, keepAlive={}",
                name, hostPorts,
                connectTimeout, socketTimeout,
                maxTotal, defaultMaxPerRoute, keepAlive);
    }

    /**
     * 关闭httpClient
     */
    public void destroy() {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException ignored) {
            }
            log.info("httpClient {} close.", name);
        }
    }

    /**
     * 根据配置创建一个 http RequestConfig
     *
     * @return RequestConfig
     */
    public RequestConfig createRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(this.connectTimeout)
                .setSocketTimeout(this.socketTimeout)
                .setCookieSpec(CookieSpecs.STANDARD).build();
    }

    /**
     * post json
     *
     * @param url  url
     * @param json json body
     * @param gzip 是否使用gizp格式发送json
     * @return result
     */
    public HttpResult postJson(String url, String json, boolean gzip) throws Exception {
        if (httpClient == null) {
            throw new Exception("httpClient is null");
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(createRequestConfig());
        httpPost.setHeader("Connection", "Keep-Alive");
        httpPost.setHeader("Accept", "application/json");
        if (gzip) {
            ByteArrayEntity entity = new ByteArrayEntity(GzipUtils.compress(json, GzipUtils.GZIP_ENCODE_UTF_8)
                    , ContentType.APPLICATION_JSON);
            httpPost.setHeader("Content-Encoding", "gzip");
            httpPost.setEntity(entity);
        } else {
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
        }

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpResult result = new HttpResult();
            StatusLine statusLine = response.getStatusLine();
            result.setHttpCode(statusLine.getStatusCode());
            result.setRespBody(EntityUtils.toString(response.getEntity()));
            return result;
        } finally {
            try {
                httpPost.abort();
                httpPost.releaseConnection();
            } catch (Exception ignored) {
            }
        }
    }

    @Data
    public static class HttpResult {
        private int httpCode;
        private String respBody;
    }
}
