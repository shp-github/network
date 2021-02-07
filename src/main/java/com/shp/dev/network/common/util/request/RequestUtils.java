package com.shp.dev.network.common.util.request;

import com.shp.dev.network.common.util.string.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/12/10 12:09
 * @PackageName: com.tieta.ai.common.utils
 * @ProjectName: ai
 */
public class RequestUtils {


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 截取图片文件目录
     * @CreateTime: 2020/8/28 2:50
     * @param: str 图片全路径
     * @return: java.lang.String
     */
    public static String cutStr(String str) {
        try {
            return str.substring(0, str.lastIndexOf("/"));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 对参数进行处理把json封装到data中
     * @CreateTime: 2020/8/27 1:16
     * @param: image
     * @return: org.json.JSONObject
     */
    public static JSONObject fristHandleJSON(JSONObject image) {
        try {
            return new JSONObject().put("data", new Base64().encodeToString(image.toString().getBytes()));
        } catch (Exception e) {
            return null;
        }
    }

    public static JSONObject fristHandleJSON(net.sf.json.JSONObject image) {
        try {
            return new JSONObject().put("data", new Base64().encodeToString(image.toString().getBytes()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 对返回数据进行处理
     * @CreateTime: 2020/8/27 0:32
     * @param:
     * @return: void
     */
    public static net.sf.json.JSONObject handleJSON(String data) {
        try {
            if (data == null || data.equals("")) {
                return null;
            }
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(data);
            if (!jsonObject.isNullObject() && jsonObject != null && jsonObject.has("err_no")) {
                if (jsonObject.getInt("err_no") > 0) {
                    System.out.println(jsonObject);
                    return null;
                }
            }
            if (jsonObject.has("result") && jsonObject.get("result") != null && !jsonObject.get("result").equals("")) {
                String result = jsonObject.getString("result");
                BASE64Decoder decode = new BASE64Decoder();
                String json = new String(decode.decodeBuffer(result));
                String str = StringUtils.replaceBlank(json);
                return net.sf.json.JSONObject.fromObject(str);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "对返回数据进行处理");
            return null;
        }
        return null;
    }

}
