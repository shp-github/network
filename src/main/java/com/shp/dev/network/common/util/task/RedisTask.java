package com.shp.dev.network.common.util.task;

import com.shp.dev.network.common.util.jdbc.JDBCUtils;
import com.shp.dev.network.common.util.sql.model.SysSql;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 定时同步到redis里
 * @CreateTime: 2020/9/25 16:49
 * @PackageName: com.shp.dev.network.common.util.start
 * @ProjectName: network
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class RedisTask {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //每隔60秒调用一次
    //@Scheduled(fixedRate = 60000)
    public void synchroSysSql() {
        log.info("同步sql表到redis中");
        try {
            JDBCUtils<SysSql> jdbcUtils = new JDBCUtils();
            List<SysSql> sysSqls = jdbcUtils.queryList("select * from tb_sys_sql", SysSql.class);
            for (SysSql sysSql : sysSqls) {
                //redisTemplate.opsForValue().set(sysSql.getSqlName(), sysSql.getSqlText());
            }
        } catch (Exception e) {
            log.info("同步sql表到redis中失败" + e.getMessage());
        }
    }

}
