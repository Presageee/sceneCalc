package com.server.scenecalc.task;

import com.server.scenecalc.core.ServerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linjuntan on 2017/10/22.
 * email: ljt1343@gmail.com
 */
@EnableScheduling
@Component
@Slf4j
public class CheckHeartbeatTask {

    @Value("${heartbeat.time:60000}")
    private long heartbeatTime;

    @Scheduled(cron = "0 * * * * ?")
    public void checkHeartbeat() {
        long time = System.currentTimeMillis();
        List<String> ips = new ArrayList<>();
        ServerContext.getInstance().getHeartbeatMap().forEach((e, v) -> {
            if (time - v > heartbeatTime) ips.add(e);
        });

        ips.forEach(e -> ServerContext.getInstance().getHeartbeatMap().remove(e));

        setOnlineCnt();
    }

    private void setOnlineCnt() {
        ServerContext.getInstance().setOnlineCnt(ServerContext.getInstance().getHeartbeatMap().size());
    }
}
