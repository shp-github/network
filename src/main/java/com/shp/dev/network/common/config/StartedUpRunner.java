package com.shp.dev.network.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;

/**
 * @CreateBy: shp
 * @version：1.0
 * @Description: TODO 启动banner
 * @CreateTime: 2021/1/5 15:55
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class StartedUpRunner  implements ApplicationRunner, ApplicationListener<WebServerInitializedEvent> {
    public static int serverPort;
    @Override
    public void run(ApplicationArguments args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        log.info("++++++++++++++++++++++++++++++++++++++++++++");
        log.info("生意我所欲，抑郁了我去");
        log.info("恭喜您，启动成功!");
        log.info("服务端口:{}", serverPort);
        log.info("进程ID:{}", pid);
        log.info("接口文档地址:http://localhost:{}",serverPort);
        log.info("++++++++++++++++++++++++++++++++++++++++++++");
    }
    public int getPort() {
        return this.serverPort;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }
}

