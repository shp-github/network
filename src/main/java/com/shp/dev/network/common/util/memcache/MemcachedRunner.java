package com.shp.dev.network.common.util.memcache;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/16 20:36
 * @PackageName: com.shp.dev.network.common.util.memcache
 * @ProjectName: network
 */
@Component
@Slf4j
public class MemcachedRunner implements CommandLineRunner {

    @Resource
    private  MemcacheSource memcacheSource;

    private MemcachedClient client = null;

    @Override
    public void run(String... args) throws Exception {
        try {
            client = new MemcachedClient(new InetSocketAddress(memcacheSource.getIp(),memcacheSource.getPort()));
        } catch (IOException e) {
            log.error("inint MemcachedClient failed ",e);
        }
    }

    public MemcachedClient getClient() {
        return client;
    }

}