package com.shp.dev.network.common.util.code.model;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/7 10:22
 * @PackageName: com.shp.dev.network.common.util.code.model
 * @ProjectName: network
 */
public class MongoCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String database = context.getEnvironment().getProperty("renren.database");
        return "mongodb".equalsIgnoreCase(database);
    }
}
