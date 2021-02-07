package com.shp.dev.network.common.config;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 人体分析配置类
 * @CreateTime: 2020/8/15 10:57
 * @PackageName: com.vimochina.vimo.util.ai
 * @ProjectName: wisdomeyeapi0114
 */
public class AIPersionConfig {

    //设置APPID/AK/SK
    public static final String APP_ID = " ";
    public static final String API_KEY = " ";
    public static final String SECRET_KEY = " ";

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 初始化
     * @CreateTime: 2020/8/15 15:02
     * @param:
     * @return: com.baidu.aip.bodyanalysis.AipBodyAnalysis
     */

    public static AipBodyAnalysis initAIPersion(){
        // 初始化一个AipBodyAnalysis
        AipBodyAnalysis client = new AipBodyAnalysis(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        return client;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 人体关键点识别
     * @CreateTime: 2020/8/15 15:02
     * @param: imagePath
     * @param: options
     * @return: org.json.JSONObject
     */
    public static JSONObject bodyAnalysis(String imagePath, HashMap<String, String> options){
        return initAIPersion().bodyAnalysis(imagePath, options);
    }
    public static JSONObject bodyAnalysis(byte[] imagePath, HashMap<String, String> options){
        return initAIPersion().bodyAnalysis(imagePath, options);
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 人体检测与属性识别
     * @CreateTime: 2020/8/15 15:58
     * @param: imagePath 
     * @param: options
     * @return: org.json.JSONObject
     */
    public static JSONObject bodyAttr(String imagePath, HashMap<String, String> options){
        return initAIPersion().bodyAttr(imagePath, options);
    }
    public static JSONObject bodyAttr(byte[] imagePath, HashMap<String, String> options){
        return initAIPersion().bodyAttr(imagePath, options);
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 人流量统计
     * @CreateTime: 2020/8/15 17:00
     * @param: imagePath
     * @param: options
     * @return: org.json.JSONObject
     */
    public static JSONObject bodyNum(String imagePath, HashMap<String, String> options){
        return initAIPersion().bodyNum(imagePath, options);
    }
    public static JSONObject bodyNum(byte[] imagePath, HashMap<String, String> options){
        return initAIPersion().bodyNum(imagePath, options);
    }

}
