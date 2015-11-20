package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sergey on 20.11.2015.
 */
class Producer implements Runnable {
    private BlockingQueue<Integer> queue;
    private Random random = new Random();
    private ReentrantLock lock;
    private AtomicBoolean flag;

    Producer(LinkedBlockingQueue q, ReentrantLock lock, AtomicBoolean flag) {
        this.queue = q;
        this.lock = lock;
        this.flag = flag;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                queue.put(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            flag.set(true);
        }
    }
}
