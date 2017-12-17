package com.server.scenecalc.protocol;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by linjuntan on 2017/11/29.
 * email: ljt1343@gmail.com
 */
public class SessionIdGenerator {

    private static AtomicInteger sessionIdSeq = new AtomicInteger(0);

    public static Integer generateSessionId() {
        int next = sessionIdSeq.incrementAndGet();

        if (next >= Integer.MAX_VALUE) {
            sessionIdSeq = new AtomicInteger(0);
            next = 0;
        }

        return next;
    }


}
