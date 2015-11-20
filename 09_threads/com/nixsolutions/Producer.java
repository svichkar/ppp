package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sergey on 20.11.2015.
 */
class Producer implements Runnable {
    private ConcurrentLinkedQueue<Integer> queue;
    private Random random = new Random();
    private ReentrantLock lock;
    private AtomicBoolean flag;

    Producer(ConcurrentLinkedQueue queue, ReentrantLock lock, AtomicBoolean flag) {
        this.queue = queue;
        this.lock = lock;
        this.flag = flag;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            queue.add(random.nextInt(500));
            lock.unlock();
        }
        flag.set(true);
    }
}

