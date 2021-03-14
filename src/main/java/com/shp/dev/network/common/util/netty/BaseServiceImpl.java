package com.shp.dev.network.common.util.netty;

import org.springframework.stereotype.Service;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/14 21:33
 * @PackageName: com.shp.dev.network.common.util.netty
 * @ProjectName: network
 */
@Service
public class BaseServiceImpl implements BaseService {
    @Override
    public void test() {
        System.out.println("调用service服务");
    }
}
