package com.shp.dev.network.common.util.base64;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 转Bash64
 * 本地图片转base64后要调用replaceBlank()方法去掉里面的\r\n否则转换有可能会失败
 * @CreateTime: 2020/8/18 15:15
 * @PackageName: com.vimochina.vimo.util.file
 * @ProjectName: wisdomeyeapi0114
 */
public class ToBash64 {

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
                String str = ToBash64.replaceBlank(json);
                return net.sf.json.JSONObject.fromObject(str);
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @CreateTime: 2020/8/26 21:29
     * @param: imgFile
     * @return: java.lang.String
     */
    public static String GetImageStr(String imgFile) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        if (imgFile == null) {
            return null;
        }
        InputStream in;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            //对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(data);//返回Base64编码过的字节数组字符串
        } catch (IOException e) {
            return null;
        }
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO java去除字符串中的空格、回车、换行符、制表符
     * @CreateTime: 2020/8/26 21:29
     * @param: str
     * @return: java.lang.String
     */
    public static String replaceBlank(String str) {
        String dest = "";
        try {
            if (str != null) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(str);
                dest = m.replaceAll("");
                return dest;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 解码base64为string
     * @CreateTime: 2020/9/6 13:07
     * @param: str
     * @return: byte[]
     */
    public static byte[] decode(String str) {
        byte[] bt = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            return null;
        }
        return bt;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 进行base64编码
     * @CreateTime: 2020/9/6 13:06
     * @param: bstr
     * @return: java.lang.String
     */
    public static String encode(byte[] bstr) {
        return new BASE64Encoder().encode(bstr);
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 本地图片转 byte数组
     * @CreateTime: 2020/8/18 21:07
     * @param: path
     * @return: byte[]
     */
    public static byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            return null;
        } catch (IOException ex1) {
            return null;
        }
        return data;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 把在线图片转base64
     * @CreateTime: 2020/8/18 21:08
     * @param: imgURL
     * @return: java.lang.String
     */
    public static String imgBase64(String imgURL) {
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        try {
            // 创建URL
            URL url = new URL(imgURL);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "fail";//连接失败/链接失效/图片不存在
            }
            InputStream inStream = conn.getInputStream();
            int len = -1;
            while ((len = inStream.read(data)) != -1) {
                outPut.write(data, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            return null;
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outPut.toByteArray());
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO  base64字符串转化成图片
     * @CreateTime: 2020/8/18 21:09
     * @param: imgData   图片base64字符编码
     * @param: imgFilePath 存放到本地路径
     * @return: boolean
     */
    public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }

}
