package com.nixsolutions.QueueCounter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sobolenko on 2/9/2016.
 */
public class RegularThreads implements Runnable {

    String name;
    AtomicInteger counter;
    int startCount;

    public RegularThreads(String name, AtomicInteger counter, int startCount) {
        this.name = name;
        this.counter = counter;
        this.startCount = startCount;
    }

    @Override
    public void run() {
        while (counter.intValue() < 1000) {
            if (counter.intValue() > startCount) {
                try {
                    Thread.sleep(1000);
                    System.out.println(name + " working...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
