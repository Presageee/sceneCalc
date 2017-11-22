package com.server.scenecalc.handler;

import com.server.scenecalc.core.ServerContext;
import com.server.scenecalc.protocol.CommonProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by linjuntan on 2017/10/15.
 * email: ljt1343@gmail.com
 */
@Slf4j
public class HeartbeatHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        log.info(datagramPacket.content().toString());
        ByteBuf buf = datagramPacket.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        CommonProtocol protocol = new CommonProtocol();
        protocol.decode(req);
        if (protocol.getHeader().getControl() == 1) {
            System.out.println("heartbeart!!!");
        } else {
            System.out.println(new String(protocol.getBody()));

        }
        //        int len = datagramPacket.content().readableBytes();
//        if (len == 1) {
//            ServerContext.getInstance().putHeartbeat(datagramPacket);
//        }
//        channelHandlerContext.fireChannelRead(datagramPacket);
//        channelHandlerContext.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("abcd", CharsetUtil.UTF_8),
//                datagramPacket.sender()));

    }
}
