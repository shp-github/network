package com.shp.dev.network.common.util.opencv;

import com.shp.dev.network.common.util.MyStreamUtils;
import com.shp.dev.network.common.util.base64.Base64Utils;
import lombok.SneakyThrows;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

public class New {
    @SneakyThrows
    public static void main(String[] args) throws UnsupportedEncodingException {
        //-Djava.library.path=E:\javawork\network\lib\64
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String templatePath = Class.class.getResource("/xml").getPath();
        templatePath += "/haarcascade_frontalface_alt.xml";
        templatePath = templatePath.substring(1);
        CascadeClassifier carDetector = new CascadeClassifier(templatePath);//调取训练出来的模型
        String filepath = "F:\\in\\";//输入路径，
        String outPath = "F:\\out\\";//输出路径

        File file = new File(filepath);
        File outfile = new File(outPath);

        if (!outfile.exists()) {
            outfile.mkdir();
        }
        if (!file.isDirectory()) {
            System.out.println("请输入正确的文件夹路径");
        } else if (file.isDirectory()) {
            int j = 0;

            String[] fileList = file.list();

            for (String f : fileList) {
                long start = System.currentTimeMillis();
                System.out.println(j++);


                String baseByFile = Base64Utils.getBaseByFile(filepath + f);
                Mat img = MyStreamUtils.base642Mat(baseByFile);

                //Mat img = Imgcodecs.imread(filepath + f);
                MatOfRect detections = new MatOfRect();
                carDetector.detectMultiScale(img, detections);
                for (Rect rect : detections.toArray()) {
                    //将识别到的目表物体框起来
                    System.out.println("将识别到的目表物体框起来" + j);
                    //Imgproc.rectangle(img,new Point(rect.x,rect.y),new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,255,0));
                    //Imgcodecs.imwrite(outPath+"/"+imgName, img);

                    String s = drawRect(filepath+f,outPath + f, rect.x, rect.y, rect.width, rect.height);
                    System.out.println(s);

                }

                System.out.println(System.currentTimeMillis()-start);

            }
        }
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 画框
     * @CreateTime: 2020/10/19 17:45
     * @param: urlImgPath
     * @param: orgJsonr
     * @param: location
     * @param: prob
     * @return: java.lang.String
     */
    public static String drawRect(String inPath, String outPath, int left, int top, int width, int height) {
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
            g2.drawRect(left, top, width, height);//矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
            g.dispose();
            //输出图片的地址
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
            }
        }
    }


}

