package com.shp.dev.network.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @CreateBy: shp
 * @version：1.0
 * @Description: TODO 启动banner
 * @CreateTime: 2021/1/5 15:55
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class StartedUpRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        log.info("++++++++++++++++++++++++++++++++++++++++++++");
        log.info("恭喜您，启动成功!");
        log.info("++++++++++++++++++++++++++++++++++++++++++++");
    }

}

