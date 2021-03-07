package com.shp.dev.network.common.util.code.dao;

import java.util.List;
import java.util.Map;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/7 10:19
 * @PackageName: com.shp.dev.network.common.util.code.dao
 * @ProjectName: network
 */
public interface GeneratorDao {
    List<Map<String, Object>> queryList(Map<String, Object> map);
    Map<String, String> queryTable(String tableName);
    List<Map<String, String>> queryColumns(String tableName);
}
