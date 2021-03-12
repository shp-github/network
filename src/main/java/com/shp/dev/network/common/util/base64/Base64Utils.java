package com.shp.dev.network.common.util.base64;

import com.shp.dev.network.common.util.request.HttpRequestUrl;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 操作base64工具类
 * @CreateTime: 2020/12/10 9:49
 * @PackageName: com.tieta.ai.common.utils
 * @ProjectName: ai
 */
public class Base64Utils {

    public static void main(String[] args) {


        String baseByFile = getBaseByFile("E:\\a.png");
        String s = HttpRequestUrl.sendPost("http://192.168.176.1:8081/common/broadCastInfo", "str=" + baseByFile);
        System.out.println(s);



    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 本地文件转base64
     * @CreateTime: 2020/12/10 11:06
     * @param: file
     * @return: java.lang.String
     */
    public static String getBaseByFile(String file) {
        return getBaseByFile(new File(file));
    }

    public static String getBaseByFile(File file) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        try {
            // 读取图片字节数组
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return Base64.encodeBase64String(data);
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 转换完成会自动换行 字符串会产生\n\r\t等字符 需要去除再转图片否则会失败
     * @CreateTime: 2020/12/10 11:51
     * @param: file
     * @return: java.lang.String
     */
    public static String getBaseByFileWrap(String file) {
        return getBaseByFileWrap(new File(file));
    }

    public static String getBaseByFileWrap(File file) {
        if (file == null) {
            return null;
        }
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            //对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(data);
            return encode;//返回Base64编码过的字节数组字符串
        } catch (IOException e) {
            return null;
        }
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO BufferedImage转base64字符串
     * @CreateTime: 2020/12/22 17:39
     * @param: bufferedImage
     * @return: java.lang.String
     */
    //import org.apache.axis.encoding.Base64;
    public static String getBaseByBufferedImage(BufferedImage bufferedImage) {
        try {
            //输出流
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", stream);
            return org.apache.axis.encoding.Base64.encode(stream.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 把在线图片转base64
     * @CreateTime: 2020/12/10 10:57
     * @param: url 在线图片地址
     * @return: java.lang.String
     */
    public static String getBaseByUrl(String url) {
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        try {
            // 创建URL
            URL u = new URL(url);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
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
    public static String writeFileByBase(String base64, String file) {
        return writeFileByBase(base64, new File(file));
    }

    public static String writeFileByBase(String base64, File file) {
        if (base64 == null || file == null) {
            System.out.println("图像数据为空或者文件输出地址为空");
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
            return file.getPath();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 根据base64获取宽高
     * @CreateTime: 2020/12/10 11:08
     * @param: base64
     * @return: int[]
     */
    public static int[] getWHByBase(String base64) {
        int i[] = new int[2];
        try {
            BufferedImage image = getBufferedImageBybase(base64);
            //获取宽高
            i[0] = image.getWidth();
            i[1] = image.getHeight();
            image.getGraphics().dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            i[0] = 0;
            i[1] = 0;
        }
        return i;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO base64转BufferedImage
     * @CreateTime: 2020/12/10 11:08
     * @param: base64
     * @return: int[]
     */
    public static BufferedImage getBufferedImageBybase(String base64) {
        try {
            byte[] strBase64 = new BASE64Decoder().decodeBuffer(base64);
            InputStream is = new ByteArrayInputStream(strBase64);
            return ImageIO.read(is);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO base64转InputStream
     * @CreateTime: 2020/12/10 11:08
     * @param: base64
     * @return: int[]
     */
    public static InputStream getInputStreamBybase(String base64) {
        try {
            byte[] strBase64 = new BASE64Decoder().decodeBuffer(base64);
            return new ByteArrayInputStream(strBase64);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 进行base解码
     * @CreateTime: 2020/12/10 11:11
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
     * @Description: TODO 进行base编码
     * @CreateTime: 2020/12/10 11:11
     * @param: bstr
     * @return: java.lang.String
     */
    public static String encode(byte[] bstr) {
        return new BASE64Encoder().encode(bstr);
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 截取base中的数据生成图片
     * @CreateTime: 2020/12/10 11:34
     * @param: base64
     * @param: outPath
     * @param: x
     * @param: y
     * @param: width
     * @param: height
     * @return: java.lang.String
     */
    public static String cutByBase64(String base64, File outPath, int x, int y, int width, int height) {
        try {
            InputStream inputStream = getInputStreamBybase(base64);
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = it.next();
            ImageInputStream iis = ImageIO.createImageInputStream(inputStream);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, "jpg", outPath);
            bi.flush();
            bi.getGraphics().dispose();
            return outPath.getPath();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO base转 byte数组
     * @CreateTime: 2020/12/10 12:12
     * @param: path
     * @return: byte[]
     */
    public static byte[] base2Bytes(String base) {
        byte[] data = null;
        try {
            InputStream inputStreamBybase = getInputStreamBybase(base);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = inputStreamBybase.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            inputStreamBybase.close();
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


}
