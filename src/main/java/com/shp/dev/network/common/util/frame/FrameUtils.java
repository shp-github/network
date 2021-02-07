package com.shp.dev.network.common.util.frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 画框工具类
 * @CreateTime: 2020/8/15 12:04
 * @PackageName: com.vimochina.vimo.util.ai
 * @ProjectName: wisdomeyeapi0114
 */
public class FrameUtils {

    static Random random = new Random();//随机出颜色信息
    static BufferedImage image = null;
    static Graphics g = null;
    static Graphics2D graphics2D=null;
    static List<Color> colors=new ArrayList<>();
    static {
        colors.add(Color.red);
        colors.add(Color.green);
        colors.add(Color.blue);
        colors.add(Color.magenta);
        colors.add(Color.orange);
        colors.add(Color.cyan);
        colors.add(Color.yellow);
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 初始化画笔 返回Graphics2D
     * @CreateTime: 2020/9/5 18:01
     * @param: filePath
     * @return: java.awt.Graphics2D
     */
    public static Graphics2D initFrame(String filePath){
        try {
            //将图片放入缓存
            image = ImageIO.read(new File(filePath));
            g = image.getGraphics();
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            return (Graphics2D)g;
        }catch (Exception e){
           return null;
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 画点，根据xy轴画点，点的大小是宽和高
     * @CreateTime: 2020/8/17 0:18
     * @param: filePath 文件地址
     * @param: x x轴
     * @param: y y轴
     * @param: width 宽
     * @param: height 高
     * @return: java.lang.String
     */
    public static String fillOval(String filePath,int x, int y, int width, int height){
        graphics2D = FrameUtils.initFrame(filePath);
        graphics2D.setStroke(new BasicStroke(3.0f));
        graphics2D.setColor(getRanColor());//画笔颜色
        graphics2D.fillOval(x, y, width, height);
        return out(filePath);
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 画线，根据第一个xy坐标到第二个xy轴画线
     * @CreateTime: 2020/9/5 18:01
     * @param: filePath
     * @param: x1
     * @param: y1
     * @param: x2
     * @param: y2
     * @return: java.lang.String
     */
    public static String drawLine(String filePath,int x1, int y1, int x2, int y2){
        graphics2D = FrameUtils.initFrame(filePath);
        graphics2D.setStroke(new BasicStroke(4.0f));
        graphics2D.setColor(getRanColorPip());//画笔颜色
        graphics2D.drawLine(x1, y1, x2, y2);
        return out(filePath);
    }

    /**
     *
     * @param filePath
     * @param x
     * @param y
     * @param width
     * @param height
     * @param arcWidth
     * @param arcHeight
     * @return
     */
    public static String drawRoundRect(String filePath,int x, int y, int width, int height,int arcWidth, int arcHeight){
        graphics2D = FrameUtils.initFrame(filePath);
        graphics2D.setStroke(new BasicStroke(4.0f));
        graphics2D.setColor(getRanColorPip());//画笔颜色
        graphics2D.drawRoundRect(x, y, width, height,arcWidth,arcHeight);
        return out(filePath);
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 画框，根据xy坐标和宽高画框
     * @CreateTime: 2020/8/17 0:27
     * @param: filePath 文件地址
     * @param: x x轴
     * @param: y y轴
     * @param: width 宽
     * @param: height 高
     * @return: java.lang.String
     * 画框的底层就是调用画线的方法
     */
    public static String drawRect(String filePath,int x, int y, int width, int height){
        graphics2D = FrameUtils.initFrame(filePath);
        graphics2D.setStroke(new BasicStroke(3.0f));
        graphics2D.setColor(getRanColorPip());//画笔颜色
        graphics2D.drawRect(x, y, width, height);
        return out(filePath);
    }
    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 写字，根据xy轴写字
     * @CreateTime: 2020/8/20 22:01
     * @param: filePath
     * @param: str 文本内容
     * @param: x
     * @param: y
     * @return: java.lang.String
     */
    public static String pencilWrite(String filePath,String str, int x, int y){
        graphics2D = FrameUtils.initFrame(filePath);
        graphics2D.setStroke(new BasicStroke(3.0f));
        graphics2D.setColor(Color.RED);//画笔颜色
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        g.drawString(str, x, y);
        return out(filePath);
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 输出图片
     * @CreateTime: 2020/8/16 23:57
     * @param: filePath
     * @return: void
     */
    public static String out(String filePath){
        try {
            ImageIO.write(image, "jpg",  new FileOutputStream(filePath));
            image.flush();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return filePath;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 返回随机的颜色
     * @CreateTime: 2020/8/16 17:41
     * @param:
     * @return: java.awt.Color
     */
    public static Color getRanColor(){
        return new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 返回随机的标准颜色
     * @param:
     * @return: java.awt.Color
     */
    public static Color getRanColorPip(){
        return  colors.get( random.nextInt(colors.size()));
    }

}
