package com.shp.dev.network.common.util.code.dao;

import com.shp.dev.network.common.util.code.MongoDefinition;
import com.shp.dev.network.common.util.code.MongoManager;
import com.shp.dev.network.common.util.code.MongoScanner;
import com.shp.dev.network.common.util.code.MongoTableInfoAdaptor;
import com.shp.dev.network.common.util.code.factory.MongoDBCollectionFactory;
import com.shp.dev.network.common.util.code.model.MongoCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/7 10:22
 * @PackageName: com.shp.dev.network.common.util.code.dao
 * @ProjectName: network
 */
@Repository
@Conditional(MongoCondition.class)
public class MongoDBGeneratorDao implements GeneratorDao {

    @Autowired
    private MongoDBCollectionFactory mongoDBCollectionFactory;

    @Override
    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        List<String> collectionNames = MongoDBCollectionFactory.getCollectionNames(map);
        return (List) MongoTableInfoAdaptor.tableInfo(collectionNames);
    }

    @Override
    public Map<String, String> queryTable(String tableName) {
        Map<String, String> result = new HashMap<>(4 * 4 / 3 + 1);
        result.put("engine", "");
        result.put("createTime", "");
        result.put("tableComment", "mongoDB " + tableName);
        result.put("tableName", tableName);
        return result;

    }

    @Override
    public List<Map<String, String>> queryColumns(String tableName) {
        MongoDefinition mongoDefinition = MongoManager.getInfo(tableName);
        if (mongoDefinition == null) {
            System.out.println(tableName);
            MongoScanner mongoScanner = new MongoScanner(mongoDBCollectionFactory.getCollection(tableName));
            mongoDefinition = mongoScanner.getProduct();
        }
        return MongoTableInfoAdaptor.columnInfo(mongoDefinition);
    }
}