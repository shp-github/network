package com.shp.dev.network.common.util.javacv;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @CreateBy: shp
 * @version：1.0
 * @Description: TODO 抽取摄像头或者视频为图片
 * @CreateTime: 2020/12/21 16:41
 */
public class VideoProcessor {

    @Test
    public void frameDraw() throws Exception {


        //获取网络摄像头
        //String rtspUrl = "rtsp://admin:admin123@10.8.8.11:554/cam/realmonitor?channel=1&subtype=0";
        //FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(rtspUrl);
        //grabber.setOption("rtsp_transport", "tcp"); // 使用tcp的方式，不然会丢包很严重
        //grabber.start();

        //获取本地摄像头
        //VideoInputFrameGrabber grabber = VideoInputFrameGrabber.createDefault(0);
        //grabber.start();

        //获取本地摄像头
        //OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        //grabber.start();   //开始获取摄像头数据

        //读取本地视频
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault("e://video//aaa.mp4");
        grabber.start();

        System.out.println("视频总帧数：" + grabber.getLengthInFrames());
        System.out.println("每秒的帧数（帧率）：" + grabber.getFrameRate());
        System.out.println("视频长度: " + grabber.getLengthInTime());
        System.out.println("视频的宽" + grabber.getImageHeight());
        System.out.println("视频的高" + grabber.getImageWidth());

        //Frame frame = grabber.grab();  //调用本地摄像头使用
        Frame frame = grabber.grabImage();//从视频中抽帧是使用

        String file = "E:\\image\\";

        //抽帧间隔（按照帧数抽）每秒抽一帧
        int t = (int) grabber.getFrameRate();

        long i = 0;
        while (i < grabber.getLengthInFrames() || grabber.getLengthInTime() == 0) {
            i++;
            if (i % t == 0 || grabber.getLengthInTime() == 0) {
                witeImage(frame, file + System.currentTimeMillis() + ".jpg");
            }
        }
        grabber.stop();
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 抽帧写出图片
     * @CreateTime: 2021/1/2 19:18
     * @param: frame
     * @param: outFile
     * @return: java.lang.String
     */
    public static String witeImage(Frame frame, String outFile) {
        return witeImage(frame, new File(outFile));
    }

    public static String witeImage(Frame frame, File outFile) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        if (null == frame || null == frame.image) {
            return null;
        }
        BufferedImage bi = converter.getBufferedImage(frame);
        try {
            ImageIO.write(bi, outFile.getName().substring(outFile.getName().indexOf(".") + 1), outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outFile.getPath();
    }


}
