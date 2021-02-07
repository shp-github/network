package com.shp.dev.network.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO application.yml 文件
 * @CreateTime: 2020/10/31 16:08
 * @PackageName: com.shp.dev.network.common.config
 * @ProjectName: network
 */
@Component
public class ApplicationConfig {

    @Value("${spring.servlet.multipart.maxFileSize}")
    private String maxFileSize;

    public void test(){
        System.out.println(maxFileSize);
    }

    public static void main(String[] args) {
        ApplicationConfig config = new ApplicationConfig();
        config.test();
    }


}
