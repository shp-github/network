package com.shp.dev.network.common.util.websocket;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 发送消息区分  {"token":"123123123","status":"send"}
 * @CreateTime: 2020/12/12 23:49
 * @PackageName: com.shp.dev.network.common.util.websocket
 * @ProjectName: network
 */

@ServerEndpoint(value = "/ws/chat")
@Component
@Slf4j
public class WebSocketChatServer {

    @PostConstruct
    public void init() {
        log.info("聊天初始化websocket");
    }

    //websocket连接数
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    //key 密钥   value session   ----> 发送消息时 根据密钥找session 根据session发送消息
    private static ConcurrentHashMap<String, Session> keyMap = new ConcurrentHashMap();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info("聊天有连接加入，当前连接数为：{}", cnt);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        int cnt = OnlineCount.decrementAndGet();
        log.info("聊天有连接关闭，当前连接数为：{}", cnt);
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
                log.info("测试心跳成功：", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
            } else {
                //如果是web大屏互相通信则保存到keyMap中
                JSONObject jsonObject = JSONObject.fromObject(message);
                String status = jsonObject.getString("status");
                String token = jsonObject.getString("token");
                if (status.equals("accept")) {
                    //通过tokne绑定session
                    keyMap.put(token, session);
                }
                if (status.equals("send")) {
                    Session sendSession = keyMap.get(token);
                    if (sendSession != null) {
                        SendMessage(sendSession, message);
                    } else {
                        log.info("聊天无人接收消息不发送" + token);
                    }
                }
            }

        } catch (Exception e) {
            log.info("聊天接收到客户端消息发生错误", e.getMessage());
        }
        log.info("聊天来自客户端的消息：{}", message);

    }

    /**
     * 出现错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("聊天发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     */
    public static void SendMessage(Session session, String message) {
        try {
            //session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)",message,session.getId()));
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("聊天发送消息出错：{}", e.getMessage());
        }
    }
}
