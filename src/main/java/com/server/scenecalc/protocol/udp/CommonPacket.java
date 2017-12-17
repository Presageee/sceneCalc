package com.server.scenecalc.protocol.udp;

import java.net.InetSocketAddress;

/**
 * Created by linjuntan on 2017/12/3.
 * email: ljt1343@gmail.com
 */
public class CommonPacket {
    private byte[] data;

    private InetSocketAddress sender;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public InetSocketAddress getSender() {
        return sender;
    }

    public void setSender(InetSocketAddress sender) {
        this.sender = sender;
    }
}
