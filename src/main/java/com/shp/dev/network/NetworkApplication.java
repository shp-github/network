package com.shp.dev.network;

import com.shp.dev.network.common.util.netty.NettyWebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication//表示为启动类
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableSwagger2//启动swagger-ui接口文档
//@EnableJms //启动消息队列
@Slf4j
public class NetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkApplication.class, args);
        try {
            new NettyWebSocketServer().start();
            log.info("netty加载成功");
        }catch(Exception e) {
            log.error("netty加载失败{}",e.getMessage());
            System.out.println("NettyServerError:"+e.getMessage());
        }
    }

}


