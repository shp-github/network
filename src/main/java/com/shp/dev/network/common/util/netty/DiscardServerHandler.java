package com.shp.dev.network.common.util.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/14 21:33
 * @PackageName: com.shp.dev.network.common.util.netty
 * @ProjectName: network
 */
@Component
@ChannelHandler.Sharable
public class DiscardServerHandler extends ChannelHandlerAdapter {
    @Resource
    private BaseService baseService;

    //@Override
    //public void channelRead(ChannelHandlerContext ctx, Object msg) {
    //    try {
    //        ByteBuf in = (ByteBuf) msg;
    //        System.out.println("传输内容是");
    //        System.out.println(in.toString(CharsetUtil.UTF_8));
    //        //这里调用service服务
    //        baseService.test();
    //    }  finally {
    //        ReferenceCountUtil.release(msg);
    //    }
    //}

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx);
    }
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 出现异常就关闭
        cause.printStackTrace();
        ctx.close();
    }
}
