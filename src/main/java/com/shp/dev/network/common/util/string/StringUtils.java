package com.shp.dev.network.common.util.string;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 操作字符串工具类
 * @CreateTime: 2020/12/10 11:19
 * @PackageName: com.tieta.ai.common.utils
 * @ProjectName: ai
 */
public class StringUtils {


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 写出文本
     * @CreateTime: 2020/12/22 17:52
     * @param:
     * @return: void
     */
    public static String writeTxt(String filePath, String str) {
        return writeTxt(new File(filePath), str);
    }

    public static String writeTxt(File file, String str) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(str);
            pw.flush();
            fw.flush();
            pw.close();
            fw.close();
            return file.getPath();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 去除字符串中的空格、回车、换行符、制表符
     * @CreateTime: 2020/8/26 21:29
     * @param: str
     * @return: java.lang.String
     */
    public static String replaceBlank(String str) {
        String dest = "";
        try {
            if (str != null) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(str);
                dest = m.replaceAll("");
                return dest;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }


}
