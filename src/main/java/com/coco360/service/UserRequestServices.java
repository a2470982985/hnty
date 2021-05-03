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
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
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


    public  BufferedImage createPicture(PictureInfo pictureInfo) throws Exception {
        Date date = new Date();
        String format = new SimpleDateFormat("当前时间:yyyy-M-dd HH:mm:ss").format(date);
        URL resource = LittleUtils.class.getClassLoader().getResource("img/today.png");
        ArrayList infoLists = new ArrayList<>();
        infoLists.add(new DrawPicture(225, 1020, pictureInfo.getBeginTime().replace("T", " ").replace(new SimpleDateFormat("yyyy-").format(new Date()), ""), 30, new Color(0, 0, 0)).toArray());
        infoLists.add(new DrawPicture(225, 1085, pictureInfo.getEndTime().replace("T", " ").replace(new SimpleDateFormat("yyyy-").format(new Date()), ""), 30, new Color(0, 0, 0)).toArray());
        infoLists.add(new DrawPicture(255, 1215, pictureInfo.getPhoneNumber(), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(225, 1280, pictureInfo.getReason(), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(225, 1345, pictureInfo.getPosition(), 30, new Color(30, 144, 255)).toArray());
        infoLists.add(new DrawPicture(240, 1475, pictureInfo.getDestination(), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(140, 1890, pictureInfo.getOwnName(), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(863, 1890, pictureInfo.getInitiateAnApplication().replace("T", " ").replace(new SimpleDateFormat("yyyy-").format(new Date()), ""), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(365, 1995, pictureInfo.getCounselorSName(), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(863, 1995, pictureInfo.getReceiveApplication().replace("T", " ").replace(new SimpleDateFormat("yyyy-").format(new Date()), ""), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(315, 640, format, 30, new Color(255, 255, 255)).toArray());
        infoLists.add(new DrawPicture(805, 1045, pictureInfo.getDuration(), 35, new Color(59, 137, 235)).toArray());

        BufferedImage image = editPicture(resource, infoLists);
        String path = (LittleUtils.class.getResource("/img").getPath() + "/outPicture.png").replaceAll("%20", " ");
        File file = new File(path);
        ImageIO.write(image, "png", file);
//        HttpClientUtil.doGet("http://pushplus.hxtrip.com/send?token=dcbe081737ca454cb3ff0d6eae1cf145&title="+"今日校园"+"&content="+pictureInfo.toString()+"&template=html",null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", "dcbe081737ca454cb3ff0d6eae1cf145");
        jsonObject.put("title", "今日校园");
        jsonObject.put("content", pictureInfo.toString());
        jsonObject.put("template", "html");
        HttpClientUtil.sendJson("http://pushplus.hxtrip.com/send", jsonObject, "utf-8", null);
        return image;
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
