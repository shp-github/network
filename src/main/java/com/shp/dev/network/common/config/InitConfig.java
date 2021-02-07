package com.shp.dev.network.common.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 系统启动时进行初始化
 * @CreateTime: 2021/1/16 20:15
 * @PackageName: com.shp.dev.network.common.util.init
 * @ProjectName: network
 */

@Component
public class InitConfig implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("进行初始化");
    }
}
