//package com.shp.dev.network.common.util.rabbitmq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
// * @CreateBy: shp
// * @Version: 1.0
// * @Description: TODO
// * @CreateTime: 2020/12/3 23:10
// * @PackageName: com.shp.dev.network.common.util.rabbitmq
// * @ProjectName: network
// */
//@Component
//@RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
//public class DirectReceiver {
//
//    @RabbitHandler
//    public void process(Map testMessage) {
//        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
//    }
//
//}