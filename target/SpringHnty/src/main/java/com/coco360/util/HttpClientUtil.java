package com.coco360.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class HttpClientUtil {



    public static String doGet(String url, Map<String, String> param, String openid) throws IOException, URISyntaxException {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            if (openid != null) {
                httpGet.setHeader("openid", openid);
            }

            // 执行请求
            RequestConfig config = RequestConfig.custom().setConnectTimeout(1000 * 60) //连接超时时间
                    .setConnectionRequestTimeout(1000 * 60) //从连接池中取的连接的最长时间
                    .setSocketTimeout(1000 * 60) //数据传输的超时时间
                    .build();
            httpGet.setConfig(config);
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200 || statusCode == 401 || statusCode == 304) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw e;

        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
    public static String doGet(String url, Map<String, String> param, String openid,int readTimeOut) throws IOException, URISyntaxException {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            if (openid != null) {
                httpGet.setHeader("openid", openid);
                httpGet.setHeader("User-Agent:","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36");
            }

            // 执行请求
            RequestConfig config = RequestConfig.custom().setConnectTimeout(1000 * 60) //连接超时时间
                    .setConnectionRequestTimeout(1000 * 60) //从连接池中取的连接的最长时间
                    .setSocketTimeout(readTimeOut) //数据传输的超时时间
                    .build();
            httpGet.setConfig(config);
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200 || statusCode == 401 || statusCode == 304) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw e;

        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url, String openid) throws Exception {
        return doGet(url, null, openid);
    }


    public static String doPost(String url, String str) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();


        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求

            HttpPost httpPost = new HttpPost(url);
            RequestConfig config = RequestConfig.custom().setConnectTimeout(1000 * 60) //连接超时时间
                    .setConnectionRequestTimeout(1000 * 60) //从连接池中取的连接的最长时间
                    .setSocketTimeout(1000 * 60) //数据传输的超时时间
                    .build();
            httpPost.setConfig(config);
            if (str == null) {
                return null;
            }
            HttpEntity httpEntity = new StringEntity(str);
            httpPost.setEntity(httpEntity);

            // 创建参数列表
            /*if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, (String) param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }*/
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }




    /**
     * 发送post请求
     *
     * @param url        路径
     * @param jsonObject 参数(json类型)
     * @param encoding   编码格式
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String sendJson(String url, JSONObject jsonObject, String encoding, String value) throws ParseException, IOException {
        String body = "";

        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();

        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000 * 60) //连接超时时间
                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
                .setSocketTimeout(1000 * 60) //数据传输的超时时间
                .build();
        httpPost.setConfig(config);
        //装填参数
        StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        //设置参数到请求对象中
        httpPost.setEntity(s);
//        System.out.println("请求地址："+url);
//        System.out.println("请求参数："+nvps.toString());

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
//        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        if (value !=null){
            httpPost.setHeader("openid", value);
        }

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }

    public static Integer getStatus(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            return response.getStatusLine().getStatusCode();
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


}