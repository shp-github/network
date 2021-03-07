package com.shp.dev.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication//表示为启动类
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableSwagger2//启动swagger-ui接口文档
//@EnableJms //启动消息队列
public class NetworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(NetworkApplication.class, args);
    }
}
