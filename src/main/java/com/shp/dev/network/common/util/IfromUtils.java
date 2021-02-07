package com.shp.dev.network.common.util;


/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 嵌套网页
 * @CreateTime: 2020/7/26 9:15
 * @PackageName: com.shp.dev.network.common.util
 * @ProjectName: network
 */
public class IfromUtils {



    public static String toIfrom(String uri){
        StringBuilder builder=new StringBuilder();
        builder.append("<!DOCTYPE html>");
        builder.append("<html lang=\"zh\">");
        builder.append("\t<head>");
        builder.append("\t</head>");
        builder.append("\t<body  bgcolor=\"#FFFFCC\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
        builder.append("\t\t<iframe ");
        builder.append("\t\t  id=\"iframeId\"");
        builder.append("\t\t  scrolling=\"no\"");
        builder.append("\t\t  framespacing=\"0\"");
        builder.append("\t\t  vspace=\"0\"");
        builder.append("\t\t  hspace=\"0\" ");
        builder.append("\t\t  frameborder=\"0\"");
        builder.append("\t\t  width=\"100%\" ");
        builder.append("\t\t  height=\"3000px\"");
        builder.append("\t\t  src=\""+uri+"\">");
        builder.append("\t\t</iframe>");
        builder.append("\t</body>");
        builder.append("</html>");
        return builder.toString();
    }

}
