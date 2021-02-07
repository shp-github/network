package com.shp.dev.network.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 时间工具类
 * @CreateTime: 2020/9/15 14:00
 * @PackageName: com.vimochina.vimo.util.ai
 * @ProjectName: wisdomeyeapi0114
 */
public class DateUtils {


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 获取当前年月日时分秒字符串格式
     * @CreateTime: 2021/1/7 23:13
     * @param:
     * @return: java.lang.String
     */
    public static String getDate(){
        return getCustomizeDateTime("yyyy/MM/dd");
    }

    public static String getDateTime(){
        return getCustomizeDateTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCustomizeDateTime(String pattern){
        return new SimpleDateFormat(pattern).format(new Date());
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 传入时间和时间格式
     * @CreateTime: 2020/9/15 14:02
     * @param: str
     * @param: date
     * @return: java.lang.String
     * DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")
     */
    public static String format(Date date,String str){
        try{
            return new SimpleDateFormat(str).format(new Date());
        }catch (Exception e){
            return null;
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 当天时间 字符串时分秒 转秒
     * @CreateTime: 2020/9/15 14:06
     * @param: time
     * @return: int
     * DateUtils.getSecond("24:00:00")
     */
    public static Integer getSecond(String time){
        try{
            String[] my =time.split(":");
            return Integer.parseInt(my[0])*3600+Integer.parseInt(my[1])*60+Integer.parseInt(my[2]);
        }catch (Exception e){
            return null;
        }
    }


}
