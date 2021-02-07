package com.shp.dev.network.common.util.request;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicLineParser;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 发送文件
 * @CreateTime: 2020/12/4 23:54
 * @PackageName: com.tieta.ai.common.utils.request
 * @ProjectName: ai
 */
@Slf4j
public class HttpFileUtils {


    @Test
    public void testModel() {
        String url = "123.182.146.55:10086/file";
        //String url = "http://10.8.240.23:10086/file";
        Map map = new HashMap<>();
        map.put("image", new File("e:/130900000013200000231608626207381.jpg"));
        String s = httpPostForFile(url, map, new HashMap<>(), new HashMap<>());
        System.out.println(s);

    }


    @Test
    public void testUpFile() {
        String url = "http://localhost:8080/common/upload";
        Map fileMap = new HashMap<>();
        fileMap.put("file", new File("e:/130900000013200000231608626207381.jpg"));
        Map params = new HashMap<>();
        params.put("fileName", "testFIleUpload.jpg");
        params.put("frist", "frist");
        params.put("last", "last");
        String s = httpPostForFile(url, fileMap, params, new HashMap<>());
        System.out.println(s);

    }


    public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/" +
            "537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36";

    public static String httpPostForFile(String url, Map<String, Object> fileMap, Map<String, Object> params,
                                         Map<String, String> headers) {
        HttpClient httpClient = buildHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeaders(buildHeader(headers));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(java.nio.charset.Charset.forName("UTF-8"));
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            if (fileMap != null && fileMap.size() > 0) {
                for (Entry<String, Object> file : fileMap.entrySet()) {
                    FileBody fileBody = new FileBody((File) file.getValue(), ContentType.DEFAULT_BINARY);
                    builder = MultipartEntityBuilder.create();
                    builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                    builder.addPart(file.getKey(), fileBody);
                }
            }

            ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
            for (Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() == null)
                    continue;

                builder.addTextBody(entry.getKey(), entry.getValue().toString(), contentType);
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);

            String result = "";
            Integer statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != null && statusCode.intValue() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            } else {
                log.error("请求地址" + url + "错误状态" + response.getStatusLine().getStatusCode());
                EntityUtils.consume(entity);
            }
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "";
    }

    public static HttpClient buildHttpClient() {
        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> httpConnectionFactory = new ManagedHttpClientConnectionFactory(
                new DefaultHttpRequestWriterFactory(),
                new DefaultHttpResponseParserFactory(new MyLineParser(),
                        new DefaultHttpResponseFactory()));
        PoolingHttpClientConnectionManager pccm = new PoolingHttpClientConnectionManager(
                httpConnectionFactory);
        pccm.setDefaultMaxPerRoute(50);
        pccm.setMaxTotal(300);
        HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000)
                .setConnectTimeout(10000).build())
                .setRedirectStrategy(new LaxRedirectStrategy())
                .setConnectionManager(pccm)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(1, true))
                .setUserAgent(DEFAULT_USER_AGENT).build();
        return httpClient;
    }

    public static Header[] buildHeader(Map<String, String> params) {
        Header[] headers = null;
        if (params != null && params.size() > 0) {
            headers = new BasicHeader[params.size()];
            int i = 0;
            for (Entry<String, String> entry : params.entrySet()) {
                headers[i] = new BasicHeader(entry.getKey(), entry.getValue());
                i++;
            }
        }
        return headers;
    }

    /**
     * 如果返回不规范的HTTP头，实现兼容.
     */
    private static class MyLineParser extends BasicLineParser {
        @Override
        public Header parseHeader(
                CharArrayBuffer buffer) throws ParseException {
            try {
                return super.parseHeader(buffer);
            } catch (ParseException ex) {
                return new BasicHeader("Invalid", buffer.toString());
            }
        }
    }
}
