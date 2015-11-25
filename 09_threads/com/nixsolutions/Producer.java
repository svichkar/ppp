package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Sergey on 20.11.2015.
 */
class Producer implements Runnable {
    private BlockingQueue<Integer> queue;
    private Random random = new Random();

    Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        while (queue.offer(random.nextInt(500))) ;
    }
}

