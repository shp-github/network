package com.shp.dev.network.common.util.fastdfs;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/9/18 21:16
 * @PackageName: com.shp.dev.network.common.util.fastdfs
 * @ProjectName: network
 */
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class FastDFSClient {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private FdfsWebServer fdfsWebServer;

    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     */
    public String uploadFile(MultipartFile file){
        try {
            StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
            return getResAccessUrl(storePath);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     */
    public String uploadFile(File file){
        try {
            FileInputStream inputStream = new FileInputStream (file);
            StorePath storePath = storageClient.uploadFile(inputStream,file.length(), FilenameUtils.getExtension(file.getName()),null);
            return getResAccessUrl(storePath);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 将一段字符串生成一个文件上传
     * @param content 文件内容
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension) {
        try {
            byte[] buff = content.getBytes(Charset.forName("UTF-8"));
            ByteArrayInputStream stream = new ByteArrayInputStream(buff);
            StorePath storePath = storageClient.uploadFile(stream,buff.length, fileExtension,null);
            return getResAccessUrl(storePath);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 封装图片完整URL地址
     */
    private String getResAccessUrl(StorePath storePath) {
        try {
            return fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 删除文件
     * @param fileUrl 文件访问地址
     * @return
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            System.out.println(e.getMessage());
            /** TODO 只是测试，所以未使用，logger，正式环境请修改打印方式 **/
        }
    }
    /**
     * 下载文件
     *
     * @param fileUrl 文件URL
     * @return 文件字节
     * @throws IOException
     */
    public byte[] downloadFile(String fileUrl) throws IOException {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = storageClient.downloadFile(group, path, downloadByteArray);
        return bytes;
    }

}