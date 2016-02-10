package com.manetskiy.task1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class NumbersProducer implements Runnable {
    private BlockingQueue<Number> queue;
    private final Long queueEnd = Long.MAX_VALUE;

    public NumbersProducer(BlockingQueue<Number> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            for (int i = 0; i < 100; i++) {
                queue.put(rand.nextInt());
            }
            queue.put(queueEnd);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
