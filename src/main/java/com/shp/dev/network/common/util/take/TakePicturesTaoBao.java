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
public class TakePicturesTaoBao {


    public static void main(String[] args) throws Exception{

        String url="https://uland.taobao.com/sem/tbsearch?refpid=mm_26632258_3504122_32538762&keyword=%E7%94%B7%E5%86%AC%E5%A5%97%E8%A3%85&clk1=17fd5ea388b5724d4a8c7fb9e5d05778&upsid=17fd5ea388b5724d4a8c7fb9e5d05778&spm=a2e0b.20350158.31919782.1&pid=mm_26632258_3504122_32538762&union_lens=recoveryid%3A201_11.23.123.50_14524470_1606486484972%3Bprepvid%3A201_11.11.100.114_14530036_1606486495487&pnum=";

        for (int i = 0; i < 10; i++) {
            String str=url+i;
            Document doc = Jsoup.connect(str).get();        //获取页面文档
            Element body = doc.body();
            Element child = body.child(6);
            Elements imgs = child.select("img"); //获取a标签
            for (Element img : imgs) {
                Element src1 = img.after("src");
                System.out.println(src1);
            }

        }



    }



    public static void main1(String[] args) {

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
