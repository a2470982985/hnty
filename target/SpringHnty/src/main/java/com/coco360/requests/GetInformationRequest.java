package com.coco360.requests;

import com.alibaba.fastjson.JSONObject;
import com.coco360.util.HttpClientUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetInformationRequest {
    static String html = null;

    /**
     * 获取name
     *
     * @param openid
     * @return name
     * @throws IOException
     */
    public static String getName(String openid) throws IOException {
//        String html=null;
        try {
            html = HttpClientUtil.doGet("http://www.hntyxxh.com/wechat-api/v2/students", null, openid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (html == null || !html.contains("item_value")) {
            return null;
        }
//        JSONObject jsonObject = JSONObject.parseObject(html);
        Object obj = JSONObject.parseObject((JSONObject.parseArray(JSONObject.parseArray(html).get(0).toString()).get(2)).toString()).get("item_value");
        return obj.toString();
    }

    /**
     * 获取Email地址
     *
     * @param openid
     * @return email
     * @throws IOException
     */
    public static String getEmail(String openid) throws IOException {
//        String html=null;
        try {
            html = HttpClientUtil.doGet("http://www.hntyxxh.com/wechat-api/v2/students", null, openid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (html == null || !html.contains("item_value")) {
            return null;
        }
        Object obj = JSONObject.parseObject((JSONObject.parseArray(JSONObject.parseArray(html).get(1).toString()).get(0)).toString()).get("item_value");
        return obj.toString();
    }

    /**
     * 获取头像
     *
     * @param openid
     * @return 头像url
     * @throws IOException
     */
    public static String getAvatar(String openid) throws IOException {
//        String html=null;
        try {
            html = HttpClientUtil.doGet("http://www.hntyxxh.com/wechat-api/v2/students", null, openid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (html == null || !html.contains("item_value")) {
            return null;
        }
        Object obj = JSONObject.parseObject((JSONObject.parseArray(JSONObject.parseArray(html).get(0).toString()).get(0)).toString()).get("item_value");
        return obj.toString();
    }

    /**
     * 获取name和Email
     *
     * @param openid
     * @return
     * @throws IOException
     */
    public static Map<String, Object> getNameAndEmail(String openid) throws Exception {
//        String html=null;
        Map<String, Object> map = new HashMap<>();

        html = HttpClientUtil.doGet("http://www.hntyxxh.com/wechat-api/v2/students", null, openid);

        if (!html.contains("item_value")) {
            return null;
        }
        Object email = JSONObject.parseObject((JSONObject.parseArray(JSONObject.parseArray(html).get(1).toString()).get(0)).toString()).get("item_value");
        Object name = JSONObject.parseObject((JSONObject.parseArray(JSONObject.parseArray(html).get(0).toString()).get(2)).toString()).get("item_value");
        map.put("name", name);
        map.put("email", email);

        return map;
    }

    /**
     * 获取name和Email和Avatar
     *
     * @param openid
     * @return
     * @throws IOException
     */
    public static Map<String, Object> getNameAndEmailAndAvatar(String openid) throws IOException {
//        String html=null;
        Map<String, Object> map = new HashMap<>();
        try {
            html = HttpClientUtil.doGet("http://www.hntyxxh.com/wechat-api/v2/students", null, openid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (html == null || !html.contains("item_value")) {
            return null;
        }
        Object email = JSONObject.parseObject((JSONObject.parseArray(JSONObject.parseArray(html).get(1).toString()).get(0)).toString()).get("item_value");
        Object name = JSONObject.parseObject((JSONObject.parseArray(JSONObject.parseArray(html).get(0).toString()).get(2)).toString()).get("item_value");
        Object avatar = JSONObject.parseObject((JSONObject.parseArray(JSONObject.parseArray(html).get(0).toString()).get(0)).toString()).get("item_value");

        map.put("name", name);
        map.put("email", email);
        map.put("avatar", avatar);

        return map;
    }


    /**
     * 置换传入的路径内的空格
     *
     * @param s
     * @return
     */
    public static String filterCommandLineSpecialChar(String s) {
        String regexp = "[\\pP\\pZ\\pS]+";
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String p = matcher.group();
            matcher.appendReplacement(sb, "\"" + p + "\"");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
