package com.shp.dev.network.common.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
public class UploadURLFileUtils {

    public static void main(String[] args) {

        String filePath="https://ieye.hsttw.cn/snapshot/firePic/2020/10/12/13112800001310001864/1602466835563.jpg";
        String s = saveFile(filePath, "E:\\image\\" + filePath);
        System.out.println(s);

    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO
     * @CreateTime: 2020/8/18 0:34
     * @param: filePath 文件地址 例如 http://a.jpg
     * @param: fileName 件名 例如  aaa.jpg aaa.pdf
     * @param: frist 前缀
     * @param: last 后缀
     * @return: java.lang.String
     */
    public static String currencySaveFile(String filePath, String fileName, String frist,String last) {
        //最终文件的目录
        String path=frist+"/"+new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"/"+last+"/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return saveFile(filePath,path+fileName);
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
    public static String currencySaveFile(String filePath, String fileName, String last) {
        //最终文件的目录
        String path=new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"/"+last+"/";
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return saveFile(filePath,path+fileName);
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
     * UploadURLFileUtils.currencySaveFile(filePath, "abc.jpg");
     */
    public static String currencySaveFile(String filePath, String fileName) {
        //最终文件的目录
        String path=new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        //文件目录不存在则创建
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return saveFile(filePath,path+fileName);
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 下载在线图片地址  下载失败返回null  下载成功返回图片地址
     * @CreateTime: 2020/8/18 0:10
     * @param: inFile 输入文件位置
     * @param: outFile 输出文件位置
     * @return: void
     */
    public static String saveFile(String inFile, String outFile){

        FileOutputStream os=null;
        InputStream is=null;
        try {
            // 构造URL
            URL url = new URL(inFile);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File file = new File(outFile);
            os = new FileOutputStream(file, true);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            return outFile;
        }catch (Exception e){
            return null;
        }finally {
            try {
                // 完毕，关闭所有链接
                if(os!=null){
                    os.close();
                }
                if(is!=null){
                    is.close();
                }
            } catch (IOException e) {
            }
        }
    }

}
