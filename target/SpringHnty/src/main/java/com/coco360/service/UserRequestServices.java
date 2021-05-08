package com.coco360.service;

import com.alibaba.fastjson.JSONObject;
import com.coco360.pojo.DrawPicture;
import com.coco360.pojo.OpenID;
import com.coco360.pojo.PictureInfo;
import com.coco360.pojo.RespMsg;
import com.coco360.requests.UserRequest;
import com.coco360.util.HttpClientUtil;
import com.coco360.util.LittleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.coco360.util.LittleUtils.editPicture;

@Service("userRequestServices")
public class UserRequestServices {
    @Autowired
    OpenID openID;

    @Autowired
    LittleUtils littleUtils;

    @Autowired
    UserRequest userRequest;
    @Value("${notice}")
    public String notice;

    @Value("${titleName}")
    public String title;


    public RespMsg getNotice(){
        return new RespMsg(200,title,notice);
    }
    /**
     * 签到Service
     *
     * @param url
     * @return RespMsg实体 ToString内容  签到状态
     * @throws Exception
     */
    public RespMsg sign(String url) {
        String openid = null;
        if (LittleUtils.checkString(url)) {
            openid = url;
        } else {
            openid = LittleUtils.getSubString(url, "openid=", "&");
        }

        if (openid == null) {
            LittleUtils.setRespMsg(403, "Error", "openid 已过期 或 openid输入错误");
        }
        String response = null;
        try {
            response = userRequest.checkSign(openid);
        } catch (Exception e) {
            return LittleUtils.setRespMsg(0, "Error", "河南统院服务器拥挤，暂未连接成功");
        }
        if (response == null) {
//            LittleUtils.updateOpenid();;
            return LittleUtils.setRespMsg(403, "Error", "openid 已过期 或 openid输入错误");
        } else if ("{}".contains(response)) {
            return LittleUtils.setRespMsg(200, "Success", "暂无开启的签到");
        }
        String s = null;
        try {
            s = userRequest.Sign_in(response, openid);
        } catch (Exception e) {

            return LittleUtils.setRespMsg(0, "Error", "河南统院服务器拥挤，暂未连接成功");
        }

        if (s == null) {
//            LittleUtils.updateOpenid();;
            return LittleUtils.setRespMsg(403, "Error", "openid 已过期 或 openid输入错误");
        } else if (s.contains("errorCode")) {
            return LittleUtils.setRespMsg(0, "Error", s);
        }
        System.out.println(s);
        return LittleUtils.setRespMsg(200, "Success", s);
    }

    public RespMsg watchCourseware(String url) {
        String openid = littleUtils.getSubString(url, "openid=", "&");
        if (openid == null) {
            littleUtils.setRespMsg(403, "Error", "openid 已过期 或 openid输入错误");
        }
        List<Map<String, Object>> list = null;
        try {
            list = userRequest.checkCourseware(openid);
        } catch (Exception e) {
            return littleUtils.setRespMsg(0, "Error", "河南统院服务器拥挤，暂未连接成功");
        }

        if (list == null) {
//            LittleUtils.updateOpenid();
            return littleUtils.setRespMsg(403, "Error", "openid 已过期 或 openid输入错误");
        } else if (list.size() == 0) {
            return littleUtils.setRespMsg(200, "Success", "暂无需要观看的课件");
        }


        List<String> str = null;
        try {
            str = userRequest.ViewCourseware(openid, list);
        } catch (Exception e) {
            return littleUtils.setRespMsg(0, "Error", "河南统院服务器拥挤，暂未连接成功");
        }
        if (str == null) {
            return littleUtils.setRespMsg(403, "Error", "openid 已过期 或 openid输入错误");
        } else if (list.size() == 0) {
            return littleUtils.setRespMsg(200, "Success", "暂无需要观看的课件");
        }
        return littleUtils.setRespMsg(200, "Success", str.toString());
    }

    public RespMsg classBack(String url) {
        String openid = littleUtils.getSubString(url, "openid=", "&");
        if (openid == null) {
            littleUtils.setRespMsg(403, "Error", "openid 已过期 或 openid输入错误");
        }
        List<Map<String, Object>> list = null;
        try {
            list = userRequest.checkClass_feedback(openid);
        } catch (Exception e) {
            return littleUtils.setRespMsg(0, "Error", "河南统院服务器拥挤，暂未连接成功");
        }

        if (list == null) {
//            littleUtils.updateOpenid();
            return littleUtils.setRespMsg(403, "Error", "openid 已过期 或 openid输入错误");
        } else if (list.size() == 0) {
            return littleUtils.setRespMsg(200, "Success", "暂无需要提交的课堂反馈");
        }


        List<String> str = userRequest.class_feedback(openid, list);
        if (str == null) {
//            LittleUtils.updateOpenid();
            return littleUtils.setRespMsg(403, "Error", "openid 已过期 或 openid输入错误");
        }
        System.out.println(str);
        if (list.size() == 0) {
            return littleUtils.setRespMsg(200, "Success", "暂无需要提交的课堂反馈");
        }
        return littleUtils.setRespMsg(200, "Success", str.toString());
    }

    public  RespMsg expandLearning() {
        return null;
    }

    public  RespMsg updataOpenid(String url) {
        String openid = littleUtils.getSubString(url, "openid=", "&");
        RespMsg response = userRequest.checkOpenid(openid);
        return response;

    }




    public  RespMsg getOpenid() {
        String id = openID.getOpenid();
        if (id==null || id==""){
            return new RespMsg(400, "error", "");
        }else {
            return new RespMsg(200 ,"success",id);
        }

    }
}
