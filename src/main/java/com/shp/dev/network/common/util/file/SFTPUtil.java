package com.shp.dev.network.common.util.file;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/10/17 11:54
 * @PackageName: com.shp.dev.network.common.util.file
 * @ProjectName: network
 */

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

/**
 * @version 1.0.0
 * @ClassName: SFTPUtil
 * @Description: sftp连接工具类
 */
public class SFTPUtil {
    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    private ChannelSftp sftp;

    private Session session;

    // FTP 登录用户名
    private String userName;
    // FTP 登录密码
    private String password;
    // FTP 服务器地址IP地址
    private String host;
    // FTP 端口
    private int port;

    /**
     * 构造基于密码认证的sftp对象
     *
     * @param userName
     * @param password
     * @param host
     * @param port
     */
    public SFTPUtil(String userName, String password, String host, int port) {
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public SFTPUtil() {
    }

    /**
     * 连接sftp服务器
     *
     * @throws Exception
     */
    public void login() {
        try {
            JSch jsch = new JSch();
            log.info("sftp connect by host:{} username:{}", host, userName);

            session = jsch.getSession(userName, host, port);
            log.info("Session is build");
            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();
            log.info("Session is connected");

            Channel channel = session.openChannel("sftp");
            channel.connect();
            log.info("channel is connected");

            sftp = (ChannelSftp) channel;
            log.info(String.format("sftp server host:[%s] port:[%s] is connect successfull", host, port));
        } catch (JSchException e) {
            log.error("Cannot connect to specified sftp server : {}:{} \n Exception message is: {}", new Object[]{host, port, e.getMessage()});
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                log.info("sftp is closed already");
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
                log.info("sshSession is closed already");
            }
        }
    }

    /**
     * 递归根据路径创建文件夹
     *
     * @param dirs     根据 / 分隔后的数组文件夹名称
     * @param tempPath 拼接路径
     * @param length   文件夹的格式
     * @param index    数组下标
     * @return
     */
    public void mkdirDir(String[] dirs, String tempPath, int length, int index) {
        // 以"/a/b/c/d"为例按"/"分隔后,第0位是"";顾下标从1开始
        index++;
        if (index < length) {
            // 目录不存在，则创建文件夹
            tempPath += "/" + dirs[index];
        }
        try {
            log.info("检测目录[" + tempPath + "]");
            sftp.cd(tempPath);
            if (index < length) {
                mkdirDir(dirs, tempPath, length, index);
            }
        } catch (SftpException ex) {
            log.warn("创建目录[" + tempPath + "]");
            try {
                sftp.mkdir(tempPath);
                sftp.cd(tempPath);
            } catch (SftpException e) {
                e.printStackTrace();
                log.error("创建目录[" + tempPath + "]失败,异常信息[" + e.getMessage() + "]");

            }
            log.info("进入目录[" + tempPath + "]");
            mkdirDir(dirs, tempPath, length, index);
        }
    }

    /**
     * 将输入流的数据上传到sftp作为文件（多层目录）
     *
     * @param directory    上传到该目录(多层目录)
     * @param sftpFileName sftp端文件名
     * @param input        输入流
     * @throws SftpException
     * @throws Exception
     */
    public void uploadMore(String directory, String sftpFileName, InputStream input) throws SftpException {
        try {
            sftp.cd(directory);
        } catch (SftpException e) {
            // 目录不存在，则创建文件夹
            String[] dirs = directory.split("/");
            String tempPath = "";
            int index = 0;
            mkdirDir(dirs, tempPath, dirs.length, index);
        }
        sftp.put(input, sftpFileName);// 上传文件
    }

    /**
     * 将输入流的数据上传到sftp作为文件
     *
     * @param directory    上传到该目录(单层目录)
     * @param sftpFileName sftp端文件名
     * @param input        输入流
     * @throws SftpException
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, InputStream input) throws SftpException {
        try {
            sftp.cd(directory);
        } catch (SftpException e) {
            log.warn("directory is not exist");
            sftp.mkdir(directory);
            sftp.cd(directory);
        }
        sftp.put(input, sftpFileName);
        log.info("file:{} is upload successful", sftpFileName);
    }

    /**
     * 上传单个文件
     *
     * @param directory  上传到sftp目录
     * @param uploadFile 要上传的文件,包括路径
     * @throws FileNotFoundException
     * @throws SftpException
     * @throws Exception
     */
    public void upload(String directory, String uploadFile) throws FileNotFoundException, SftpException {
        File file = new File(uploadFile);
        upload(directory, file.getName(), new FileInputStream(file));
    }

    /**
     * 将byte[]上传到sftp，作为文件。注意:从String生成byte[]是，要指定字符集。
     *
     * @param directory    上传到sftp目录
     * @param sftpFileName 文件在sftp端的命名
     * @param byteArr      要上传的字节数组
     * @throws SftpException
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, byte[] byteArr) throws SftpException {
        upload(directory, sftpFileName, new ByteArrayInputStream(byteArr));
    }

    /**
     * 将字符串按照指定的字符编码上传到sftp
     *
     * @param directory    上传到sftp目录
     * @param sftpFileName 文件在sftp端的命名
     * @param dataStr      待上传的数据
     * @param charsetName  sftp上的文件，按该字符编码保存
     * @throws UnsupportedEncodingException
     * @throws SftpException
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, String dataStr, String charsetName) throws UnsupportedEncodingException, SftpException {
        upload(directory, sftpFileName, new ByteArrayInputStream(dataStr.getBytes(charsetName)));
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     * @throws SftpException
     * @throws FileNotFoundException
     * @throws Exception
     */
    public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        File file = new File(saveFile);
        sftp.get(downloadFile, new FileOutputStream(file));
        log.info("file:{} is download successful", downloadFile);
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     * @throws SftpException
     * @throws IOException
     * @throws Exception
     */
    public byte[] download(String directory, String downloadFile) throws SftpException, IOException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        InputStream is = sftp.get(downloadFile);
        byte[] fileData = IOUtils.toByteArray(is);
        log.info("file:{} is download successful", downloadFile);
        return fileData;
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @throws SftpException
     * @throws Exception
     */
    public void delete(String directory, String deleteFile) throws SftpException {
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     * @return
     * @throws SftpException
     */
    public Vector<?> listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
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
    public String currencySaveFile(File filePath, String fileName, String frist, String last) {
        //最终文件的目录
        String path = frist + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + last + "/";
        return saveFile(filePath, path, fileName);
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
    public String currencySaveFile(File filePath, String fileName, String last) {
        //最终文件的目录
        String path = new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + last + "/";
        return saveFile(filePath, path, fileName);
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
    public String currencySaveFile(File filePath, String fileName) {
        //最终文件的目录
        String path = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        return saveFile(filePath, path, fileName);
    }


    public String saveFile(File file, String directory, String fileName) {
        try {
            SFTPUtil sftp = new SFTPUtil(userName, password, host, port);
            sftp.login();
            InputStream is = new FileInputStream(file);
            // 多级目录创建并上传
            sftp.uploadMore(directory, fileName, is);
            sftp.logout();
        } catch (Exception e) {
            return null;
        }
        return "/" + directory + fileName;
    }
    public static void main(String[] args) {
        SFTPUtil sftp = new SFTPUtil("root", "sS123456", "47.92.213.36", 22);
        //public String currencySaveFile(String filePath, String fileName, String frist, String last) {
        String s = sftp.currencySaveFile(new File("e://a.jpg"), "shp.jpg", "111");
        System.out.println(s);
    }

}
