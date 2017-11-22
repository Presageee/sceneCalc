package com.server.scenecalc.worker;

/**
 * Created by linjuntan on 2017/10/19.
 * email: ljt1343@gmail.com
 */
public interface BaseWorker extends Runnable {

    default void run() {
        handler();
    }

    void handler();
}
