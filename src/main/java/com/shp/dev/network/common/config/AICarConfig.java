package com.shp.dev.network.common.config;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 车辆分析配置类
 * @CreateTime: 2020/8/15 10:57
 * @PackageName: com.vimochina.vimo.util.ai
 * @ProjectName: wisdomeyeapi0114
 */
public class AICarConfig {

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
    public static AipImageClassify initAICar(){
        // 初始化一个AipBodyAnalysis
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

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
     * @Description: TODO 识别车辆  当前只支持单主体识别，若图片中有多个车辆，则识别目标最大的车辆。
     * @CreateTime: 2020/8/16 14:35
     * @param: imagePath
     * @param: options
     * @return: org.json.JSONObject
     */
    public static JSONObject carDetect(String imagePath, HashMap<String, String> options){
        return initAICar().carDetect(imagePath, options);
    }
    public static JSONObject carDetect(byte[] imagePath, HashMap<String, String> options){
        return initAICar().carDetect(imagePath, options);
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 通用物体识别
     * @CreateTime: 2020/8/19 0:54
     * @param: imagePath
     * @param: options
     * @return: org.json.JSONObject
     */
    public static JSONObject advancedGeneral(byte[] imagePath, HashMap<String, String> options){
        return initAICar().advancedGeneral(imagePath, options);
    }
    public static JSONObject advancedGeneral(String imagePath, HashMap<String, String> options){
        return initAICar().advancedGeneral(imagePath, options);
    }


}
