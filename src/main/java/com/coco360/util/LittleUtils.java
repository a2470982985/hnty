package com.coco360.util;

import com.alibaba.fastjson.JSONObject;
import com.coco360.exception.UnknownException;
import com.coco360.pojo.RespMsg;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;

import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.coco360.requests.GetInformationRequest.filterCommandLineSpecialChar;
@Component
public class LittleUtils {
    /**
     * 验证传入字符串是否为数字 + 字母 格式 且字符串长度为 32
     *
     * @param text
     * @return
     */
    public static boolean checkString(String text) {
        if (text.length() != 32) {
            return false;
        }
        Pattern p = Pattern.compile("[0-9a-zA-Z]{1,}");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    /**
     * 取两个文本之间的文本值
     *
     * @param text  源文本 比如：欲取全文本为 12345
     * @param left  文本前面
     * @param right 后面文本
     * @return 返回 String
     */
    public static String getSubString(String text, String left, String right) {

        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);

        if (checkString(result)) {
            return result;
        }
        return null;
    }


    /**
     * 设置  RespMsg实体 内容
     *
     * @param i 错误代码
     * @return 0:拥挤
     * 403：openid过期
     * 200：暂无开启签到
     */
    public static RespMsg setRespMsg(int i, String msg, String data) {
        return new RespMsg(i, msg, data);

    }

    /**
     * Robot打开微信 并点击微信事件
     * 更新Openid  防止openid过期
     *
     * @return
     */
    public static void updateOpenid() throws InterruptedException, IOException, UnknownException {
        Robot robot = null;
        String s = filterCommandLineSpecialChar(LittleUtils.getWeChatPath().get("DisplayIcon").replace("\\", "/"));
        try {
            Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k start " + s); // 通过cmd窗口执行命令
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
            try {
//                SendMail.sendEmail("2470982985@qq.com", "Server error", "Server error\n" + e.getStackTrace().toString());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        Thread.sleep(1000);
        int i = 10;
        while (i-- > 0) {
            robot.mouseMove(637, 639);
        }
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        i = 10;
        while (i-- > 0) {
            robot.mouseMove(744, 645);
        }
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        i = 10;
        while (i-- > 0) {
            robot.mouseMove(1100, 565);
        }
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    /**
     * 设置签到提交提交包
     *
     * @param html checkSign函数的返回值
     * @param lat  纬度
     * @param lon  经度
     * @return 签到提交Data值
     */
    public static String setSignData(String html, String lat, String lon) {
        JSONObject jsonObject = JSONObject.parseObject(html);
        Object courseId = jsonObject.get("courseId");
        Object signId = jsonObject.get("signId");
        if ((int) jsonObject.get("isGPS") == 1) {
            return "{\"courseId\":" + courseId + ",\"signId\":" + signId + ",\"lat\":\"34.80022\",\"lon\":\"113.680275\"}";
        } else if ((int) jsonObject.get("isQR") == 1) {
            return "{\"courseId\":" + courseId + ",\"signId\":" + signId + ",\"lat\":\"34.80022\",\"lon\":\"113.680275\"}";
        } else {
            return "{\"courseId\":" + courseId + ",\"signId\":" + signId + "}";
        }
    }

    /**
     * 通过注册表获取WeChat路径
     *
     * @return
     * @throws UnknownException 未找到 WeChar路径异常
     * @throws IOException
     */
    public static Map<String, String> getWeChatPath() throws UnknownException, IOException {
        Map<String, String> map = new HashMap<>();
        int i = 0;
        Process ps = null;
        ps = Runtime.getRuntime().exec("reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\WeChat");
        ps.getOutputStream().close();
        InputStreamReader isr = new InputStreamReader(ps.getInputStream(), "gb2312");
        String line;
        String[] list;
        BufferedReader buffer = new BufferedReader(isr);

        while ((line = buffer.readLine()) != null) {
            i++;
//                System.out.println(line);
            if (line.contains("REG_SZ")) {
                String[] split = line.split("REG_SZ");
                map.put(split[0].trim(), split[1].trim().replace("\"", ""));

            }
        }
        if (i == 0) {
            throw new UnknownException("软件未找到Wechat安装路径!");
        }
        return map;

    }
    public static String getWeChatPath(String param) throws UnknownException, IOException {
        Map<String, String> map = new HashMap<>();
        int i = 0;
        Process ps = null;
        ps = Runtime.getRuntime().exec("reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\WeChat");
        ps.getOutputStream().close();
        InputStreamReader isr = new InputStreamReader(ps.getInputStream(), "gb2312");
        String line;
        String[] list;
        BufferedReader buffer = new BufferedReader(isr);

        while ((line = buffer.readLine()) != null) {
            i++;
//                System.out.println(line);
            if (line.contains("REG_SZ")) {
                String[] split = line.split("REG_SZ");
                map.put(split[0].trim(), split[1].trim().replace("\"", ""));

            }
        }
        if (i == 0) {
            throw new UnknownException("软件未找到Wechat安装路径!");
        }
        return map.get(param);

    }

    /**
     * 编辑今日校园图片  根据传入信息生成图片
     *
     * @param path      图片路径
     * @param x         文本的x坐标
     * @param y         文本的y坐标
     * @param text      文本
     * @param textSize  文本文字大小
     * @param textColor 文本文字颜色
     * @return 添加文字后，生成的图片
     */
    public static Image drawString(URL path, int x, int y, String text, int textSize, Color textColor) throws IOException, URISyntaxException, FontFormatException {

        File picturePath = new File(path.toURI());
        Image img = ImageIO.read(picturePath);

        BufferedImage image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), null);
        InputStream is = LittleUtils.class.getClass().getResourceAsStream("myyh.ttf");

        Font font = new Font("微软雅黑", Font.PLAIN, textSize);
        g.setColor(textColor);
        g.setFont(font);
        g.drawString(text, x, y);
        g.dispose();
        return image;
    }
    public static BufferedImage editPicture(URL path, ArrayList<ArrayList> list) throws IOException, URISyntaxException {
        File picturePath = new File(path.toURI());
        Image img = ImageIO.read(picturePath);
        BufferedImage image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), null);
        File file= new File(LittleUtils.class.getClassLoader().getResource("img/2.png").toURI());
        g.drawImage(ImageIO.read(file), 291, 600, 460, 55, null);
        for (ArrayList info : list) {
            Font font = new Font("微软雅黑", Font.PLAIN, (Integer) info.get(3));
            g.setColor((Color) info.get(4));
            g.setFont(font);
            g.drawString((String) info.get(2), (Integer) info.get(0), (Integer) info.get(1));
        }


        g.dispose();
        return image;
    }


    /**
     * 根据访问tomcat 返回值 决定是否需要开始点击事件
     * @param stringBuilder
     * @return  true 为过期 false 未过期
     * @throws UnknownException
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean checkOpenid(StringBuilder stringBuilder) throws UnknownException, IOException, InterruptedException {
        JSONObject jsonObject = JSONObject.parseObject(stringBuilder.toString());
        String msg = (String) jsonObject.get("msg");
        boolean b = !"success".equalsIgnoreCase(msg);
        if (b){
            LittleUtils.updateOpenid();
        }
        return b;
    }
}
