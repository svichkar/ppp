package com.nixsolutions.QueueCounter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sobolenko on 2/9/2016.
 */
public class RegularThreads implements Runnable {

    String name;
    AtomicInteger counter;

    public RegularThreads(String name, AtomicInteger counter) {
        this.name = name;
        this.counter = counter;
    }

    @Override
    public void run() {
        while (counter.intValue() < 1000) {
            try {
                Thread.sleep(1000);
                System.out.println("Thread " + name + " working...");
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
    }
}
