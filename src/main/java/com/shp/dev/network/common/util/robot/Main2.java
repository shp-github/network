package com.shp.dev.network.common.util.robot;

import com.shp.dev.network.common.util.Base64Utils;
import com.shp.dev.network.common.util.request.RequestUtils;
import net.sf.json.JSONObject;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/12/30 1:14
 * @PackageName: com.shp.dev.network.common.util.robot
 * @ProjectName: network
 */
public class Main2 {
    public static void main(String[] args) {

        //String s = HttpRequestUrl.sendPost("https://ieye.hsttw.cn/cruiseTower/getRedisFlow", "channel=13110200581314000096");
        //System.out.println(s);
        //
        //String base = Base64Utils.getBaseByFile("E:\\131171400713201000011608702071746.jpg");
        //long fristTime = System.currentTimeMillis();
        //String divStr = HttpRequestUrl.sendPost("http://123.182.146.55:10086/video_feed", "picture=" + base + "&name=" + UUID.randomUUID().toString() + "&date=" + new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()) + System.currentTimeMillis() + "&image_read=" + s);
        //System.out.println(divStr);
        //System.out.println(System.currentTimeMillis() - fristTime);


        //for (int i = 0; i < 10; i++) {
        //    String base = Base64Utils.getBaseByFile("E:\\131171400713201000011608702071746.jpg");
        //    long fristTime = System.currentTimeMillis();
        //    String fireStr = HttpRequestUrl.sendPost("http://10.8.240.22:7080/index", "picture=" + base + "&name=" + UUID.randomUUID().toString() + "&date=" + new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()) + System.currentTimeMillis());
        //    System.out.println(System.currentTimeMillis() - fristTime);
        //}


        String base = Base64Utils.getBaseByFile("E:\\131171400713201000011608702071746.jpg");
        JSONObject image = new JSONObject();
        image.put("image", base);
        String jsonData = com.shp.dev.network.common.util.request.HttpRequestUrl.getJsonData(RequestUtils.fristHandleJSON(image), "http://10.8.240.10:8129/GeneralClassifyService/classify");
        System.out.println(jsonData);


    }
}
