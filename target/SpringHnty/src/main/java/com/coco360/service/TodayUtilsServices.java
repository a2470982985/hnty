package com.coco360.service;

import com.alibaba.fastjson.JSONObject;
import com.coco360.pojo.DrawPicture;
import com.coco360.pojo.PictureInfo;
import com.coco360.pojo.RespMsg;
import com.coco360.util.HttpClientUtil;
import com.coco360.util.LittleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import static com.coco360.util.LittleUtils.editPicture;

@Service("todayUtilsServices")
public class TodayUtilsServices {

    @Value("${notice}")
    public String notice;

    @Value("${titleName}")
    public String title;


    public RespMsg getNotice(){
        return new RespMsg(200,title,notice);
    }

    public String createPicture(PictureInfo pictureInfo) throws Exception {
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
        String base64 = LittleUtils.ImageToBase64(image);
        return base64;

    }
}
