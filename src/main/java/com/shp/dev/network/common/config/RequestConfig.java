package com.shp.dev.network.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 允许多请求地址多加斜杠
 * @CreateTime: 2020/9/12 12:19
 * @PackageName: com.shp.dev.network.common.config
 * @ProjectName: network
 */
public class RequestConfig {
    //允许多请求地址多加斜杠  比如 /msg/list   //msg/list
    @Bean
    public HttpFirewall httpFirewall() {
        return new DefaultHttpFirewall();
    }
}
