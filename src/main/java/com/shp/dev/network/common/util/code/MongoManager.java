package com.shp.dev.network.common.util.code;

import com.shp.dev.network.common.util.code.conf.DbConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/7 10:28
 * @PackageName: com.shp.dev.network.common.util.code
 * @ProjectName: network
 */
public class MongoManager {

    /***mongo扫描很消耗性能 尤其是子类的封装  使用缓存**/
    private static Map<String, MongoDefinition> mongoCache = new ConcurrentHashMap<>();

    public static Map<String, MongoDefinition> getCache() {
        return mongoCache;
    }

    public static MongoDefinition getInfo(String tableName) {
        return mongoCache.getOrDefault(tableName, null);
    }

    public static MongoDefinition putInfo(String tableName, MongoDefinition mongoDefinition) {
        return mongoCache.put(tableName, mongoDefinition);
    }

    /**
     * 当前配置是否为mongo内容
     */
    public static boolean isMongo() {
        return DbConfig.isMongo();
    }


}
