package com.shp.dev.network.common.util.jdbc;

import com.alibaba.fastjson.JSON;
import com.shp.dev.network.common.config.Constant;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;


/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO jdbc工具类
 * @CreateTime: 2020/9/22 23:03
 * @PackageName: com.shp.dev.network.common.util.jdbc
 * @ProjectName: network
 */
@NoArgsConstructor
public class JDBCUtils<T> {

    static Connection connection = null;
    static PreparedStatement prepareStatement = null;
    static ResultSet rs = null;

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 返回对象
     * @CreateTime: 2020/9/24 11:13
     * @param: sql sql语句
     * @param: clz 返回数据类型
     * @param: obj 参数可以没有，也可以多个参数用逗号隔开，可以是任意类型，但是要和sql语句中‘？’对应
     * @return: java.util.List<T>
     */

    public Object queryObject(String sql, Class<?> clz, Object... obj) {
        try {
            return queryList(sql, clz, obj).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 返回数据集合
     * @CreateTime: 2020/9/24 11:13
     * @param: sql sql语句
     * @param: clz 返回数据类型
     * @param: obj 参数可以没有，也可以多个参数用逗号隔开，可以是任意类型，但是要和sql语句中‘？’对应
     * @return: java.util.List<T>
     */
    public List<T> queryList(String sql, Class<?> clz, Object... obj) {
        if (sql == null) {
            return null;
        }
        List<T> row = new ArrayList();
        Map map = new HashMap();
        try {
            // 加载驱动
            Class.forName(Constant.MYSQL_DRIVER);
            // 获取连接
            try {
                connection = DriverManager.getConnection(Constant.MYSQL_URL, Constant.MYSQL_USERNAME, Constant.MYSQL_PASSWORD);
            } catch (Exception e) {
                new RuntimeException("连接mysql失败");
            }

            prepareStatement = connection.prepareStatement(sql);
            //设置参数 第一个参数  参数值
            for (int i = 0; i < obj.length; i++) {
                prepareStatement.setObject(i + 1, obj[i]);
            }
            // 执行查询
            rs = prepareStatement.executeQuery();
            // 处理结果集
            while (rs.next()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();//获取列数
                Field[] fields = clz.getDeclaredFields();//获得属性
                columnCount = columnCount < fields.length ? columnCount : fields.length;
                for (int i = 0; i < columnCount; i++) {
                    map.put(fields[i].getName(), rs.getString(metaData.getColumnName(i + 1)));
                }
                row.add((T) JSON.parseObject(JSON.toJSONString(map), clz));
            }
        } catch (Exception e) {
            throw new RuntimeException("参数可能不对应");
        } finally {
            try {
                // 关闭连接，释放资源
                if (rs != null) {
                    rs.close();
                }
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
        return row;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 返回数据集合 --不需要指定对象类型和泛型
     * @CreateTime: 2020/9/24 11:13
     * @param: sql sql语句
     * @param: obj 参数可以没有，也可以多个参数用逗号隔开，可以是任意类型，但是要和sql语句中‘？’对应
     * @return: java.util.List
     */
    public static List queryList(String sql, Object... obj) {
        if (sql == null) {
            return null;
        }
        List row = new ArrayList();
        Map map;
        try {
            try {
                // 加载驱动
                Class.forName(Constant.MYSQL_DRIVER);
                // 获取连接
                connection = DriverManager.getConnection(Constant.MYSQL_URL, Constant.MYSQL_USERNAME, Constant.MYSQL_PASSWORD);
            } catch (Exception e) {
                System.out.println("连接mysql失败");
                System.err.println(e);
                return null;
            }
            prepareStatement = connection.prepareStatement(sql);
            //设置参数 第一个参数  参数值
            for (int i = 0; i < obj.length; i++) {
                prepareStatement.setObject(i + 1, obj[i]);
            }
            // 执行查询
            rs = prepareStatement.executeQuery();
            // 处理结果集
            while (rs.next()) {
                ResultSetMetaData metaData = rs.getMetaData();
                map = new HashMap<>();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    map.put(metaData.getColumnName(i + 1), rs.getString(metaData.getColumnName(i + 1)));
                }
                row.add(map);
            }
        } catch (Exception e) {
            throw new RuntimeException("参数可能不对应"+e.getMessage());
        } finally {
            try {
                // 关闭连接，释放资源
                if (rs != null) {
                    rs.close();
                }
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                return null;
            }
        }
        return row;
    }

    public static Integer sqlFactory(String sql, Object... obj) {
        if (sql == null) {
            return 0;
        }
        try {
            // 加载驱动
            Class.forName(Constant.MYSQL_DRIVER);
            try {
                // 获取连接
                connection = DriverManager.getConnection(Constant.MYSQL_URL, Constant.MYSQL_USERNAME, Constant.MYSQL_PASSWORD);
            } catch (Exception e) {
                System.out.println("连接失败");
                System.err.println(e.getMessage());
            }

            prepareStatement = connection.prepareStatement(sql);
            //设置参数 第一个参数  参数值
            for (int i = 0; i < obj.length; i++) {
                prepareStatement.setObject(i, obj[i]);
            }
            // 执行查询
            return prepareStatement.executeUpdate();
        } catch (Exception e) {
            return 0;
        } finally {
            try {
                // 关闭连接，释放资源
                if (rs != null) {
                    rs.close();
                }
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
    }

}
