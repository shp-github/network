package com.shp.dev.network.common.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 读取txt文件
 * @CreateTime: 2020/8/25 23:14
 * @PackageName: com.shp.dev.network.common.util
 * @ProjectName: network
 */
public class ReadTxt {

    public static void main(String[] args) {

        write();
    }

    public static String write() {

        String str = "package com.shp.dev.network.common.bean;\n" +
                "\n" +
                "public class C {\n" +
                "    private String b;\n" +
                "}\n";

        //src.main.java.com.shp.dev.network.common.util

        System.out.println("----------------开始写新日志----------------");

        try {
            File newlog = new File("/aaaaaaaaaa.java");
            if (!newlog.isFile()) {
                newlog.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newlog), "utf-8"));

            bw.write(str + "\r\n");

            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
