package com.nixsolutions.task1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by svichkar on 11/23/2015.
 */
public class Producer implements Runnable {

    private BlockingQueue<Integer> queue;
    private AtomicInteger remainingCapacity;

    /**
     * Producer's constructor
     */
    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.queue = blockingQueue;
        this.remainingCapacity = new AtomicInteger(blockingQueue.remainingCapacity());
    }

    /**
     * method which fills queue with 100 random Integer elements
     */
    @Override
    public void run() {

        try {
            while (remainingCapacity.get() > 0) {
                queue.put(ThreadLocalRandom.current().nextInt());
                remainingCapacity.decrementAndGet();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
