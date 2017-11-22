package com.server.scenecalc;

import com.server.scenecalc.core.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CompletableFuture;

/**
 * Created by linjuntan on 2017/10/22.
 * email: ljt1343@gmail.com
 */
@Slf4j
public class SceneCalcApplication {
    private static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                BaseConfiguration.class);
        context.start();
        synchronized (obj) {
            obj.wait();
        }
    }
}
