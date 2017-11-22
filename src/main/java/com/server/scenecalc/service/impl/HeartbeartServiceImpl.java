package com.server.scenecalc.service.impl;

import com.server.scenecalc.core.ServerContext;
import com.server.scenecalc.service.HeartbeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 * Created by linjuntan on 2017/10/22.
 * email: ljt1343@gmail.com
 */
@Service
@Slf4j
public class HeartbeartServiceImpl implements HeartbeatService {
    @Override
    public void updateHeartbeat(InetSocketAddress address) {
        log.debug("[debug] >>> address heartbeat => {}", address.toString());
        String ip = address.getAddress().getHostAddress();
        putMap(ip);
    }

    private void putMap(String ip) {
        Map<String, Long>map = ServerContext.getInstance().getHeartbeatMap();
        map.put(ip, System.currentTimeMillis());
    }

}
