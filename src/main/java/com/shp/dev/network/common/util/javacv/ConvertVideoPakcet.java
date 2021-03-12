package com.shp.dev.network.common.util.javacv;


import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameGrabber.Exception;

import java.io.IOException;
import java.util.Random;

import static org.bytedeco.ffmpeg.global.avcodec.av_packet_unref;


/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO rtsp转封装rtmp
 * @CreateTime: 2020/12/30 22:59
 * @PackageName: com.shp.dev.network.common.util.javacv.push
 * @ProjectName: network
 */
public class ConvertVideoPakcet {
    FFmpegFrameGrabber grabber = null;
    FFmpegFrameRecorder record = null;
    int width = -1, height = -1;

    // 视频参数
    protected int audiocodecid;
    protected int codecid;
    protected double framerate;// 帧率
    protected int bitrate;// 比特率

    /**
     * 选择视频源.
     */
    public ConvertVideoPakcet from(String src) throws Exception {
        // 采集/抓取器
        grabber = new FFmpegFrameGrabber(src);
        if (src.indexOf("rtsp") >= 0) {
            grabber.setOption("rtsp_transport", "tcp");
        }
        grabber.start();// 开始之后ffmpeg会采集视频信息，之后就可以获取音视频信息
        if (width < 0 || height < 0) {
            width = grabber.getImageWidth();
            height = grabber.getImageHeight();
        }
        // 视频参数
        audiocodecid = grabber.getAudioCodec();
        return this;
    }

    /**
     * 选择输出.
     */
    public ConvertVideoPakcet to(String out) throws IOException {
        // 录制/推流器
        record = new FFmpegFrameRecorder(out, width, height);
        record.setGopSize(2);
        record.setFrameRate(framerate);
        record.setVideoBitrate(bitrate);
        AVFormatContext fc = null;
        if (out.indexOf("rtmp") >= 0 || out.indexOf("flv") > 0) {
            // 封装格式flv
            record.setFormat("flv");
            record.setAudioCodecName("aac");
            fc = grabber.getFormatContext();
        }
        record.start(fc);
        return this;
    }

    /**
     * 转封装.
     */
    public ConvertVideoPakcet go() throws IOException {
        long err_index = 0;//采集或推流导致的错误次数test
        //连续五次没有采集到帧则认为视频采集结束，程序错误次数超过1次即中断程序
        for (int no_frame_index = 0; no_frame_index < 5 || err_index > 1; ) {
            AVPacket pkt = null;
            try {
                //没有解码的音视频帧
                pkt = grabber.grabPacket();
                if (pkt == null || pkt.size() <= 0 || pkt.data() == null) {
                    //空包记录次数跳过
                    no_frame_index++;
                    continue;
                }
                //不需要编码直接把音视频帧推出去
                err_index += (record.recordPacket(pkt) ? 0 : 1);//如果失败err_index自增1
                av_packet_unref(pkt);
            } catch (Exception e) {//推流失败
                err_index++;
            } catch (IOException e) {
                err_index++;
            }
        }
        return this;
    }

    public static void main(String[] args) throws Exception, IOException {

        String out = "rtmp://10.8.240.5:9999/live/" + getRandomString(10);

        //运行，设置视频源和推流地址
        new ConvertVideoPakcet().from("rtsp://admin:admin123@10.8.8.11:554/cam/realmonitor?channel=1&subtype=0")
                .to(out)
                .go();
    }


    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


}

