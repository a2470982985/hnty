package com.coco360.controller;

import com.coco360.pojo.PictureInfo;
import com.coco360.pojo.RespMsg;
import com.coco360.service.UserRequestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Controller
public class UserSignController {

    @Autowired
    UserRequestServices userRequestServices;

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


    @GetMapping("/getPicture")
    public void getPicture(HttpServletResponse response, PictureInfo pictureInfo) throws Exception {
        BufferedImage image = userRequestServices.createPicture(pictureInfo);
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image,"png",os);
    }
}
