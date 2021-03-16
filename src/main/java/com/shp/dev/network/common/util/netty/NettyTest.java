package com.shp.dev.network.common.util.netty;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO 广播推送（从redis中获取base64然后发送到websocket，websocket发送到前端展示视频）
 * @CreateTime: 2021/3/12 13:40
 * @PackageName: com.shp.dev.network.common.util.video
 * @ProjectName: network
 */

@Component
@EnableScheduling
@Slf4j
public class NettyTest {

    //调度
    @SneakyThrows
    @Scheduled(initialDelay = 0, fixedRate = 1000)
    private void cheduled() {
        NettyWebSocketHandler.sendAllMessage("测试nettywebsocket服务是否可以广播");
    }


}
