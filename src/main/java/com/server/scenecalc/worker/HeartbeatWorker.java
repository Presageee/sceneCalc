package com.server.scenecalc.worker;

import com.server.scenecalc.core.ServerContext;
import com.server.scenecalc.service.HeartbeatService;
import io.netty.channel.socket.DatagramPacket;


/**
 * Created by linjuntan on 2017/10/19.
 * email: ljt1343@gmail.com
 */
public class HeartbeatWorker implements BaseWorker {
    private HeartbeatService heartbeatService;

    private ServerContext context;

    public HeartbeatWorker() {
        context = ServerContext.getInstance();
    }

    public HeartbeatWorker(HeartbeatService heartbeatService) {
        this.heartbeatService = heartbeatService;
        context = ServerContext.getInstance();
    }


    @Override
    public void handler() {
        while (true) {
            try {
                DatagramPacket packet = context.takeHeartbeat();

                if (packet == null) {
                    continue;
                }
                handlerMessage(packet);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void handlerMessage(DatagramPacket packet) {
        heartbeatService.updateHeartbeat(packet.sender());
    }
}
