//package com.shp.dev.network.common.util.task;
//
//import com.shp.dev.network.common.util.activemq.Producer;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * @CreateBy: shp
// * @Version: 1.0
// * @Description: TODO 模拟生产消息
// * @CreateTime: 2020/9/25 16:49
// * @PackageName: com.shp.dev.network.common.util.start
// * @ProjectName: network
// */
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling   // 2.开启定时任务
//@Slf4j
//public class ProducerTake {
//
//    @Autowired//注入activemq
//    private Producer producer;
//
//    @Autowired//注入rabbitmq
//    private RabbitTemplate rabbitTemplate;
//
//    @Autowired//注入kafka
//    private KafkaProducer kafkaProducer;
//
//
//    //模拟向Kafka发送消息
//    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
//    public void sendMessagesToKafka() {
//        try {
//            for (int i = 0; i < 10; i++) {
//                //kafkaProducer.send("发送消息到kafka通道");
//                Thread.sleep(1000);
//            }
//        } catch (Exception e) {
//            System.out.println("没有启动Kafka消息队列");
//            System.err.println(e.getMessage());
//        }
//    }
//
//    //模拟向activemq发送消息
//    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
//    public void sendMessagesToAciveMQ() {
//        try {
//            for (int i = 0; i < 10; i++) {
//                producer.sendMsg("test.queue", " send messages-" + i);
//                Thread.sleep(1000);
//            }
//        } catch (Exception e) {
//            //启动方式： 进入目录  D:\installTmp\apache-activemq-5.16.0\bin ，
//            //打开dos窗口输入命令：activemq.bat start
//            //访问地址 http://localhost:8161/ 输入用户名：admin，密码：admin 测试是否成功。
//            System.out.println("没有启动activemq消息队列");
//            System.err.println(e.getMessage());
//        }
//    }
//
//    //模拟向rabbitmq发送消息
//    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
//    public void sendMessagesToRabbitMQ() {
//        try {
//            for (int i = 0; i < 10; i++) {
//                String messageId = String.valueOf(UUID.randomUUID());
//                String messageData = "test message, hello!";
//                String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                Map<String, Object> map = new HashMap<>();
//                map.put("messageId", messageId);
//                map.put("messageData", messageData);
//                map.put("createTime", createTime);
//                //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
//                rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
//                Thread.sleep(1000);
//            }
//        } catch (Exception e) {
//            //管理界面访问地址 http://localhost:15672/ 输入用户名：guest，密码：guest 测试是否成功。
//            //java访问地址 http://localhost:5672/ 输入用户名：guest，密码：guest 测试是否成功。
//            System.out.println("没有启动rabbitmq消息队列");
//            System.err.println(e.getMessage());
//        }
//    }
//
//}
//
