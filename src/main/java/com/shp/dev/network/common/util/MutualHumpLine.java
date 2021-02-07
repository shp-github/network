package com.shp.dev.network.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/9/24 11:27
 * @PackageName: com.shp.dev.network.common.util.jdbc
 * @ProjectName: network
 */
public class MutualHumpLine {

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static void main(String[] args) {
        System.out.println(humpToLineDown("userId"));
    }

    /**
     * 驼峰转下划线,最后转为小写
     * @param str
     * @return
     */
    public static String humpToLineDown(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString().toLowerCase();
    }

    /**
     * 驼峰转下划线,最后转为小写
     * @param str
     * @return
     */
    public static String humpToLineUp(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString().toLowerCase();
    }

    /**
     * 下划线转驼峰,正常输出
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
