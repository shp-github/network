package com.shp.dev.network.common.util.memcache;

import io.swagger.annotations.ApiOperation;
import net.spy.memcached.MemcachedClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/16 20:41
 * @PackageName: com.shp.dev.network.common.util.memcache
 * @ProjectName: network
 */

@RestController
@RequestMapping("/memcache")
public class MemcacheController {
    @Resource
    private MemcachedRunner memcachedRunner;

    @RequestMapping(value = "/testSetGet", method = {RequestMethod.POST})
    @ApiOperation("测试")
    public void testSetGet() {
        MemcachedClient memcachedClient = memcachedRunner.getClient();
        memcachedClient.set("testkey", 1000, "666666");
        System.out.println("***********  " + memcachedClient.get("testkey").toString());
    }
}
