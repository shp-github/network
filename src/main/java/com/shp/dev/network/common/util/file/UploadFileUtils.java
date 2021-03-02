package com.shp.dev.network.common.util.file;

import com.shp.dev.network.common.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 文件上传工具类
 * @CreateTime: 2020/8/17 23:57
 * @PackageName: com.vimochina.vimo.util.file
 * @ProjectName: wisdomeyeapi0114
 */
public class UploadFileUtils {


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 创建目录
     * @CreateTime: 2020/8/18 0:34
     * @param: frist 前缀
     * @param: last 后缀
     * @return: java.lang.String
     */
    public static String currencyCreateDirectory(String frist, String last) {
        //最终文件的目录
        String path = frist + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + last + "/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return path;
    }

    public static boolean currencyCreateDirectoryB(String frist, String last) {
        //最终文件的目录
        String path = frist + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + last + "/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return true;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 保存base64为图片
     * @CreateTime: 2020/12/10 14:17
     * @param: filePath
     * @param: fileName
     * @param: frist
     * @param: last
     * @return: java.lang.String
     */
    public static String currencySaveFileBase64(String filePath, String fileName, String frist, String last) {
        //最终文件的目录
        String path = frist + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + last + "/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return Base64Utils.writeFileByBase(filePath, path + fileName);
    }

    public static String currencySaveFile(String filePath, String fileName, String frist, String last) {
        //最终文件的目录
        String path = frist + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + last + "/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return saveFile(filePath, path + fileName);
    }

    public static String currencySaveFile(MultipartFile filePath, String fileName, String frist, String last) {
        //最终文件的目录
        String path = frist + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + last + "/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return saveFile(filePath, path + fileName);
    }


    public static String currencySaveFile(MultipartFile filePath, String fileName) {
        //最终文件的目录
        String path = "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return saveFile(filePath, path + fileName);
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO
     * @CreateTime: 2020/8/18 0:34
     * @param: filePath 文件地址 例如 http://a.jpg
     * @param: fileName 件名 例如  aaa.jpg aaa.pdf
     * @param: last 前缀
     * @return: java.lang.String
     */
    public static String currencySaveFileLast(String filePath, String fileName, String last) {
        //最终文件的目录
        String path = new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + last + "/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return saveFile(filePath, path + fileName);
    }

    public static String currencySaveFileLast(MultipartFile filePath, String fileName, String last) {
        //最终文件的目录
        String path = new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + last + "/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return saveFile(filePath, path + fileName);
    }

    public static String currencySaveFileFrist(String filePath, String fileName, String frist) {
        //最终文件的目录
        String path = frist + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return saveFile(filePath, path + fileName);
    }

    public static String currencySaveFileFrist(MultipartFile filePath, String fileName, String frist) {
        //最终文件的目录
        String path = frist + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return saveFile(filePath, path + fileName);
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO
     * @CreateTime: 2020/8/17 19:13
     * @param: filePath 文件地址  例如 http://a.jpg
     * @param: fileName 文件名   例如  aaa.jpg  aaa.pdf
     * @param: sort 给文件分类 通过","来分类  例如："图片,黑白图片" 图片里就有黑白图片
     * @return: java.lang.String
     * UploadFileUtils.currencySaveFile(filePath, "abc.jpg");
     */
    public static String currencySaveFile(String filePath, String fileName) {
        //最终文件的目录
        String path = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return saveFile(filePath, path + fileName);
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO
     * @CreateTime: 2020/8/18 0:10
     * @param: inFile 输入文件位置
     * @param: outFile 输出文件位置
     * @return: void
     */
    public static String saveFile(String inFile, String outFile) {
        if (inFile == null || inFile.equals("") || outFile == null || outFile.equals("")) {
            return null;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        //创建输入流输出流
        try {
            fis = new FileInputStream(inFile);
            fos = new FileOutputStream(outFile);
            int len;
            byte[] b = new byte[1024];
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            return outFile;
        } catch (Exception e) {
            System.out.println("上传文件失败,inFile:" + inFile + "outFile" + outFile + e.getMessage());
            return null;
        } finally {
            try {
                fos.close();
                fis.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO
     * @CreateTime: 2020/8/18 0:10
     * @param: inFile 输入文件位置
     * @param: outFile 输出文件位置
     * @return: void
     */
    public static String saveFile(MultipartFile inFile, String outFile) {
        if (inFile == null || inFile.equals("") || outFile == null || outFile.equals("")) {
            return null;
        }
        OutputStream os = null;
        InputStream inputStream = null;
        //String fileName = null;

        try {
            inputStream = inFile.getInputStream();
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            os = new FileOutputStream(outFile);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            return outFile;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

}
