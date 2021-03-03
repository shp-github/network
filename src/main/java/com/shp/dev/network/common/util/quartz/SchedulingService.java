package com.shp.dev.network.common.util.quartz;

import com.shp.dev.network.common.util.redis.RedisConfig;
import com.shp.dev.network.common.util.sql.mapper.SysSqlMapper;
import com.shp.dev.network.common.util.sql.model.SysSql;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO 调度业务层
 * @CreateTime: 2021/3/3 16:48
 * @PackageName: com.shp.dev.network.common.util.quartz
 * @ProjectName: network
 */

@Slf4j
@Component
public class SchedulingService {


    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private SysSqlMapper mapper;

    //同步sql表到redis中
    @Async("redis")
    public void synchronizedRedis() {
        List<SysSql> all = mapper.findAll();
        for (SysSql sysSql : all) {
            redisConfig.getRedisTemplate(0).opsForValue().set(sysSql.getSqlName(), sysSql.getSqlText());
        }
        log.info("同步sql表到redis中");
    }

}
