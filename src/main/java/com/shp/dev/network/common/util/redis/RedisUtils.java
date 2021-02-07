package com.shp.dev.network.common.util.redis;

import redis.clients.jedis.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/11/30 14:40
 * @PackageName: com.tieta.ai.common.utils.redis
 * @ProjectName: ai
 */
public class RedisUtils {

    //集群
    private static ShardedJedisPool pool;
    //单独
    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "47.92.213.36", 6379, 2000, "2948299576", 1);
    private static Jedis resource = jedisPool.getResource();

    static {
        jedisPoolConfig.setMaxIdle(10000);
        jedisPoolConfig.setMaxWaitMillis(2000);

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        // 集群
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("47.92.213.36", 6379);
        jedisShardInfo1.setPassword("2948299576");
        jedisShardInfo1.setConnectionTimeout(1000);
        jedisShardInfo1.setSoTimeout(1000);

        List<JedisShardInfo> list = new LinkedList();
        list.add(jedisShardInfo1);
        pool = new ShardedJedisPool(config, list);
    }


    public static void main(String[] args) {

        String aaa = RedisUtils.resource.set("aaa", "111");
        System.out.println(aaa+":"+resource.get("aaa"));

        String bbb = RedisUtils.pool.getResource().set("bbb", "222");
        System.out.println(bbb+":"+RedisUtils.pool.getResource().get("bbb"));

    }
}
