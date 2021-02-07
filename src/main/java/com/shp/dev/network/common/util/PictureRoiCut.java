package com.shp.dev.network.common.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 截取指定位置图片
 * @CreateTime: 2020/8/15 17:58
 * @PackageName: com.vimochina.vimo.util.ai
 * @ProjectName: wisdomeyeapi0114
 */
public class PictureRoiCut{

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 截取不规则图片
     * @CreateTime: 2020/9/9 11:11
     * @param: args
     * @return: void
     */
    public static void main(String[] args) throws IOException {
        String inputFilePath="D:/b.jpg";
        String outFilePath="D:/b.jpg";
        int [] arr[] ={{860,20},{650,20},{300,1000},{1700,1000}};
        int xArr []=new int[arr.length];
        int yArr []=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                xArr[i]=arr[i][0];
                yArr[i]=arr[i][1];
            }
        }
        PictureRoiCut.cutPolygonImage(inputFilePath, outFilePath,xArr,yArr,yArr.length,1);
        System.out.println("ok");
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
    public static String cutPolygonImage(String inputFilePath,String outFilePath,int x[], int y[],int n,double d){
        try {
            BufferedImage image = ImageIO.read(new File(inputFilePath));
            GeneralPath clip = new GeneralPath(GeneralPath.WIND_EVEN_ODD, n);
            //int x[]={860,650,300,1700};
            //int y[]={20,20,1000,1000};
            clip.moveTo(x[0]/d, y[0]/d);
            for (int i = 1; i < x.length; i++) {
                clip.lineTo(x[i]/d, y[i]/d);
            }
            clip.closePath();
            //Rectangle bounds = clip.getBounds();
            //BufferedImage img = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_BGR);
            BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR);
            Graphics2D g2d = img.createGraphics();
            //clip.transform(AffineTransform.getTranslateInstance(0, 0));
            g2d.setClip(clip);
            //g2d.translate(0, 0);
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            FileOutputStream out = new FileOutputStream(outFilePath);//输出图片的地址
            ImageIO.write(img, "jpg", out);
            out.close();
        } catch (Exception e) {
            return null;
        }
        return outFilePath;
    }

    public static String cutPolygonImagePNG(String inputFilePath,String outFilePath,int x[], int y[],int n,double d){
        try {
            BufferedImage image = ImageIO.read(new File(inputFilePath));
            GeneralPath clip = new GeneralPath(GeneralPath.WIND_EVEN_ODD, n);
            clip.moveTo(x[0]/d, y[0]/d);
            for (int i = 1; i < x.length; i++) {
                clip.lineTo(x[i]/d, y[i]/d);
            }
            clip.closePath();
            Rectangle bounds = clip.getBounds();
            BufferedImage img = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_BGR);
            Graphics2D g2d = img.createGraphics();
            g2d.setClip(clip);
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            FileOutputStream out = new FileOutputStream(outFilePath);//输出图片的地址
            ImageIO.write(img, "png", out);
            out.close();
        } catch (Exception e) {
            return null;
        }
        return outFilePath;
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
    public static String  cut(String imPath, String outPath, int x, int y, int width, int height) {
        FileInputStream is = null;
        ImageInputStream iis = null;
        try {
            //批量读取图片文件
            is = new FileInputStream(imPath);
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = it.next();
            //image stream
            iis = ImageIO.createImageInputStream(is);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            boolean pd = ImageIO.write(bi, "jpg", new File(outPath));
            if(pd){
                return outPath;
            }
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (iis != null) {
                    iis.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
        }
    }

}
