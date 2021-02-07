package com.shp.dev.network.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO Spring工具类
 * @CreateTime: 2020-07-07 22:05
 * @PackageName: com.shp.dev.network.common.util
 * @ProjectName: network
 */
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
        }
    }

    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }
}
