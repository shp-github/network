package com.shp.dev.network.common.util.exprot;


import com.shp.dev.network.common.util.exprot.api.WordGeneratorWithFreemarker;

import java.util.HashMap;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/10/29 22:54
 * @PackageName: org.java.export.plugin.example
 * @ProjectName: ExportWordPlugin
 */
public class ExportExample {

    public static void main(String[] args) throws Exception {

        //给模板对应的key赋值
        HashMap<String, Object> data = new HashMap();
        data.put("name","巴啦啦小魔仙");

        String docFilePath = "e:\\out.doc";
        String templatePath = Class.class.getResource("/ftl").getPath();
        templatePath = java.net.URLDecoder.decode(templatePath, "utf-8");//这里我的路径有空格添加此处理
        WordGeneratorWithFreemarker.createDoc(templatePath, "export.ftl", data, docFilePath);

        System.out.println("导出成功!!!\n文件地址:"+docFilePath);
    }
}
