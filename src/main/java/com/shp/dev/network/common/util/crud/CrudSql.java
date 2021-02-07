package com.shp.dev.network.common.util.crud;


import com.shp.dev.network.sql.model.SysSql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 生成简单的增删改查sql语句
 * @CreateTime: 2020-07-05 0:33
 * @PackageName: com.shp.dev.network.common.util
 * @ProjectName: network
 */


public class CrudSql {

    public static void main(String[] args) {


        List list=new ArrayList<>();
        list.add(new SysSql());
        forCrudSql(list);


    }



    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 传入对象集合，打印出对象的crdu sql语句
     * @CreateTime: 2020/8/11 23:52
     * @param: list
     * @return: void
     */
    private static void forCrudSql(List list) {
        for (Object o : list) {
            System.out.println(o.getClass().toString().substring(o.getClass().toString().lastIndexOf(".") + 1) + ":-----------------------------");
            String[] caseinputs = getField(o, o.getClass().toString().substring(o.getClass().toString().lastIndexOf(".") + 1));
            for (String caseinput : caseinputs) {
                System.out.println(caseinput);
            }
        }
    }


    /**
     * 反射获取类中的属性
     *
     * @return
     */

    public static String[] getProPertis(Object o) {
        Field[] declaredFields = o.getClass().getDeclaredFields();//获得属性
        String[] arrs = new String[declaredFields.length];//定义一个和declaredFields长度相同的数组，储存类中的属性
        for (int i = 0; i < declaredFields.length; i++) {//遍历数组获取所有属性
            arrs[i] = declaredFields[i].toString().substring(declaredFields[i].toString().lastIndexOf(".") + 1);
        }
        return arrs;
    }


    /**
     * 通过反射获取类中的属性
     *
     * @param o     实体类
     * @param table 表名
     * @return
     */
    private static String[] getField(Object o, String table) {
        Field[] declaredFields = o.getClass().getDeclaredFields();//获得属性
        //用 ` 符号包裹防止表名为关键字
        table = "`" + humpToLine(table).substring(humpToLine(table).indexOf("_") + 1) + "`";
        String[] arrs = new String[declaredFields.length];//定义一个和declaredFields长度相同的数组，储存类中的属性
        for (int i = 0; i < declaredFields.length; i++) {//遍历数组获取所有属性
            arrs[i] = declaredFields[i].toString().substring(declaredFields[i].toString().lastIndexOf(".") + 1);
        }
        return crudToString(arrs, table);
    }


    /**
     * 把增删改查组合
     *
     * @param table 表名
     * @param arr   字段数组
     * @return
     */
    public static String[] crudToString(String[] arr, String table) {
        String[] arrs = new String[4];
        arrs[0] = add(arr, table);
        arrs[1] = delete(table, arr);
        arrs[2] = edit(arr, table);
        arrs[3] = query(arr, table);
        return arrs;
    }


    /**
     * @param arr   表中字段
     * @param table 表名
     * @return
     */
    public static String add(String[] arr, String table) {
        StringBuffer addBuffer = new StringBuffer("INSERT INTO " + table + "(");
        for (int i = 1; i < arr.length; i++) {
            addBuffer.append("`" + humpToLine(arr[i]) + "`,");
        }
        addBuffer.delete(addBuffer.length() - 1, addBuffer.length());//删除最后一个逗号
        addBuffer.append(")");
        addBuffer.append(" VALUES(");
        for (int i = 1; i < arr.length; i++) {
            addBuffer.append("#{" + arr[i] + "},");
        }
        addBuffer.delete(addBuffer.length() - 1, addBuffer.length());//删除最后一个逗号
        addBuffer.append(")");
        return addBuffer.toString();
    }

    /**
     * @param arr   表中字段
     * @param table 表名
     * @return
     */
    public static String edit(String[] arr, String table) {
        StringBuffer editBuffer = new StringBuffer("UPDATE " + table + " SET ");
        for (int i = 1; i < arr.length; i++) {
            editBuffer.append("`" + humpToLine(arr[i]) + "`=#{" + arr[i] + "},");
        }
        editBuffer.delete(editBuffer.length() - 1, editBuffer.length());
        editBuffer.append(" WHERE " + arr[0] + "=#{" + arr[0] + "}");
        return editBuffer.toString();
    }

    /**
     * @param arr   表中字段
     * @param table 表名
     * @return
     */
    public static String delete(String table, String[] arr) {
        return "DELETE FROM " + table + " WHERE `" + humpToLine(arr[0]) + "`=#{" + arr[0] + "}";
    }

    /**
     * @param arr   表中字段
     * @param table 表名
     * @return
     */
    public static String query(String[] arr, String table) {
        StringBuffer queryBuffer = new StringBuffer("SELECT ");
        for (String s : arr) {
            queryBuffer.append("`" + humpToLine(s) + "` " + s + ",");
        }
        queryBuffer.delete(queryBuffer.length() - 1, queryBuffer.length());
        queryBuffer.append(" FROM " + table);
        return queryBuffer.toString();
    }


    /**
     * @param: str
     * @return: java.lang.String
     * @Description: TODO 驼峰转下划线
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

}