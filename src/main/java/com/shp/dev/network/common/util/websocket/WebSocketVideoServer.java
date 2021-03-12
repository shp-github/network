package com.shp.dev.network.common.util.websocket;

import com.shp.dev.network.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO websockt视频服务
 * @CreateTime: 2020/12/12 23:49
 * @PackageName: com.shp.dev.network.common.util.websocket
 * @ProjectName: network
 */

@ServerEndpoint(value = "/ws/video")
@Component
@Slf4j
public class WebSocketVideoServer {

    //打印日志是的标题分类
    private final static String type = "视频服务";
    //websocket连接数
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<Session>();

    @PostConstruct
    public void init() {
        log.info(type + "初始化成功！！！");
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        SessionSet.add(session);
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info(type + "有连接加入，当前连接数为：{}", cnt);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        SessionSet.remove(session);
        int cnt = OnlineCount.decrementAndGet();
        log.info(type + "有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * 群发消息
     * @param message
     */
    public static void broadCastInfo(String message){
        for (Session session : SessionSet) {
            if (session.isOpen()) {
                SendMessage(session, message);
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            if ("ping".equals(message)) {
                SendMessage(session, message);
                log.info(type + "目前存活。。。{}", DateUtils.getDateTime());
            } else {
                SendMessage(session, message);
            }
        } catch (Exception e) {
            log.info(type + "接收到客户端消息发生错误{}", e.getMessage());
        }
        log.info(type + "自客户端的消息：{}", message);
    }

    /**
     * 出现错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error(type + "错误：{}，Session ID： {}", error.getMessage(), session.getId());
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     */
    public static void SendMessage(Session session, String message) {
        try {
            //session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)",message,session.getId()));
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error(type + "发送消息出错：{}", e.getMessage());
        }
    }
}
