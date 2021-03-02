package com.shp.dev.network.common.util;

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
