package com.shp.dev.network.common.util;

import com.shp.dev.network.common.util.frame.FrameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 私有化部署给人画框写字
 * @CreateTime: 2020/8/20 19:40
 * @PackageName: com.vimochina.vimo.util.ai
 * @ProjectName: wisdomeyeapi0114
 */
public class PrivateBodyAttr {


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 截图再框中写字
     * @CreateTime: 2020/8/21 4:44
     * @param: urlImgPath
     * @param: bodyAttr
     * @return: java.lang.String
     */
    public static String cutFromWrite(String urlImgPath, JSONObject bodyAttr) throws IOException {

        if (!bodyAttr.has("error_msg")) {
            JSONArray person_info = bodyAttr.getJSONArray("person_info");
            for (int i = 0; i < person_info.length(); i++) {
                if (person_info.getJSONObject(i).has("attributes")) {
                    JSONObject attributes = person_info.getJSONObject(i).getJSONObject("attributes");
                    JSONObject location = person_info.getJSONObject(i).getJSONObject("location");
                    int top = location.getInt("top");
                    int left = location.getInt("left");
                    int width = location.getInt("width");
                    int height = location.getInt("height");
                    if (top == 0 && left == 0 && width == 0 && width == 0) {
                        return null;
                    }
                    int x = 0;
                    int y = 0;

                    //截图并写字
                    String newaiPersionFile = urlImgPath.substring(0, urlImgPath.lastIndexOf("/"));//截除新的人目录
                    String substring = urlImgPath.substring(0, urlImgPath.lastIndexOf(".")) + "-" + i + ".jpg";
                    String cut = PictureRoiCut.cut(urlImgPath, substring, top, left, width, height);
                    if (cut == null) {
                        return null;
                    }
                    //将图片放入缓存
                    BufferedImage image = ImageIO.read(new File(cut));
                    Graphics g = image.getGraphics();
                    Graphics2D g2 = (Graphics2D) g;
                    Stroke stroke = new BasicStroke(2.0f);//设置线宽为3.0
                    g2.setStroke(stroke);
                    g2.setColor(Color.blue);//画笔颜色
                    g.setFont(new Font("微软雅黑", Font.BOLD, 10));
                    FileOutputStream out = new FileOutputStream(urlImgPath);
                    String gender = attributes.getJSONObject("gender").getString("name");
                    String age = attributes.getJSONObject("age").getString("name");
                    String upper_wear = attributes.getJSONObject("upper_wear").getString("name");
                    String upper_color = attributes.getJSONObject("upper_color").getString("name");
                    String up = upper_color + "色" + upper_wear;
                    String lower_wear = attributes.getJSONObject("lower_wear").getString("name");
                    String lower_color = attributes.getJSONObject("lower_color").getString("name");
                    String down = lower_color + "色" + lower_wear;
                    //计算图片的中心位置
                    g.drawString(gender, x, y);
                    y += 20;
                    g.drawString(age, x, y);
                    y += 20;
                    g.drawString(up, x, y);
                    y += 20;
                    g.drawString(down, x, y);
                    ImageIO.write(image, "jpg", out);
                    image.flush();
                }
            }
        }
        return urlImgPath;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 画框在旁边写字
     * @CreateTime: 2020/8/21 4:41
     * @param: urlImgPath 图片位置
     * @param: bodyAttr 人的属性
     * @param: bodyAnalysis人的位置信息
     * @return: java.lang.String
     */
    public static String fromWrite(String urlImgPath, JSONObject orgJsonr, JSONObject location) {
        try {
            //将图片放入缓存
            BufferedImage image = ImageIO.read(new File(urlImgPath));
            Graphics g = image.getGraphics();
            Graphics2D g2 = (Graphics2D) g;
            Stroke stroke = new BasicStroke(2.0f);//设置线宽为3.0
            g2.setStroke(stroke);
            g2.setColor(FrameUtils.getRanColor());//画笔颜色
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));

            int top = location.getInt("top");
            int left = location.getInt("left");
            int width = location.getInt("width");
            int height = location.getInt("height");

            //给所有人画框
            g2.drawRect(left, top, width, height);//矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
            String gender = orgJsonr.getJSONObject("gender").getString("name");
            String age = orgJsonr.getJSONObject("age").getString("name");
            String upper_wear = orgJsonr.getJSONObject("upper_wear").getString("name");
            String upper_color = orgJsonr.getJSONObject("upper_color").getString("name");
            String up = upper_color + "色" + upper_wear;
            String lower_wear = orgJsonr.getJSONObject("lower_wear").getString("name");
            String lower_color = orgJsonr.getJSONObject("lower_color").getString("name");
            String down = lower_color + "色" + lower_wear;
            int one = top + 20;
            int two = top + 45;
            int three = top + 70;
            int four = top + 95;
            int x = width + left + 10;
            //判断性别是否有值
            boolean bo4 = !gender.equals("不确定");
            //判断年龄段是否有值
            boolean bo3 = !age.equals("不确定");
            //判断上身的数据是否有值
            boolean bo1 = !upper_color.equals("不确定") && !upper_wear.equals("不确定");
            //判断下身的数据是否有值
            boolean bo2 = !lower_color.equals("不确定") && !lower_wear.equals("不确定");
            if (!bo4) {
                if (!bo3) {
                    if (!bo1) {
                        if (!bo2) {
                            //全部包含不确定
                        } else {
                            g.drawString(down, x, one);
                        }
                    } else {
                        if (!bo2) {
                            g.drawString(down, x, one);
                        } else {
                            g.drawString(up, x, one);
                            g.drawString(down, x, two);
                        }
                    }
                } else {
                    if (!bo1) {
                        if (!bo2) {
                            g.drawString(age, x, one);
                        } else {
                            g.drawString(age, x, one);
                            g.drawString(down, x, two);
                        }
                    } else {
                        if (!bo2) {
                            g.drawString(age, x, one);
                            g.drawString(up, x, two);
                        } else {
                            g.drawString(age, x, one);
                            g.drawString(up, x, two);
                            g.drawString(down, x, three);
                        }
                    }
                }
            } else {
                if (!bo3) {
                    if (!bo1) {
                        if (!bo2) {
                            g.drawString(gender, x, one);
                        } else {
                            g.drawString(gender, x, one);
                            g.drawString(down, x, two);
                        }
                    } else {
                        if (!bo2) {
                            g.drawString(gender, x, one);
                            g.drawString(up, x, two);
                        } else {
                            g.drawString(gender, x, one);
                            g.drawString(up, x, two);
                            g.drawString(down, x, three);
                        }
                    }
                } else {
                    if (!bo1) {
                        if (!bo2) {
                            g.drawString(gender, x, one);
                            g.drawString(age, x, two);
                        } else {
                            g.drawString(gender, x, one);
                            g.drawString(age, x, two);
                            g.drawString(down, x, three);
                        }
                    } else {
                        if (!bo2) {
                            g.drawString(gender, x, one);
                            g.drawString(age, x, two);
                            g.drawString(up, x, three);
                        } else {
                            g.drawString(gender, x, one);
                            g.drawString(age, x, two);
                            g.drawString(up, x, three);
                            g.drawString(down, x, four);
                        }
                    }
                }
            }

            //g.dispose();
            //输出图片的地址
            FileOutputStream out = new FileOutputStream(urlImgPath);
            ImageIO.write(image, "jpg", out);
            image.flush();
            return urlImgPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

