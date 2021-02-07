package com.shp.dev.network.common.util.image;


import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 截取指定位置图片
 * @CreateTime: 2020/8/15 17:58
 * @PackageName: com.vimochina.vimo.util.ai
 * @ProjectName: wisdomeyeapi0114
 */
public class CutImgUtils {

    public static void main(String[] args) throws IOException {

        BufferedImage bi = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        bi.getGraphics().fillRect(0, 0, 800, 600);
        ImageIO.write(bi, "jpeg", new File("e:/a.jpg"));

    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 绘制遮罩图
     * @CreateTime: 2020/12/9 18:14
     * @param: width
     * @param: height
     * @param: outFile
     * @param: arr
     * @param: d
     * @return: java.lang.String
     */
    public static String mask(int width, int height, File outFile, net.sf.json.JSONArray arr, double d) {
        BufferedImage img = null;
        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            image.getGraphics().fillRect(0, 0, width, height);

            //绘制截图区域
            GeneralPath clip = new GeneralPath(GeneralPath.WIND_EVEN_ODD, arr.size());
            clip.moveTo(arr.getJSONArray(0).getInt(0) / d, arr.getJSONArray(0).getInt(1) / d);
            for (int i = 0; i < arr.size(); i++) {
                clip.lineTo(arr.getJSONArray(i).getInt(0) / d, arr.getJSONArray(i).getInt(1) / d);
            }
            clip.closePath();
            img = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

            Graphics2D g2d = img.createGraphics();
            g2d.setClip(clip);
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            FileOutputStream out = new FileOutputStream(outFile);//输出图片的地址
            ImageIO.setUseCache(false);//使用系统缓存
            ImageIO.write(img, "jpg", out);
            image.flush();
            image.getGraphics().dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (img != null) {
                img.flush();
            }
        }
        return outFile.getPath();
    }




    /**
     * 获取随机颜色
     *
     * @return
     */
    public static Color getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }


    public static String cutPolygonImage(String inputFilePath, String outFilePath, net.sf.json.JSONArray arr, double d) {
        BufferedImage img = null;
        try {
            BufferedImage image = ImageIO.read(new File(inputFilePath));
            GeneralPath clip = new GeneralPath(GeneralPath.WIND_EVEN_ODD, arr.size());
            clip.moveTo(arr.getJSONArray(0).getInt(0) / d, arr.getJSONArray(0).getInt(1) / d);
            for (int i = 0; i < arr.size(); i++) {
                clip.lineTo(arr.getJSONArray(i).getInt(0) / d, arr.getJSONArray(i).getInt(1) / d);
            }
            clip.closePath();
            //Rectangle bounds = clip.getBounds();
            //BufferedImage img = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_BGR);
            img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR);
            Graphics2D g2d = img.createGraphics();
            //clip.transform(AffineTransform.getTranslateInstance(0, 0));
            g2d.setClip(clip);
            //g2d.translate(0, 0);
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            FileOutputStream out = new FileOutputStream(outFilePath);//输出图片的地址
            ImageIO.setUseCache(false);//使用系统缓存
            ImageIO.write(img, "jpg", out);
        } catch (Exception e) {
            return null;
        } finally {
            if (img != null) {
                img.flush();
            }
        }
        return outFilePath;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO  裁剪(多边形) 截取不规则图片
     * @CreateTime: 2020/9/8 20:55
     * @param: inputFilePath 图片输入路径
     * @param: outFilePath 图片输出路径
     * @param: x x轴坐标点数组
     * @param: y y轴坐标点数组
     * @param: n  坐标点数量
     * @param: d 图片比例,如果1比1的则输入1
     * @return: java.lang.String
     */
    public static String cutPolygonImage(String inputFilePath, String outFilePath, int x[], int y[], int n, double d) {
        BufferedImage img = null;
        try {
            BufferedImage image = ImageIO.read(new File(inputFilePath));
            GeneralPath clip = new GeneralPath(GeneralPath.WIND_EVEN_ODD, n);
            clip.moveTo(x[0] / d, y[0] / d);
            for (int i = 1; i < x.length; i++) {
                clip.lineTo(x[i] / d, y[i] / d);
            }
            clip.closePath();
            //Rectangle bounds = clip.getBounds();
            //BufferedImage img = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_BGR);
            img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR);
            Graphics2D g2d = img.createGraphics();
            //clip.transform(AffineTransform.getTranslateInstance(0, 0));
            g2d.setClip(clip);
            //g2d.translate(0, 0);
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            FileOutputStream out = new FileOutputStream(outFilePath);//输出图片的地址
            ImageIO.setUseCache(false);//使用系统缓存
            ImageIO.write(img, "jpg", out);
        } catch (Exception e) {
            return null;
        } finally {
            if (img != null) {
                img.flush();
            }
        }
        return outFilePath;
    }


    public static String cutPolygonImagePNG(String inputFilePath, String outFilePath, int x[], int y[], int n, double d) {
        String substring = null;
        BufferedImage img = null;
        FileOutputStream out = null;
        try {
            int index = outFilePath.indexOf(".");
            substring = outFilePath.substring(0, index) + ".png";
            BufferedImage image = ImageIO.read(new File(inputFilePath));
            GeneralPath clip = new GeneralPath(GeneralPath.WIND_EVEN_ODD, n);
            clip.moveTo(x[0] / d, y[0] / d);
            for (int i = 1; i < x.length; i++) {
                clip.lineTo(x[i] / d, y[i] / d);
            }
            clip.closePath();
            //Rectangle bounds = clip.getBounds();
            //BufferedImage img = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_BGR);
            img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            //clip.transform(AffineTransform.getTranslateInstance(0, 0));
            g2d.setClip(clip);
            //g2d.translate(0, 0);
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            out = new FileOutputStream(substring);//输出图片的地址
            ImageIO.setUseCache(false);//使用系统缓存
            ImageIO.write(img, "png", out);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "截出png失败");
            return null;
        } finally {
            if (img != null) {
                img.flush();
            }
        }
        return substring;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 截取出指定位置为图片
     * @CreateTime: 2020/8/15 18:01
     * @param: imPath
     * @param: outPath
     * @param: x
     * @param: y
     * @param: width
     * @param: height
     * @return: void
     */
    public static String cut(String imPath, String outPath, int x, int y, int width, int height) {
        FileInputStream is = null;
        ImageInputStream iis = null;
        BufferedImage bi = null;
        try {
            //批量读取图片文件
            is = new FileInputStream(imPath);
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = it.next();
            iis = ImageIO.createImageInputStream(is);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            bi = reader.read(0, param);
            ImageIO.setUseCache(false);//使用系统缓存  --作用不明显
            ImageIO.write(bi, "jpg", new File(outPath));
            return outPath;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (bi != null) {
                bi.flush();
            }
        }
    }

}
