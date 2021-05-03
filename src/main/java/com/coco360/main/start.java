package com.coco360.main;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.coco360.pojo.OpenID;
import com.coco360.pojo.RespMsg;
import com.coco360.util.HttpClientUtil;
import com.coco360.util.LittleUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;


public class start {

    static String all_openid = null;
    static String Some_openid = null;
    static long read_openidTime = 1000 * 30;
    static long checkCoursewareTime = 3600000;
    static long classBackTime = 3600000;
    static long ty_qdTime = 1000 * 60;
    static String prefix_Url = "http://localhost:8080/";
    static String WeChart = "D:/Program Files (x86)/Tencent/WeChat/WeChat.exe";

    static {

        Properties p = new Properties();
        InputStream is = null;
        try {
            WeChart = LittleUtils.getWeChatPath("DisplayIcon");
            // 配置文件位于当前目录中的config目录下
            is = new BufferedInputStream(new FileInputStream("config/" + "config.properties"));
//            is=run.class.getClassLoader().getResourceAsStream("config.properties");
            p.load(is);
            read_openidTime = Long.parseLong(p.get("read_openidTime").toString());
            checkCoursewareTime = Long.parseLong(p.get("checkCoursewareTime").toString());
            classBackTime = Long.parseLong(p.get("classBackTime").toString());
            ty_qdTime = Long.parseLong(p.get("ty_qdTime").toString());
            prefix_Url = p.get("prefix_Url").toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        return format;
    }

    public static void main(String[] args) {
        read_OpenID_Runnable ror = new read_OpenID_Runnable();
        Thread read_openID_Thread = new Thread(ror);
        read_openID_Thread.start();

        ty_qd_Runnable qd = new ty_qd_Runnable();
        Thread qd_Thread = new Thread(qd);
        qd_Thread.start();

        checkCourseware_Runnable ccr = new checkCourseware_Runnable();
        Thread checkCourseware_Thread = new Thread(ccr);
        checkCourseware_Thread.start();

        classBack_Runnable cbr = new classBack_Runnable();
        Thread classBack_Thread = new Thread(cbr);
        classBack_Thread.start();


        System.out.println("请输入对应数字开启相应功能");
        System.out.println("0.打开菜单");
        System.out.println("1.自动签到");
        System.out.println("2.自动观看课件");
        System.out.println("3.自动提交课堂反馈");
        System.out.println("9.查看任务状态");

        while (true) {
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            if (i == 0) {
                System.out.println("请输入对应数字开启相应功能");
                System.out.println("0.打开菜单");
                System.out.println("1.开启签到");
                System.out.println("2.观看课件");
                System.out.println("3.自动提交课堂反馈");
                System.out.println("9.查看任务状态");
            }
            if (i == 1) {

                if (qd.qd_Bool == true) {
                    qd.qd_Bool = false;
                    System.out.println('[' + getDate() + ']' + "签到任务已关闭");
//                    关闭方法
                } else {
                    qd.qd_Bool = true;
                    System.out.println('[' + getDate() + ']' + "签到任务已开启");
                    qd_Thread.interrupt();
                }
            }
            if (i == 2) {
                if (ccr.checkCourseware_Bool == true) {
                    ccr.checkCourseware_Bool = false;
                    System.out.println('[' + getDate() + ']' + "观看课件任务已关闭");

                } else {
                    ccr.checkCourseware_Bool = true;
                    System.out.println('[' + getDate() + ']' + "观看课件任务已开启");
//                    开启方法
                    checkCourseware_Thread.interrupt();

                }
            }
            if (i == 3) {
                if (cbr.classBack_Bool == true) {
                    cbr.classBack_Bool = false;
                    System.out.println('[' + getDate() + ']' + "自动提交课堂反馈已关闭");
//                    关闭方法
//                    checkCourseware_Thread.interrupt();
                } else {
                    cbr.classBack_Bool = true;
                    System.out.println('[' + getDate() + ']' + "自动提交课堂反馈已开启");
//                    开启方法
                    classBack_Thread.interrupt();

                }
            }
            if (i == 9) {
                System.out.println("-----当前任务状态-----");
                System.out.println("自动签到任务:" + qd.qd_Bool);
                System.out.println("查看课件任务:" + ccr.checkCourseware_Bool);
                System.out.println("自动课堂反馈:" + cbr.classBack_Bool);
                System.out.println("-----当前任务状态-----");
            }
        }

    }

    static class ty_qd_Runnable implements Runnable {

        boolean qd_Bool = false;
        StringBuilder stringBuilder = new StringBuilder();

        @Override
        public void run() { //run方法，里面包含要执行的任务

            while (true) {
                while (qd_Bool) {
                    try {
                        stringBuilder.append(HttpClientUtil.doGet(prefix_Url + "sign?url=http://www.hntyxxh.com/wechat2-ssr/?openid=" + all_openid
                                , all_openid));

                        boolean b = LittleUtils.checkOpenid(stringBuilder);
                        if (b) {
                            Thread.sleep(read_openidTime);
                            continue;
                        } else {
                            System.out.println('[' + getDate() + ']' + stringBuilder.toString());
                            stringBuilder.delete(0, stringBuilder.length());
                            Thread.sleep(ty_qdTime);
                        }
                    } catch (Exception e) {


                    }
                }
                try {

                    Thread.sleep(1000 * 60 * 60 * 24 * 365);
                } catch (InterruptedException e) {
//                    e.printStackTrace();

                }


            }
        }
    }

    static class classBack_Runnable implements Runnable {

        boolean classBack_Bool = false;
        StringBuilder stringBuilder = new StringBuilder();

        @Override
        public void run() { //run方法，里面包含要执行的任务
            while (true) {
                while (classBack_Bool) {
                    try {
                        stringBuilder.append(HttpClientUtil.doGet(prefix_Url + "classBack?url=http://www.hntyxxh.com/wechat2-ssr/?openid=" + all_openid
                                , all_openid));
                        boolean b = LittleUtils.checkOpenid(stringBuilder);
                        if (b) {
                            Thread.sleep(classBackTime);
                            continue;
                        } else {
                            System.out.println('[' + getDate() + ']' + stringBuilder.toString());

                            Thread.sleep(classBackTime);
                            stringBuilder.delete(0, stringBuilder.length());
                        }
                    } catch (Exception e) {


                    }
                }
                try {

                    Thread.sleep(1000 * 60 * 60 * 24 * 365);
                } catch (InterruptedException e) {
//                    e.printStackTrace();

                }


            }
        }
    }

    static class checkCourseware_Runnable implements Runnable {
        boolean checkCourseware_Bool = false;
        StringBuilder stringBuilder = new StringBuilder();

        @Override
        public void run() {
            while (true) {
                while (checkCourseware_Bool) {
                    try {
                        stringBuilder.append(HttpClientUtil.doGet(prefix_Url + "watchCourseware?url=http://www.hntyxxh.com/wechat2-ssr/?openid=" + all_openid
                                , all_openid));
                        boolean b = LittleUtils.checkOpenid(stringBuilder);
                        if (b) {
                            Thread.sleep(checkCoursewareTime);
                            continue;
                        } else {

                            System.out.println('[' + getDate() + ']' + stringBuilder.toString());

                            Thread.sleep(checkCoursewareTime);
                            stringBuilder.delete(0, stringBuilder.length());
                        }
                    } catch (Exception e) {


                    }


                }
                try {
                    Thread.sleep(1000 * 60 * 60 * 24 * 365);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        }
    }

    static class read_OpenID_Runnable implements Runnable {

        @Override
        public void run() {
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                try {
                    stringBuilder.append(HttpClientUtil.doGet(prefix_Url + "getOpenid", null));
                    JSONObject jsonObject = JSONObject.parseObject(stringBuilder.toString());
                    String msg = (String) jsonObject.get("msg");

                    if ("success".equals(msg)) {
                        all_openid = (String) jsonObject.get("data");
                        if (all_openid != null) {
                            if (all_openid.equals(Some_openid)) {


                            } else {
                                System.out.println("[" + getDate() + "]openid 获取成功  " + all_openid);
                                Some_openid = all_openid;
                            }
                        }

                    } else {
                        System.out.println("[" + getDate() + "]openid 未获取成功  将启动自动点击事件！");
                        LittleUtils.updateOpenid();

                    }
                    Thread.sleep(read_openidTime);
                    stringBuilder.delete(0, stringBuilder.length());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }
    }
}