package com.shp.dev.network.common.util.video;

import com.shp.dev.network.common.util.base64.Base64Utils;
import com.shp.dev.network.common.util.redis.RedisConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sound.sampled.AudioFormat;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO 获取视频资源(拉流抽帧保存到redis中)
 * @CreateTime: 2021/3/11 16:57
 * @PackageName: com.shp.dev.network.common.util.video
 * @ProjectName: network
 */

@Component
@EnableScheduling
@Slf4j
public class GetVideoResources {

    @Autowired
    private RedisConfig redisConfig;

    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
    private void process() {
        for (Map map : VideoConstant.tbVideoList) {
            play(map);
        }
    }

    public void play(Map map) {
        Thread playThread = new Thread(new Runnable() {
            public void run() {
                try {
                    final FFmpegFrameGrabber grabber = new FFmpegFrameGrabber((String) map.get("v_url"));
                    grabber.start();
                    final AudioFormat audioFormat = new AudioFormat(grabber.getSampleRate(), 16, grabber.getAudioChannels(), true, true);
                    final Java2DFrameConverter converter = new Java2DFrameConverter();
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    while (!Thread.interrupted()) {
                        Frame frame = grabber.grab();
                        if (frame == null) {
                            break;
                        }
                        if (frame.image != null) {
                            BufferedImage image = converter.convert(frame);
                            new Runnable() {
                                @SneakyThrows
                                public void run() {
                                    //写到本地视频会卡
                                    //ImageIO.write(image, "jpg", new File(out+ UUID.randomUUID().toString().replace("-","")+".jpg"));
                                    String baseByBufferedImage = Base64Utils.getBaseByBufferedImage(image);
                                    redisConfig.getRedisTemplate(1).opsForValue().set((String) map.get("channel"), baseByBufferedImage);
                                    //直接输出到websocket
                                    //String baseByFile = "data:image/png;base64,"+baseByBufferedImage;
                                    //WebSocketVideoServer.broadCastInfo(baseByFile);
                                }
                            }.run();
                        }
                    }
                    executor.shutdownNow();
                    executor.awaitTermination(10, TimeUnit.SECONDS);
                    grabber.stop();
                    grabber.release();
                } catch (Exception exception) {
                    System.exit(1);
                }
            }
        });
        playThread.start();
    }

}
