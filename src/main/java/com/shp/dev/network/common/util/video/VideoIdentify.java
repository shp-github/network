package com.shp.dev.network.common.util.video;

import com.shp.dev.network.common.util.MyStreamUtils;
import com.shp.dev.network.common.util.base64.Base64Utils;
import com.shp.dev.network.common.util.redis.RedisConfig;
import lombok.SneakyThrows;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO 识别（从redis中取到base64字符串然后进行识别处理，并把识别后的结果保存到redis中）
 * @CreateTime: 2021/3/12 11:29
 * @PackageName: com.shp.dev.network.common.util.video
 * @ProjectName: network
 */

//@Component
//@EnableScheduling
public class VideoIdentify {

    @Autowired
    private RedisConfig redisConfig;

    private CascadeClassifier carDetector;

    {
        //加载模型
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String templatePath = Class.class.getResource("/xml").getPath();
        templatePath += "/haarcascade_frontalface_alt.xml";
        templatePath = templatePath.substring(1);
        carDetector = new CascadeClassifier(templatePath);//调取训练出来的模型
    }

    //调度
    @SneakyThrows
    @Scheduled(initialDelay = 3000, fixedRate = 1000 / 5)
    public void scheduled(){
        for (Map map : VideoConstant.tbVideoList) {
            process(map);
        }
    }

    //处理图片
    private void process(Map map) {
        Thread processThread = new Thread(new Runnable() { @SneakyThrows
        public void run() {
            long start = System.currentTimeMillis();
            String base64 = (String) redisConfig.getRedisTemplate(1).opsForValue().get(map.get("channel"));
            if(base64!=null){
                Mat img = MyStreamUtils.base642Mat(base64);
                MatOfRect detections = new MatOfRect();
                carDetector.detectMultiScale(img, detections);
                Rect[] rects = detections.toArray();
                if (rects.length > 0) {
                    byte[] strBase64 = new BASE64Decoder().decodeBuffer(base64);
                    InputStream is = new ByteArrayInputStream(strBase64);
                    BufferedImage image = ImageIO.read(is);
                    Graphics g = image.getGraphics();
                    g.setColor(Color.RED);
                    for (Rect rect : rects) {
                        g.drawRect(rect.x, rect.y, rect.width, rect.height);
                    }
                    base64= Base64Utils.getBaseByBufferedImage(image);
                    //把识别出来的图片写到本地
                    //String s = Base64Utils.writeFileByBase(str, "E:\\videoout\\" + UUID.randomUUID().toString().replace("-", "") + ".jpg");
                    //System.out.println(s);
                }
                redisConfig.getRedisTemplate(2).opsForValue().set((String) map.get("channel"), base64);
                System.out.println("从redi+s里取出，识别，处理图片，写道本地消耗的时间" + (System.currentTimeMillis() - start));
            }
        }});
        processThread.start();
    }

    //画框
    public static String drawRect(String inPath, String outPath, Rect[] rects) {
        BufferedImage image = null;
        FileOutputStream out = null;
        try {
            //将图片放入缓存
            image = ImageIO.read(new File(inPath));
            Graphics g = image.getGraphics();
            Graphics2D g2 = (Graphics2D) g;
            Stroke stroke = new BasicStroke(2.0f);//设置线宽为3.0
            g2.setColor(Color.RED);
            g2.setStroke(stroke);
            for (Rect rect : rects) {
                g2.drawRect(rect.x, rect.y, rect.width, rect.height);//矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
            }
            g.dispose();
            out = new FileOutputStream(outPath);
            ImageIO.setUseCache(false);//使用系统缓存  --作用不明显
            ImageIO.write(image, "jpg", out);
            return outPath;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (image != null) {
                image.flush();
                image.getGraphics().dispose();
            }
        }
    }


}
