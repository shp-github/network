package com.shp.dev.network.common.util.minio;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO minio工具类
 * @CreateTime: 2020/9/16 18:06
 * @PackageName: com.shp.dev.network.common.util.minio
 * @ProjectName: network
 */
@Component
@Slf4j
public class MinioClientUtils {

    private final static String accessKey="AKIAIOSFODNN7EXAMPLE";
    private final static String secretKey="wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
    private final static String bucketName="test";
    private final static String endpoint="http://47.92.213.36:9000";

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 上传文件到服务器上
     * @CreateTime: 2020/9/16 20:09
     * @param: fileName  本地文件地址
     * @param: objectName 上传到服务中的文件名
     * @return: java.lang.String
     */
    public static String upload(String fileName,String objectName)  {
        try {
            // 参数为：图床，账号，密码
            MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
            // 检查文件夹是否已经存在
            boolean isExist = minioClient.bucketExists(bucketName);
            if(!isExist) {
                minioClient.makeBucket(bucketName);
            }
            //上传文件 参数为：文件夹，要存成的名字，要存的文件
            minioClient.putObject(bucketName,objectName, fileName);
            //文件访问路径
            return minioClient.getObjectUrl("test", objectName);
        } catch(Exception e) {
          return null;
        }
    }
    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 上传文件到服务器上
     * @CreateTime: 2020/9/16 20:09
     * @param: fileName  本地文件地址
     * @param: objectName 上传到服务中的文件名
     * @param: bucketName 指定目录
     * @return: java.lang.String
     */
    public static String upload(String fileName,String objectName,String bucketName)  {
        try {
            // 参数为：图床，账号，密码
            MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
            // 检查文件夹是否已经存在
            boolean isExist = minioClient.bucketExists(bucketName);
            if(!isExist) {
                minioClient.makeBucket(bucketName);
            }
            //上传文件 参数为：文件夹，要存成的名字，要存的文件
            minioClient.putObject(bucketName,objectName, fileName);
            //文件访问路径
            return minioClient.getObjectUrl("test", objectName);
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 下载文件
     * @CreateTime: 2020/9/16 20:19
     * @param: objectName 服务器上的文件名
     * @param: fileName 下载到本地的文件地址
     * @return: java.lang.String
     */
    public static String download(String objectName,String fileName)  {
        try {
            // 参数为：图床，账号，密码
            MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
            minioClient.statObject(bucketName, objectName);
            minioClient.getObject(bucketName, objectName, fileName);
            return fileName;
        } catch(Exception e) {
            return null;
        }
    }
    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO
     * @CreateTime: 2020/9/16 20:37
     * @param: objectName   服务器上的文件名
     * @param: fileName  下载到本地的文件地址
     * @param: bucketName 指定目录
     * @return: java.lang.String
     */
    public static String download(String objectName,String fileName,String bucketName)  {
        try {
            // 参数为：图床，账号，密码
            MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
            minioClient.statObject(bucketName, objectName);
            minioClient.getObject(bucketName, objectName, fileName);
            return fileName;
        } catch(Exception e) {
            return null;
        }
    }

}
