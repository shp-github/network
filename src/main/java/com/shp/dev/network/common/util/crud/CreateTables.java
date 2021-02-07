package com.shp.dev.network.common.util.crud;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 根据java的实体类，创建对应的表
 * @CreateTime: 2020-07-05 0:07
 * @PackageName: com.shp.dev.network.common.util
 * @ProjectName: network
 */

public class CreateTables {

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO  数据库的连接信息
     * @CreateTime: 2020-07-05 0:09
     */
    private static final String url = "jdbc:mysql://localhost:3306/network?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";        //user 数据库用户名
    private static final String password = "root";    //password 数据库密码
    private static Map<String, String> map = new HashMap<>();//java类型对应mysql中的类型

    //Java中类型对应mysql中的类型
    static {
        map.put("Integer", "int(0)");
        map.put("int", "int(0)");
        map.put("long", "bigint(0)");
        map.put("Long", "bigint(0)");
        map.put("double", "double(16,2)");
        map.put("Double", "double(16,2)");
        map.put("boolean", "bit(1)");
        map.put("Boolean", "bit(1)");
        map.put("float", "float");
        map.put("Float", "float");
        map.put("Date", "datetime");
        map.put("List", "json");
        map.put("string", "varchar(255)");
        map.put("String", "varchar(255)");
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 需要传入对象集合,然后会调用写入数据库和反射获取类属性的操作。
     * @CreateTime: 2020/8/3 15:00
     * @param: list
     * @return: void
     */
    private static void forCreateTables(List list) {
        for (Object o : list) {
            goCreate(o, humpToLine(getConvert(o.getClass().toString().substring(o.getClass().toString().lastIndexOf(".") + 1))));
        }
    }

    private static void forCreateTables(Object[] obj) {
        for (Object o : obj) {
            goCreate(o, humpToLine(getConvert(o.getClass().toString().substring(o.getClass().toString().lastIndexOf(".") + 1))));
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 根据对象和表名，生成sql，连接mysql执行sql语句创建表
     * @CreateTime: 2020/8/1 15:52
     * @param: obj 表对应的实体类
     * @param: tableName
     * @return: void
     */
    private static void goCreate(Object obj, String tableName) {
        Statement st = null;
        Connection conn = null;
        try {
            String sql = getTablesSql(obj, tableName).toString();
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
            int row = st.executeUpdate(sql);
            System.out.println(row == 0 ? "创建成功！" : "可能出现了异常\n");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 拼接获取创建表的sql语句
     * @CreateTime: 2020/8/3 15:01
     * @param: obj 需要转换成创建表的类。
     * @param: tableName 表名
     * @return: java.lang.StringBuilder 返回创建表的sql语句
     */
    private static StringBuilder getTablesSql(Object obj, String tableName) {
        StringBuilder builder = new StringBuilder();
        //builder.append("DROP TABLE IF EXISTS `" + tableName + "`;\n");
        builder.append("CREATE TABLE IF NOT EXISTS `" + tableName + "`(\n");
        Field[] field = obj.getClass().getDeclaredFields();
        builder.append("`" + humpToLine(field[0].getName()) + "` INT UNSIGNED AUTO_INCREMENT,\n");
        for (int i = 1; i < field.length; i++) {
            builder.append("`" + humpToLine(field[i].getName()) + "` ");
            //根据实体类的类型来转换为数据库中的类型
            try {
                //获取实体类对应数据库的类型
                builder.append(map.get(cut(field[i].getType().toString())));
            } catch (Exception e) {
                //如果没有对应的类型则会存json类型
                builder.append(map.get("List"));
            }
            builder.append(",\n");
        }
        StringBuilder sql = builder.append(" PRIMARY KEY (`" + humpToLine(field[0].getName()) + "`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");
        //打印sql语句
        System.out.println("\n\n" + sql.toString());
        return sql;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 取点"."后面的后缀不包含点"."
     * @CreateTime: 2020/8/3 15:25
     * @param: str
     * @return: java.lang.String
     */
    private static String cut(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    /**
     * @param: str
     * @return: java.lang.String
     * @Description: TODO 驼峰转下划线
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 将字符串的首字母转小写
     * @CreateTime: 2020/9/3 11:40
     * @param: str
     * @return: java.lang.String
     */
    public static String getConvert(String str) {
        String first = str.substring(0, 1);
        String after = str.substring(1);
        first = first.toLowerCase();
        return first + after;
    }


}
