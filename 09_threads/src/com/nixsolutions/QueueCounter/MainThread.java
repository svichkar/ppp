package com.nixsolutions.QueueCounter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sobolenko on 2/9/2016.
 */
public class MainThread implements Runnable {

    AtomicInteger counter;

    public MainThread(AtomicInteger counter) {
        this.counter = counter;
    }

    public AtomicInteger getCounter() {
        return counter;
    }

    @Override
    public void run() {
        for (int i = 0; counter.intValue() < 1000; i++) {
            try {
                Thread.sleep(100);
                counter.addAndGet(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
