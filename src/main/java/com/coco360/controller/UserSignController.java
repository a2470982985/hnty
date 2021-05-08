package com.coco360.controller;

import com.coco360.pojo.PictureInfo;
import com.coco360.pojo.RespMsg;
import com.coco360.service.TodayUtilsServices;
import com.coco360.service.UserRequestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.Properties;

@Controller
public class UserSignController {

    @Autowired
    UserRequestServices userRequestServices;

    @Autowired
    TodayUtilsServices todayUtilsServices;


    @GetMapping("/sign")
    @ResponseBody
    public RespMsg sign(@RequestParam String url){
        RespMsg msg = userRequestServices.sign(url);
        return msg;
    }

    @GetMapping("/classBack")
    @ResponseBody
    public RespMsg classBack(@RequestParam String url) {
        return userRequestServices.classBack(url);
    }

    @GetMapping("/watchCourseware")
    @ResponseBody
    public RespMsg watchCourseware(@RequestParam String url) {
        return userRequestServices.watchCourseware(url);
    }

    @GetMapping("/updateOpenid")
    @ResponseBody
    public RespMsg updataOpenid(@RequestParam String url) {
        return userRequestServices.updataOpenid(url);
    }

    @GetMapping("/getOpenid")
    @ResponseBody
    public RespMsg getOpenid() {
        return userRequestServices.getOpenid();
    }


    @PostMapping("/getPicture")
    @ResponseBody
    public RespMsg getPicture(HttpSession session, PictureInfo pictureInfo) throws Exception {
        String base64 = todayUtilsServices.createPicture(pictureInfo);
        session.setAttribute("base64",base64);
        session.setAttribute("username",pictureInfo.getOwnName());
        return new RespMsg(200,"success",base64);
    }

    @RequestMapping(value = "/notice")
    @ResponseBody
    public RespMsg getNotice(){
        return userRequestServices.getNotice();
    }


}
