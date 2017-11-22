package com.server.scenecalc.service;

import com.server.scenecalc.core.ServerContext;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

/**
 * Created by linjuntan on 2017/10/19.
 * email: ljt1343@gmail.com
 */
public interface HeartbeatService {
    default void updateHeartbeat(InetSocketAddress address) {
//        System.out.println(address.toString());
        ServerContext.getInstance().getHeartbeatMap().put(address.getAddress().toString(), System.currentTimeMillis());
    }
}
