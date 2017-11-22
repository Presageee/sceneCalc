package com.server.scenecalc.event;

import com.server.scenecalc.service.HeartbeatService;
import com.server.scenecalc.worker.HeartbeatWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Created by linjuntan on 2017/10/22.
 * email: ljt1343@gmail.com
 */
@Slf4j
@Component
public class ApplicationInit implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private HeartbeatService heartbeatService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("[info] >>> spring application context started");
        CompletableFuture.runAsync(new HeartbeatWorker(heartbeatService));
    }
}
