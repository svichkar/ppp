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

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(100);
                counter.addAndGet(1);
                System.out.println(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Thread.currentThread().interrupt();
    }
}
