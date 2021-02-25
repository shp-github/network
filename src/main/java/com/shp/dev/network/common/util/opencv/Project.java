package com.shp.dev.network.common.util.opencv;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTimF: 2021/2/23 18:53
 * @PackageNamF: com.shp.dev.network.common.util.opencv
 * @ProjectNamF: network
 */
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Project{

    //下面都是文件src，根据自己的实际情况填写

    public static String posFilePath = "F:\\face\\pos";//正面例子源文件夹
    public static String posOutPath = "F:\\face\\posOut";//正面例子输出文件夹
    public static String posTxtPath = "F:\\face\\pos.txt";//正面例子信息txt文件
    public static String negFilePath = "F:\\face\\neg";//反面例子源文件夹
    public static String NegOutPath = "F:\\face\\negOut";//反面例子输出文件夹
    public static String negTxtPath = "F:\\face\\neg.txt";//反面例子信息txt文件

    //修改文件大小为20*20  可自定义设置，根据opencv官方推荐20*20效果最佳，
    //个人建议最大不要超过40*40，太大的话跑不太动
    public static int width = 20 ;
    public static int height = 20 ;

    //导入opencv库
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void renameFiles(String filePath){
        File file = new File(filePath);
        File[] files = file.listFiles();
        int i = 0;
        for (File f : files){
            f.renameTo(new File(filePath+"\\"+i+".jpg"));
            i++;
        }
    }

    //获取img和txt的过程
    /*
     * 1、对图片缩放至20*20
     * 2、对图片进行灰度处理
     * 3、获取Txt
     * */
    public static void getPosImgAndTxt() throws IOException {
        File infile = new File(posFilePath);//获取正面例子的文件夹
        File outfile = new File(posOutPath);//缩放和灰度处理后的例子存放文件夹
        File txtFile = new File(posTxtPath);//txt信息文件的文件位置

        if (txtFile.isDirectory()){
            System.out.println("txt文件别忘了在文件夹地址后加上文件名");
            return;
        }
        if (!txtFile.exists()){
            txtFile.createNewFile();
            System.out.println("文件不存在，自动创建完毕");
        }
        if (!outfile.exists()){
            outfile.mkdir();
            System.out.println("输出路径不存在，自动创建完毕");
        }
        if (!infile.isDirectory()){
            System.out.println("请确保参数posFilePath为文件夹路径");
        }else if (infile.isDirectory()){
            File[] files = infile.listFiles();
            FileOutputStream fileOutputStream = new FileOutputStream(txtFile);
            PrintWriter printWriter = new PrintWriter(fileOutputStream,true);

            for (File file :files){
                Mat mat = Imgcodecs.imread(file.getPath());

                //缩放
                Imgproc.resize(mat,mat,new Size(width,height),0,0,Imgproc.INTER_CUBIC);

                //灰度处理
                Imgproc.cvtColor(mat,mat,Imgproc.COLOR_BGR2GRAY,0);

                //获取图片
                Imgcodecs.imwrite(outfile+"\\"+file.getName(),mat);

                //添加文件信息到txt文件中
                printWriter.println(outfile.getPath()+"\\"+file.getName()+" 1 0 0 20 20");
            }
            printWriter.close();
            fileOutputStream.close();
        }
    }
    //获取反面img和txt的过程
    /*
     *1、对图片进行灰度处理
     *2、获取txt
     * */
    public static void getNegImgAndTxt() throws IOException {
        File infile = new File(negFilePath);//获取反面例子的文件夹
        File outfile = new File(NegOutPath);//灰度处理后的例子存放文件夹
        File txtFile = new File(negTxtPath);//txt信息文件的文件位置
        if (txtFile.isDirectory()){
            System.out.println("txt文件别忘了在文件夹地址后加上文件名");
            return;
        }
        if (!txtFile.exists()){
            txtFile.createNewFile();
            System.out.println("文件不存在，自动创建完毕");
        }
        if (!outfile.exists()){
            outfile.mkdir();
            System.out.println("输出路径不存在，自动创建完毕");
        }
        if (!infile.isDirectory()){
            System.out.println("请确保参数posFilePath为文件夹路径");
        }else if (infile.isDirectory()){
            File[] files = infile.listFiles();
            FileOutputStream fileOutputStream = new FileOutputStream(txtFile);
            PrintWriter printWriter = new PrintWriter(fileOutputStream,true);

            for (File file :files){
                Mat mat = Imgcodecs.imread(file.getPath());

                //灰度处理
                Imgproc.cvtColor(mat,mat,Imgproc.COLOR_BGR2GRAY,0);

                //获取图片
                Imgcodecs.imwrite(outfile+"\\"+file.getName(),mat);

                //添加文件信息到txt文件中
                printWriter.println(outfile.getPath()+"\\"+file.getName());
            }
            printWriter.close();
            fileOutputStream.close();
        }
    }

    public static void main(String[] args) throws IOException {

        /*
         * 如果有需要，可以调用重命名函数，因为图像文件名中是
         * 不可以有汉字的我的例子图像好多都是通过qq截图截的，其中含有“截图”这两个字，
         * 所以被迫写一个重命名的函数，我把图像的名字都改成了数字
         *  重命名函数已经写好，可以直接调用
         * renameFiles(参数);
         * 参数路径中不要有不是作为目标检测素材的其他文件，因为如果有非图像文件
         * 需要的参数是文件夹路径，会将整个文件夹中的文件重命名为  数字.jpg 的格式
         *
         * */
        getPosImgAndTxt();
        getNegImgAndTxt();
    }












}
