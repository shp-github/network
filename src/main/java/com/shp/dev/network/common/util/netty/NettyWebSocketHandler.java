package com.shp.dev.network.common.util.netty;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/16 17:21
 * @PackageName: com.shp.dev.network.common.util.netty
 * @ProjectName: network
 */
public class NettyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("与客户端建立连接，通道开启！");
        //添加到channelGroup通道组
        NettyChannelHandlerPool.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("与客户端断开连接，通道关闭！");
        //添加到channelGroup 通道组
        NettyChannelHandlerPool.channelGroup.remove(ctx.channel());
    }

    @SneakyThrows
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        //首次连接是FullHttpRequest，处理链接地址中拼接的参数
        //ws://127.0.0.1:12345/ws?uid=666&gid=777
        if (null != msg && msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            //request.uri()获取完整ws的URL地址
            String uri = request.uri();
            Map paramMap = getUrlParams(uri);
            System.out.println("nettywebsocket接收到的参数是：" + JSON.toJSONString(paramMap));
            //如果url包含参数，需要处理
            if (uri.contains("?")) {
                String newUri = uri.substring(0, uri.indexOf("?"));
                System.out.println(newUri);
                request.setUri(newUri);
            }
        } else if (msg instanceof TextWebSocketFrame) {
            //正常的TEXT消息类型
            TextWebSocketFrame frame = (TextWebSocketFrame) msg;
            System.out.println("nettywebsocket客户端收到服务器数据：" + frame.text());
            sendAllMessage(frame.text());
        }
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) {

    }

    public static void sendAllMessage(String message) {
        //收到信息后，群发给所有channel
        NettyChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }

    /**
     * @CreateBy: Administrator
     * @version：1.0
     * @Description: TODO 提取参数
     * @CreateTime: 2021/3/16 17:56
     * @param: url
     * @return: java.util.Map
     */
    private static Map getUrlParams(String url) {
        Map<String, String> map = new HashMap<>();
        url = url.replace("?", ";");
        if (!url.contains(";")) {
            return map;
        }
        if (url.split(";").length > 0) {
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr) {
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key, value);
            }
            return map;
        } else {
            return map;
        }
    }
}
