//package com.shp.dev.network.common.util.activemq;
//
//import org.apache.activemq.command.ActiveMQQueue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.jms.Destination;
//
///**
// * @CreateBy: shp
// * @Version: 1.0
// * @Description: TODO 生产者producer 用于发送消息
// * @CreateTime: 2020/10/31 0:29
// * @PackageName: com.shp.dev.network.common.util.activemq
// * @ProjectName: network
// */
//@Service("producer")
//public class Producer {
//
//    @Autowired
//    private JmsMessagingTemplate jmsTemplate;
//
//    public void sendMsg(String destinationName, String message) {
//        System.out.println("------------ 发送消息消费者进行处理:" + message);
//        Destination destination = new ActiveMQQueue(destinationName);
//        jmsTemplate.convertAndSend(destination, message);
//    }
//}
