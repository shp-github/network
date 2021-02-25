package com.shp.dev.network.common.util.robot;

import com.shp.dev.network.common.util.Base64Utils;
import com.shp.dev.network.common.util.DateUtils;
import com.shp.dev.network.common.util.request.HttpRequestUrl;
import net.sf.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 模拟键盘鼠标操作
 * @CreateTime: 2020/10/1 13:26
 * @PackageName: com.shp.dev.network.common.util.robot
 * @ProjectName: network
 */

public class Main {

    private static Robot robot;


    public static void main(String[] args) {

        System.out.println();



    }





    public static void main3(String[] args) {

        System.out.println(File.separator);

        String outFIle="asdgag.jpg";
        System.out.println(outFIle);

        File file = new File(outFIle);

        outFIle=file.getPath();

        String substring = outFIle.substring(outFIle.indexOf(".")+1);
        System.out.println(substring);

    }

    public static void mai1n(String[] args) {


        String s = HttpRequestUrl.sendPost("https://ieye.hsttw.cn/cruiseTower/getRedisFlow", "channel=13110200581314000096");
        System.out.println(s);


        String base = Base64Utils.getBaseByFile("E:\\131171400713201000011608702071746.jpg");

        //String divStr = HttpRequestUrl.sendPost("http://10.8.240.23:10086/video_feed", "picture=" + base + "&name=" + UUID.randomUUID().toString() + "&date=" + new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()) + System.currentTimeMillis());
        long fristTime = System.currentTimeMillis();
        String divStr = HttpRequestUrl.sendPost("http://123.182.146.55:10086/video_feed", "picture=" + base + "&name=" + UUID.randomUUID().toString() + "&date=" + new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()) + System.currentTimeMillis()+"&image_read="+s);
        System.out.println(divStr);
        System.out.println(System.currentTimeMillis()-fristTime);
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(divStr);

        System.out.println(jsonObject);

        JSONArray aiJSONArray = jsonObject.getJSONArray("AI");
        for (int i = 0; i < aiJSONArray.size(); i++) {
            net.sf.json.JSONObject json = aiJSONArray.getJSONObject(i);
            JSONArray box_points = json.getJSONArray("box_points");
            for (Object box_point : box_points) {
                System.out.println(box_point);
            }
            System.out.println(json);
        }

    }



    public static void main2(String[] args) {

        UUID uuid = UUID.randomUUID();

        String message ="{\"token\":\"a0facb44-1796-4d98-8b43-ce63ae192ee8\",\"status\":\"send\"}";

        //a0facb44-1796-4d98-8b43-ce63ae192ee8
        //5485c6b9-26be-4296-8dcf-0788bdad8fcd

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token",uuid);
        jsonObject.put("status","accept");
        //jsonObject.put("status","send");
        //jsonObject.put("message","黄发，啥时候发工资啊？");

        System.out.println(jsonObject);
        System.out.println(message);


    }

    public static void main1(String[] args) throws InterruptedException {

        try {
            robot = new Robot();//创建Robot对象
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("单击");

        Thread.sleep(3000);

        robot.mousePress(InputEvent.BUTTON1_MASK);//按住鼠标左键

        long l = System.currentTimeMillis();
        //System.out.println(Instant.now().toEpochMilli()); // 精确到毫秒
        //System.out.println(Instant.now().getEpochSecond());;// 精确到秒

        Integer endTime = DateUtils.getSecond("21:05:00");
        Integer startTime;
        for (; ; ) {
            startTime = DateUtils.getSecond(DateUtils.format(new Date(), "HH:mm:ss"));
            if (startTime > endTime - 1) {
                robot.mouseRelease(InputEvent.BUTTON1_MASK);//释放鼠标左键
                System.out.println("释放鼠标");
                break;
            }
        }


    }
}