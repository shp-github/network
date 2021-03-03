package com.shp.dev.network.common.util.redis;

import com.shp.dev.network.common.config.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/12/21 20:34
 * @PackageName: com.shp.dev.network.common.util.redis
 * @ProjectName: network
 */

@Component
@Slf4j
public class RedisConfig {

    private int defaultDb;

    public static Map<Integer, RedisTemplate<Serializable, Object>> redisTemplateMap = new HashMap<>();

    @PostConstruct
    public void initRedisTemp() {
        List<Integer> dbs = Constant.REDIS_DBS;
        log.info("###### START 初始化 Redis 连接池 START ######");
        defaultDb = dbs.get(0);
        for (Integer db : dbs) {
            log.info("###### 正在加载Redis-db-" + db + " ######");
            redisTemplateMap.put(db, redisTemplateObject(db));
        }
        log.info("###### END 初始化 Redis 连接池 END ######");
    }

    public RedisTemplate<Serializable, Object> redisTemplateObject(Integer dbIndex) {
        RedisTemplate<Serializable, Object> redisTemplateObject = new RedisTemplate<Serializable, Object>();
        redisTemplateObject.setConnectionFactory(redisConnectionFactory(jedisPoolConfig(), dbIndex));
        setSerializer(redisTemplateObject);
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }

    /**
     * 连接池配置信息
     * @return
     */
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxIdle(Constant.REDIS_MAXIDL);
        // 最小空闲连接数
        poolConfig.setMinIdle(Constant.REDIS_MINIDL);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        // 当池内没有可用的连接时，最大等待时间
        poolConfig.setMaxWaitMillis(10000);
        // ------其他属性根据需要自行添加-------------
        return poolConfig;
    }

    /**
     * jedis连接工厂
     * @param jedisPoolConfig
     * @return
     */
    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig, int db) {
        // 单机版jedis
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        // 设置redis服务器的host或者ip地址
        redisStandaloneConfiguration.setHostName(Constant.REDIS_HOST);
        // 设置默认使用的数据库
        redisStandaloneConfiguration.setDatabase(db);
        // 设置密码
        redisStandaloneConfiguration.setPassword(RedisPassword.of(Constant.REDIS_PASSWORD));
        // 设置redis的服务的端口号
        redisStandaloneConfiguration.setPort(Constant.REDIS_PORT);

        // 获得默认的连接池构造器(怎么设计的，为什么不抽象出单独类，供用户使用呢)
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration
                .builder();
        // 指定jedisPoolConifig来修改默认的连接池构造器（真麻烦，滥用设计模式！）
        jpcb.poolConfig(jedisPoolConfig);
        // 通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        // 单机配置 + 客户端配置 = jedis连接工厂
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    private void setSerializer(RedisTemplate<Serializable, Object> template) {
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
    }

    public RedisTemplate<Serializable, Object> getRedisTemplate(int db) {
        return redisTemplateMap.get(db);
    }

    public RedisTemplate<Serializable, Object> getRedisTemplate() {
        return redisTemplateMap.get(defaultDb);
    }
}