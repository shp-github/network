//package com.shp.dev.network.common.util.javacv;
//
//
//import org.bytedeco.javacv.*;
//import org.junit.Test;
//
//import javax.swing.*;
//
///**
// * @CreateBy: shp
// * @Version: 1.0
// * @Description: TODO 调用摄像头
// * @CreateTime: 2020/12/23 9:18
// * @PackageName: com.shp.dev.network.common.util.javacv
// * @ProjectName: network
// */
//public class CameraUtils {
//
//    static Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter();
//
//    /**
//     * @CreateBy: shp
//     * @version：1.0
//     * @Description: TODO 拉去rtsp摄像头
//     * @CreateTime: 2020/12/23 14:50
//     * @param:
//     * @return: void
//     */
//    @Test
//    public void RTSPCamera() throws FrameGrabber.Exception {
//        String rtspUrl = "rtsp://admin:a1234567@111.61.67.59:554/h264/ch1/main/av_stream";
//        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(rtspUrl);
//        grabber.setOption("rtsp_transport", "tcp"); // 使用tcp的方式，不然会丢包很严重
//        grabber.start();
//        System.out.println("连接摄像头成功");
//        CanvasFrame canvasFrame = new CanvasFrame("监控画面");
//        canvasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        canvasFrame.setAlwaysOnTop(true);
//        while (true) {
//            Frame frame = grabber.grabImage();
//            canvasFrame.showImage(frame);
//            //BufferedImage bufferedImage = java2DFrameConverter.getBufferedImage(frame);
//            //canvasFrame.showImage(bufferedImage);
//        }
//    }
//
//
//    /**
//     * @CreateBy: shp
//     * @version：1.0
//     * @Description: TODO 获取本地摄像头画面
//     * @CreateTime: 2020/12/23 14:51
//     * @param:
//     * @return: void
//     */
//    @Test
//    public void localCamera2() throws FrameGrabber.Exception, InterruptedException {
//        VideoInputFrameGrabber grabber = VideoInputFrameGrabber.createDefault(0);
//        grabber.start();
//        CanvasFrame canvasFrame = new CanvasFrame("摄像头");
//        canvasFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        canvasFrame.setAlwaysOnTop(true);
//        while (true) {
//            if (!canvasFrame.isDisplayable()) {
//                grabber.stop();
//                System.exit(-1);
//            }
//            Frame frame = grabber.grab();
//            canvasFrame.showImage(frame);
//            Thread.sleep(30);
//        }
//    }
//
//
//    /**
//     * @CreateBy: shp
//     * @version：1.0
//     * @Description: TODO 获取本地摄像头画面
//     * @CreateTime: 2020/12/23 14:51
//     * @param:
//     * @return: void
//     */
//    @Test
//    public void localCamera() throws FrameGrabber.Exception, InterruptedException {
//        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
//        grabber.start();   //开始获取摄像头数据
//        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
//        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        canvas.setAlwaysOnTop(true);
//
//        while (true) {
//            if (!canvas.isDisplayable()) {//窗口是否关闭
//                grabber.stop();//停止抓取
//                System.exit(2);//退出
//            }
//            canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
//
//            Thread.sleep(50);//50毫秒刷新一次图像
//        }
//    }
//
//}
