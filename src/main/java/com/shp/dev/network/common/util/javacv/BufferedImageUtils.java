package com.shp.dev.network.common.util.javacv;

import java.awt.image.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 转换为BufferedImage工具类
 * @CreateTime: 2020/12/23 9:31
 * @PackageName: com.shp.dev.network.common.util.javacv
 * @ProjectName: network
 */
public class BufferedImageUtils {

    /**
     * 24位BGR数组转BufferedImage
     * @param src -bgr排列的24位图像像素数据数组
     * @param width -宽度
     * @param height-高度
     * @return
     */
    public static BufferedImage BGR2BufferedImage(byte[] src,int width,int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Raster ra = image.getRaster();
        DataBuffer out = ra.getDataBuffer();
        DataBufferByte db=(DataBufferByte)out;
        ByteBuffer.wrap(db.getData()).put(src,0,src.length);
        return image;
    }

    /**
     * 24位BGR字节缓冲转BufferedImage
     * @param src -bgr排列的24位图像像素数据字节缓冲
     * @param width -宽度
     * @param height-高度
     * @return
     */
    public static BufferedImage BGR2BufferedImage(ByteBuffer src,int width,int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Raster ra = image.getRaster();
        DataBuffer out = ra.getDataBuffer();
        DataBufferByte db=(DataBufferByte)out;
        ByteBuffer.wrap(db.getData()).put(src);
        return image;
    }


    /**
     * 24位整型BGR字节缓冲转BufferedImage
     * @param src -bgr排列的24位图像像素整型缓冲（int由3个byte组成）
     * @param width -宽度
     * @param height-高度
     * @return
     */
    public static  BufferedImage BGR2BufferedImage(IntBuffer src,int width,int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Raster ra = image.getRaster();
        DataBuffer out = ra.getDataBuffer();
        DataBufferInt db=(DataBufferInt)out;
        IntBuffer.wrap(db.getData()).put(src);
        return image;
    }

    /**
     * 24位整型RGB字节缓冲转BufferedImage
     * @param src --rgb排列的24位图像像素整型缓冲（int由3个byte组成）
     * @param width -宽度
     * @param height-高度
     * @return
     */
    public static  BufferedImage RGB2BufferedImage(IntBuffer src,int width,int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Raster ra = image.getRaster();
        DataBuffer out = ra.getDataBuffer();
        DataBufferInt db=(DataBufferInt)out;
        IntBuffer.wrap(db.getData()).put(src);
        return image;
    }


}
