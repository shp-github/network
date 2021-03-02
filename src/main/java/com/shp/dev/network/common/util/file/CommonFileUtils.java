package com.shp.dev.network.common.util.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 通用文件操作工具类
 * @CreateTime: 2021/1/19 16:39
 * @PackageName: com.shp.dev.network.common.util
 * @ProjectName: network
 */

@Slf4j
public class CommonFileUtils {



    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 保存文件
     * @CreateTime: 2021/1/19 21:12
     * @param: object 源文件： 本地文件路径、 本地文件、 在线文件地址、 base64字符串
     * @param: fileName 文件名称
     * @param: frist 路径开头
     * @param: last 路径结尾
     * @return: java.lang.String  返回文件所在地址 默认会创建年/月/日作为中间的文件夹
     */
    public static String currencySaveFile(Object object, String fileName, String frist, String last) {
        return saveFile(object, createDirectory(frist, last) + fileName);
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 创建目录
     * @CreateTime: 2021/1/19 21:29
     * @param: frist
     * @param: last
     * @return: java.lang.String
     */
    public static String createDirectory(String frist, String last) {

        String path = "";
        if (noNull(frist)) {
            path += frist;
        }

        path += "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/";

        if (noNull(last)) {
            path += last + "/";
        }

        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return path;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 保存文件到本地
     * @CreateTime: 2021/1/19 18:05
     * @param: object
     * @param: outFile
     * @return: java.lang.String
     */
    public static String saveFile(Object object, String outFile) {
        try {
            //如果是输入是字符串
            if (object instanceof String) {
                String str = (String) object;
                File file = new File(str);
                //本地有此文件
                if (file.exists()) {
                    return writeByFile(file, outFile);
                } else {
                    //转base64
                    if (writeByURL(str, outFile) != null) {
                        return outFile;
                    } else {
                        //保存在线图片
                        return writeByBase64(str, outFile);
                    }
                }
            } else if (object instanceof File) {
                return writeByFile((File) object, outFile);
            } else if (object instanceof MultipartFile) {
                return writeFileByMultipartFile((MultipartFile) object, outFile);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        log.error("没有匹配保存文件的类型");
        return null;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 写文件到本地
     * @CreateTime: 2021/1/19 18:01
     * @param: multipartFile
     * @param: outFile
     * @return: java.lang.String
     */
    public static String writeFileByMultipartFile(MultipartFile multipartFile, String outFile) {
        try {
            if (isNull(multipartFile, outFile)) {
                log.error("参数为空或者文件不存在");
                return null;
            }
            InputStream is = multipartFile.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            OutputStream os = new FileOutputStream(outFile);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            is.close();
            return outFile;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 写文件到本地
     * @CreateTime: 2021/1/19 18:02
     * @param: base64
     * @param: outFile
     * @return: java.lang.String
     */
    public static String writeByBase64(String base64, String outFile) {
        try {
            if (isNull(base64, outFile)) {
                log.error("参数为空或者文件不存在");
                return null;
            }
            BASE64Decoder decoder = new BASE64Decoder();
            OutputStream out = new FileOutputStream(outFile);
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
            out.close();
            return outFile;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 写文件到本地
     * @CreateTime: 2021/1/19 18:02
     * @param: inFile
     * @param: outFile
     * @return: java.lang.String
     */
    public static String writeByURL(String inFile, String outFile) {
        try {
            if (isNull(inFile, outFile)) {
                log.error("参数为空或者文件不存在");
                return null;
            }
            URL url = new URL(inFile);
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            FileOutputStream os = new FileOutputStream(outFile);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            is.close();
            return outFile;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 写文件到本地
     * @CreateTime: 2021/1/19 18:02
     * @param: filePath
     * @param: outFile
     * @return: java.lang.String
     */
    public static String writeByFilePath(String filePath, String outFile) {
        try {
            if (isNull(filePath, outFile)) {
                log.error("参数为空或者文件不存在");
                return null;
            }
            FileInputStream fis = new FileInputStream(filePath);
            FileOutputStream fos = new FileOutputStream(outFile);
            int len;
            byte[] b = new byte[1024];
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.close();
            fis.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return outFile;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 写文件到本地
     * @CreateTime: 2021/1/19 18:02
     * @param: file
     * @param: outFile
     * @return: java.lang.String
     */
    public static String writeByFile(File file, String outFile) {
        try {
            if (isNull(file, outFile)) {
                log.error("参数为空或者文件不存在");
                return null;
            }
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(outFile);
            int len;
            byte[] b = new byte[1024];
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.close();
            fis.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return outFile;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 如果有一个为空则返回true
     * @CreateTime: 2021/1/19 16:47
     * @param: files
     * @return: boolean
     */
    public static boolean isNull(Object... objects) {
        if (objects == null || objects.equals("") || objects[0] == null) {
            return true;
        }
        for (Object object : objects) {
            String name = object.getClass().getName();
            log.info("比较的类型为{}", name);
            if (name.equalsIgnoreCase("java.io.File")) {
                File file = (File) object;
                return file == null || file.equals("") || !file.isFile();
            }
            if (name.equalsIgnoreCase("org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile")) {
                MultipartFile multipartFile = (MultipartFile) object;
                return multipartFile == null || multipartFile.equals("") || multipartFile.isEmpty();
            }
            return object == null || object.equals("");
        }
        return false;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 都不为空则返回true
     * @CreateTime: 2021/1/19 16:47
     * @param: files
     * @return: boolean
     */
    public static boolean noNull(Object... objects) {

        if (objects == null || objects.equals("") || objects[0] == null) {
            return false;
        }

        for (Object object : objects) {
            String name = object.getClass().getName();
            log.info("比较的类型为{}", name);
            if (name.equalsIgnoreCase("java.io.File")) {
                File file = (File) object;
                return file != null && file.equals("") && !file.isFile();
            }
            if (name.equalsIgnoreCase("org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile")) {
                MultipartFile multipartFile = (MultipartFile) object;
                return multipartFile != null || !multipartFile.equals("") || !multipartFile.isEmpty();
            }
            return object != null || !object.equals("");
        }
        return false;
    }


}
