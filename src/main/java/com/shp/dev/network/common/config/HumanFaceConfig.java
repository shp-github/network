package com.shp.dev.network.common.config;

import com.baidu.aip.face.AipFace;
import com.shp.dev.network.common.util.base64.Base64Utils;
import com.shp.dev.network.common.util.image.CutImgUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/11/25 18:12
 * @PackageName: com.tieta.ai.common.conf
 * @ProjectName: ai
 */
public class HumanFaceConfig {


    //设置APPID/AK/SK
    public static final String APP_ID = "23042104";
    public static final String API_KEY = "EAhIKxFaeoq84uQKwLykaLfl";
    public static final String SECRET_KEY = "e70DWoKQDD1VhT3BeE0RyLolV7Q1TCN0";

    // 初始化一个AipFace
    static AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

    static {
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(2000);
    }

    public static AipFace initHumanFace() {
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(2000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        return client;
    }


    public static void main1(String[] args) {

        File filePath = new File("E:\\fuck");
        File[] files = filePath.listFiles();
        for (int j = 0; j < files.length; j++) {
            String file = files[j].getPath();
            // 传入可选参数调用接口
            HashMap<String, String> optionsTest = new HashMap<String, String>();
            optionsTest.put("max_face_num", "3");
            optionsTest.put("match_threshold", "70");
            optionsTest.put("quality_control", "NORMAL");
            optionsTest.put("liveness_control", "LOW");
            optionsTest.put("user_id", "233451");
            optionsTest.put("max_user_num", "3");
            String imageType = "BASE64";

            // 人脸搜索
            JSONObject resTest = detect(Base64Utils.getBaseByFile(file), imageType, optionsTest);
            System.out.println(resTest.toString(2));
            JSONObject resultJSONObject = resTest.getJSONObject("result");
            JSONArray resultJSONArray = resultJSONObject.getJSONArray("face_list");
            for (int i = 0; i < resultJSONArray.length(); i++) {
                JSONObject location = resultJSONArray.getJSONObject(i).getJSONObject("location");
                String cutTest = CutImgUtils.cut(file, "e:/out/" + j + ".jpg", location.getInt("left"), location.getInt("top"), location.getInt("width"), location.getInt("height"));
                // 传入可选参数调用接口
                HashMap<String, String> optionsSearch = new HashMap<String, String>();
                optionsSearch.put("max_face_num", "3");
                optionsSearch.put("match_threshold", "70");
                optionsSearch.put("quality_control", "NORMAL");
                optionsSearch.put("liveness_control", "LOW");
                optionsSearch.put("max_user_num", "3");
                String groupIdList = "c1";
                String cutSearch = Base64Utils.getBaseByFile(cutTest);
                // 人脸搜索
                JSONObject res = search(cutSearch, imageType, groupIdList, optionsSearch);
                System.out.println(res.toString(2));
            }
        }
    }


    //人脸搜索
    public static JSONObject search(String image, String imageType, String groupIdList, HashMap<String, String> options) {
        return client.search(image, imageType, groupIdList, options);
    }

    //人脸检测
    public static JSONObject detect(String image, String imageType, HashMap<String, String> options) {
        return client.detect(image, imageType, options);
    }

    //添加人脸库
    public static JSONObject addUser(String image, String imageType, String groupId, String userId, HashMap<String, String> options) {
        return client.addUser(image, imageType, groupId, userId, options);
    }

    public static void main(String[] args) {

        HashMap<String, String> optionsSearch = new HashMap<String, String>();
        optionsSearch.put("max_face_num", "3");
        optionsSearch.put("match_threshold", "70");
        optionsSearch.put("quality_control", "NORMAL");
        optionsSearch.put("liveness_control", "LOW");
        optionsSearch.put("max_user_num", "3");
        String groupIdList = "c1";
        String cutSearch = Base64Utils.getBaseByFile("e:/130900000013200000271606446100157.jpg");
        // 人脸搜索
        JSONObject res = search(cutSearch, "BASE64", groupIdList, optionsSearch);
        System.out.println(res.toString(2));

    }

    public static void main2(String[] args) {

        // 传入可选参数调用接口
        HashMap<String, String> optionsTest = new HashMap<String, String>();
        optionsTest.put("max_face_num", "3");
        optionsTest.put("match_threshold", "70");
        optionsTest.put("quality_control", "NORMAL");
        optionsTest.put("liveness_control", "LOW");
        optionsTest.put("user_id", "233451");
        optionsTest.put("max_user_num", "3");
        String imageType = "BASE64";
        // 人脸搜索
        JSONObject resTest = detect(Base64Utils.getBaseByFile("e:/130900000013200000271606446100157.jpg"), imageType, optionsTest);
        System.out.println(resTest.toString(2));


    }

}
