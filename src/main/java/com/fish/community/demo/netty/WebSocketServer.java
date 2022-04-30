package com.fish.community.demo.netty;

import com.fish.community.demo.aspect.LogAspect;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class WebSocketServer {
    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    private static class SingletionWSServer {
        static final WebSocketServer instance = new WebSocketServer();
    }

    public static WebSocketServer getInstance() {
        return SingletionWSServer.instance;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;
    private Channel serverChannel;

    public WebSocketServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());
    }

    public void start() throws InterruptedException {
//        this.future = server.bind("172.27.75.4",8888);
        this.future = server.bind("127.0.0.1",8888);
        serverChannel = future.sync().channel().closeFuture().sync().channel();
        if (future.isSuccess()) {
            LOG.info("启动 Netty 成功");
            System.out.println("启动 Netty 成功");
        }else{
            LOG.info("启动 Netty 成功");
            System.out.println("启动 Netty 失败");
        }
    }

    @PreDestroy
    public void stop() throws Exception {
        serverChannel.close();
        serverChannel.parent().close();
        LOG.info("netty关闭");
    }
}
