package com.shp.dev.network.common.util.take;

import com.shp.dev.network.common.util.file.UploadURLFileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 爬取图片
 * @CreateTime: 2020/11/15 13:30
 * @PackageName: com.shp.dev.network.common.util
 * @ProjectName: network
 */
public class TakePicturesUtils {

    public static void main(String[] args) {

        List<String> urls = getUrl("http://www.nipic.com/photo");
        for (String url : urls) {
            getImages(url);
        }

        System.out.println("全部执行完毕");

    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 获取该地址下的所有网页
     * @CreateTime: 2020/11/15 13:57
     * @param: url
     * @return: void
     */
    public static List<String> getUrl(String url) {
        List<String> list = new ArrayList();
        try {
            int i = 0;
            Document doc = Jsoup.connect(url).get();        //获取页面文档
            Element body = doc.body();
            Element child = body.child(1).child(0).child(0).child(1);
            Elements divList = child.select("a"); //获取a标签
            for (Element element : divList) {
                String href = element.attr("href");
                href = "http://www.nipic.com" + href;
                list.add(href);
            }
            System.out.printf("一共获取到：" + i + "个网页信息");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 下载某个地址下所有图片
     * @CreateTime: 2020/11/15 13:43
     * @param: url
     * @return: void
     */
    public static void getImages(String url) {
        try {
            int i = 0;
            int error = 0;
            int sucesses = 0;
            Document doc = Jsoup.connect(url).get();        //获取页面文档
            Elements divList = doc.body().select("img"); //获取所有图片信息
            for (Element element : divList) {
                i++;
                String src = element.attr("data-src");
                File dirFile = new File("/image/");
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                String s = UploadURLFileUtils.saveFile(src, "/image/" + System.currentTimeMillis() + "_" + i + ".jpg");
                System.out.println("执行第" + i + "次\t图片地址:" + s);
                if (s != null) {
                    sucesses++;
                } else {
                    error++;
                }
            }
            System.out.println("失败：" + error);
            System.out.println("成功：" + sucesses);
            System.out.printf("一共：" + i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
