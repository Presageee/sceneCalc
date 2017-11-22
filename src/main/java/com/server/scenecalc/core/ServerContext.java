package com.server.scenecalc.core;

import io.netty.channel.socket.DatagramPacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by linjuntan on 2017/10/15.
 * email: ljt1343@gmail.com
 */
public class ServerContext {
    private ServerContext() {

    }

    public static ServerContext getInstance() {
        return ServerContextHolder.context;
    }

    private static class ServerContextHolder {
        static ServerContext context = new ServerContext();
    }

    private Map<String, Long> heartbeatMap = new ConcurrentHashMap<>();

    private volatile long onlineCnt;

    public long getOnlineCnt() {
        return onlineCnt;
    }

    public void setOnlineCnt(long onlineCnt) {
        this.onlineCnt = onlineCnt;
    }

    private LinkedBlockingQueue<DatagramPacket> heartbeatQueue = new LinkedBlockingQueue<>();

    public void putHeartbeat(DatagramPacket packet) throws InterruptedException {
        heartbeatQueue.put(packet);
    }

    public DatagramPacket takeHeartbeat() throws InterruptedException {
        return heartbeatQueue.take();
    }

    public Map<String, Long> getHeartbeatMap() {
        return heartbeatMap;
    }

    public void setHeartbeatMap(Map<String, Long> heartbeatMap) {
        this.heartbeatMap = heartbeatMap;
    }
}
