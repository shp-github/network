package com.shp.dev.network.common.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @CreateBy: shp
 * @version：1.0
 * @Description: TODO
 * @CreateTime: 2020/6/28 17:06
 */
@Component
public class ShpUtils {


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 返回类中为空值的属性
     * @CreateTime: 2020/6/29 15:41
     * @param: obj
     * @return: java.lang.String
     */
    public String isNullByAttribute(Object obj) {
        if (obj == null) {
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) {
                    return field.getName() + " 的属性值不能为空";
                }
            } catch (IllegalAccessException e) {
                return "发生异常情况！！！";
            }
        }
        return null;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO Object转Map不put进去空值
     * @CreateTime: 2020/6/29 15:41
     * @param: obj
     * @return: Map<java.lang.Object, java.lang.Object>
     */
    public Map<Object, Object> screenNullToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<Object, Object> map = new HashMap();
        Field[] fields = obj.getClass().getDeclaredFields();
        Object o;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                o = field.get(obj);
                if (noNull(o)) {
                    map.put(field.getName(), field.get(obj));
                }
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return map;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 把Object中的空值替换成空字符串
     * @CreateTime: 2020/6/29 15:41
     * @param: obj
     * @return: java.lang.Object
     */
    public Object screenNullToObj(Object obj) {
        if (obj == null) {
            return obj;
        }
        Object o;
        String name;
        Map<String, Object> map = new HashMap();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                o = field.get(obj);
                if (noNull(o)) {
                    name = field.getName();
                    map.put(name, o);
                }
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return map2Obj(map, Object.class);
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO Object转Map
     * @CreateTime: 2020/6/29 11:56
     * @param: obj
     * @return: Map<java.lang.String, java.lang.Object>
     */
    public Map<Object, Object> obj2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<Object, Object> map = new LinkedHashMap();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return map;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO Map转Object
     * @CreateTime: 2020/8/31 16:55
     * @param: map
     * @param: clz 对象类型例如：Student.class
     * @return: java.lang.Object
     */
    public Object map2Obj(Map<String, Object> map, Class<?> clz) {
        if (map == null) {
            return map;
        }
        if (clz == null) {
            return clz;
        }
        Object obj;
        try {
            obj = clz.newInstance();
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            return null;
        }
        return obj;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 判如果为空返回true
     * @CreateTime: 2020/6/29 17:15
     * @param: o
     * @return: boolean
     */
    public static boolean isNull(Object o) {
        return o == null || o.equals("") || o.equals("null") || o.equals("NaN") || o.equals("undefined");
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 如果不为空返回true
     * @CreateTime: 2020/6/29 17:15
     * @param: o
     * @return: boolean
     */
    public static boolean noNull(Object o) {
        return o != null && !o.equals("") && !o.equals("null") && !o.equals("NaN") && !o.equals("undefined");
    }

}
