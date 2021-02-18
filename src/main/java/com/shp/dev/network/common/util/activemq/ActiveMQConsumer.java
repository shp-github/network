package com.shp.dev.network.common.util.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 消费者 用于消费消息
 * @CreateTime: 2020/10/31 0:30
 * @PackageName: com.shp.dev.network.common.util.activemq
 * @ProjectName: network
 */
@Component
public class ActiveMQConsumer {
    //destination = 监听的主题
    //listen(String text) 收到的消息
    @JmsListener(destination = "active.queue")
    public void listen(String text) {
        System.out.println("<<<========= 收到消息开始处理 " + text);
    }
}