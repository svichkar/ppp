package com.nixsolutions.ProdConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by sobolenko on 2/8/2016.
 */
public class Producer implements Runnable {
    BlockingQueue queue;
    Random random = new Random();
    public boolean ready = false;

    public boolean isReady() {
        return ready;
    }

    public BlockingQueue getQueue() {
        return queue;
    }

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                queue.put(produce());
            } catch (InterruptedException e) {
                Thread.interrupted();
                e.printStackTrace();
            }
        }
        ready = true;
    }

    Object produce() {
        int integer = random.nextInt(150);
        System.out.println("add: " + integer);
        return integer;
    }
}