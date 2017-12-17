package com.server.scenecalc.protocol.udp;

import com.server.scenecalc.core.PacketLengthExceedException;
import com.server.scenecalc.core.ServerContext;
import com.server.scenecalc.protocol.Header;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by linjuntan on 2017/11/22.
 * email: ljt1343@gmail.com
 */
@Slf4j
@Component
@EnableScheduling
public class UdpKeeper {
    private static ConcurrentHashMap<Integer, HistoryPacket> historyMsgMap = new ConcurrentHashMap<>();

    private ServerContext context;

    @Value("${udp.retry:3}")
    private int retryTime;

    public static void putMsg(Header header, byte[] data, InetSocketAddress sender) {
        HistoryPacket packet = new HistoryPacket(data, sender);
        historyMsgMap.putIfAbsent(header.getSessionId(), packet);
    }

    public static void receiveMsg(int sessionId) {
        HistoryPacket packet = historyMsgMap.remove(sessionId);
        log.debug("[debug] >>> receive package, sessionId => {}, packet => {}", sessionId, packet.toString());
    }


    @Scheduled(cron = "0/5 * * * * *")//每五秒
    public void checkReceivePacket() {
        if (historyMsgMap.isEmpty()) return;

        if (context == null) context = ServerContext.getInstance();

        historyMsgMap.forEach((e, v) -> {
            if (v.getRetryTime() > retryTime) {
                  log.error("[error] >>> {} is offline, can not receive returnPacket", v.getSender());
                  historyMsgMap.remove(e);
            } else {
                v.setRetryTime(v.getRetryTime() + 1);
                try {
                    context.sendUdpMessage(v.getData(), v.getSender());
                } catch (PacketLengthExceedException e1) {
                    log.error("[error] >>> data length exceed", e1);
                }
            }
        });
    }


}
