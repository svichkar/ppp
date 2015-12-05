package com.nixsolutions.task1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by svichkar on 11/23/2015.
 */
public class Producer implements Runnable {

    private BlockingQueue<Integer> queue;

    /**
     * Producer's constructor
     */
    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.queue = blockingQueue;
    }

    /**
     * method which fills queue with 100 random Integer elements
     */
    @Override
    public void run() {

        int N = queue.remainingCapacity();
        int i = 0;
        try {
            while (i++ < N) {
                queue.put(ThreadLocalRandom.current().nextInt());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
