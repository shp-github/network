package com.shp.dev.network.common.util;

import com.shp.dev.network.common.util.jdbc.JDBCUtils;
import org.osgl.Lang;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 从集合中过滤数据
 * @CreateTime: 2020/10/31 11:13
 * @PackageName: com.shp.dev.network.common.util
 * @ProjectName: network
 */
public class ListStremUtils {
    public static void main(String[] args) {

        String sql="select * from sys_user";
        List<Map> list = JDBCUtils.queryList(sql);
        for (Map map : list) {
            System.out.println(map.get("username"));
        }

        Map<Object,Object> whiteMap = new HashMap();

        list.stream()
                //list转成map 第一个参数是组成的key 第二个是每个key对应的value  Lang.Tuple(key,vlue)
                .map(map -> Lang.Tuple(map.get("username")+"_"+map.get("age"),map))
                .forEach(tuple ->
                        //遍历这个map集合 重新put到whiteMap中
                        whiteMap.put(tuple._1, tuple._2));

        for (Map.Entry<Object,Object> entry : whiteMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyyMMddHHmmss").appendValue(ChronoField.MILLI_OF_SECOND, 3).toFormatter();
        System.out.println(dateTimeFormatter);

        //年龄为18的人
        List age18=new ArrayList();
        //用户为男性
        List manList=new ArrayList();
        //用户为女性
        List womanList=new ArrayList();

        List<Object[]> objects=list.stream()
                //过滤集合中的数据
                .filter(map -> {
                    Integer age = Integer.valueOf(map.get("age").toString());
                    if(age!=18){
                        return true;
                    }
                    age18.add(map);
                    return false;
                })
                .filter(map -> {
                    if(map.get("sex").toString().equals("男")){
                        manList.add(map);
                        return true;
                    }
                    if(map.get("sex").toString().equals("女")){
                        womanList.add(map);
                        return true;
                    }
                    return false;
                    //new 一个对象数组取出某个字段的参数，添加到集合中。
                }).map(map -> new Object[]{
                        map.get("username"),
                        map.get("sex"),
                        map.get("age")
                }).collect(Collectors.toList());

        //System.out.println("遍历从list流中过滤出的数据。");
        //for (Object[] object : objects) {
        //    for (Object o : object) {
        //        System.out.print(o+"  ");
        //    }
        //    System.out.println();
        //}

        System.out.println("遍历从list流中过滤出的数据。");
        objects.forEach(objs  -> {
            for (Object obj : objs) {
                System.out.print(obj+"\t");
            }
            System.out.println();
        });

        System.out.println("年龄为18的数据");
        age18.forEach(System.out::println);
        System.out.println("性别为男性的数据");
        manList.forEach(System.out::println);
        System.out.println("性别为女性的数据");
        womanList.forEach(System.out::println);

    }
}
