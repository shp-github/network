package com.shp.dev.network.common.util.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO 通道组池，管理所有websocket连接
 * @CreateTime: 2021/3/16 17:20
 * @PackageName: com.shp.dev.network.common.util.netty
 * @ProjectName: network
 */
public class NettyChannelHandlerPool {

    public NettyChannelHandlerPool(){}

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}

