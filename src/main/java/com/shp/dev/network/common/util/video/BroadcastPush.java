package com.shp.dev.network.common.util.video;

import com.shp.dev.network.common.util.redis.RedisConfig;
import com.shp.dev.network.common.util.websocket.WebSocketVideoServer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

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
public class BroadcastPush {

    @Autowired
    private RedisConfig redisConfig;

    //调度
    @SneakyThrows
    @Scheduled(initialDelay = 5000, fixedRate = 1000 / 25)
    private void cheduled() {
        for (Map map : VideoConstant.tbVideoList) {
            process(map);
        }
    }

    private void process(Map map) {
        Thread playThread = new Thread(new Runnable() { public void run() {
            String o = (String) redisConfig.getRedisTemplate(2).opsForValue().get(map.get("channel"));
            String baseByFile = "data:image/png;base64," + o;
            WebSocketVideoServer.broadCastInfo(baseByFile);
        }});
        playThread.start();
    }

}
