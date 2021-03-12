package com.shp.dev.network.common.util.take;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 爬取视频
 * @CreateTime: 2020/11/18 0:49
 * @PackageName: com.shp.dev.network.common.util.take
 * @ProjectName: network
 */
public class TaskVideoUtils {


    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://www.zxzj.me/video/3030-1-1.html").get();        //获取页面文档
        Element body = doc.body();
        Element child = body.child(1).child(0).child(0).child(0).child(0).child(0).child(0);
        Elements divList = child.select("video"); //获取a标签
        for (int i = 0; i < divList.size(); i++) {
            System.out.println(divList.get(i));
        }

    }

}
