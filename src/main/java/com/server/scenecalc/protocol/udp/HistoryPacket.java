package com.server.scenecalc.protocol.udp;

import java.net.InetSocketAddress;

/**
 * Created by linjuntan on 2017/11/22.
 * email: ljt1343@gmail.com
 */
public class HistoryPacket {
    private byte[] data;

    private int retryTime;

    private InetSocketAddress sender;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }

    public InetSocketAddress getSender() {
        return sender;
    }

    public void setSender(InetSocketAddress sender) {
        this.sender = sender;
    }
}
