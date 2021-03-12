package com.shp.dev.network.common.util.video;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import java.awt.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO 播放器
 * @CreateTime: 2021/3/11 20:45
 * @PackageName: com.shp.dev.network.common.util.video
 * @ProjectName: network
 */
public class VideoPlayer {

    //获取视频流地址
    static List<String> urls = Arrays.asList("rtmp://58.200.131.2:1935/livetv/cctv1", "rtmp://58.200.131.2:1935/livetv/cctv2","rtmp://58.200.131.2:1935/livetv/cctv10");
    //拉流

    public static void main(String[] args) {
            play("rtmp://58.200.131.2:1935/livetv/cctv11");
    }
    public static void play(String filepath) {
        Thread playThread = new Thread(new Runnable() { public void run() {
            try {
                //开始抓取
                @SuppressWarnings("resource")
                final FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filepath);
                grabber.start();
                //初始化视图
                CanvasFrame canvas = new CanvasFrame("云端",CanvasFrame.getDefaultGamma() / grabber.getGamma());//新建一个窗口
                canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //初始化扬声器
                final AudioFormat audioFormat = new AudioFormat(grabber.getSampleRate(), 16, grabber.getAudioChannels(), true, true);
                final DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                final SourceDataLine soundLine = (SourceDataLine) AudioSystem.getLine(info);
                soundLine.open(audioFormat);
                soundLine.start();
                //图像转换器 将帧中的image相关信息提取出来
                final Java2DFrameConverter converter = new Java2DFrameConverter();

                ExecutorService executor = Executors.newSingleThreadExecutor();

                while (!Thread.interrupted()) {
                    Frame frame = grabber.grab();
                    if (frame == null) {
                        break;
                    }

                    //帧 为图像帧 或 声音帧
                    if (frame.image != null) {
                        Image image = converter.convert(frame);
                        new Runnable() { public void run() {
                            canvas.showImage(image);
                        }}.run();;
                    } else if (frame.samples != null) {
                        final ShortBuffer channelSamplesShortBuffer = (ShortBuffer) frame.samples[0];
                        channelSamplesShortBuffer.rewind();

                        final ByteBuffer outBuffer = ByteBuffer.allocate(channelSamplesShortBuffer.capacity() * 2);

                        for (int i = 0; i < channelSamplesShortBuffer.capacity(); i++) {
                            short val = channelSamplesShortBuffer.get(i);
                            outBuffer.putShort(val);
                        }
                        /*
                         * 写入到扬声器
                         *   并准备下一次读取
                         */
                        try {
                            executor.submit(new Runnable() { public void run() {
                                soundLine.write(outBuffer.array(), 0, outBuffer.capacity());
                                outBuffer.clear();
                            }}).get();
                        } catch (InterruptedException interruptedException) {
                            System.out.println("打断线程啦");
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                executor.shutdownNow();
                executor.awaitTermination(10, TimeUnit.SECONDS);
                soundLine.stop();
                grabber.stop();
                grabber.release();
            } catch (Exception exception) {

                System.exit(1);
            }
        }});
        playThread.start();

    }
}
