package com.nixsolutions;

import java.awt.image.AffineTransformOp;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sergey on 20.11.2015.
 */
class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;
    private boolean even;
    private ReentrantLock lock;
    private AtomicBoolean flag;
    boolean aBoolean = false;

    Consumer(LinkedBlockingQueue<Integer> q, ReentrantLock lock, boolean even, AtomicBoolean flag) {
        this.queue = q;
        this.lock = lock;
        this.even = even;
        this.flag = flag;
    }

    public void run() {
        while (!aBoolean) {
            lock.lock();
            try {
                if (!queue.isEmpty()) {
                    if (even) {
                        if (queue.peek() % 2 == 0) {
                            System.out.println("Even: " + queue.take());
                        }
                    } else {
                        if (queue.peek() % 2 == 1) {
                            System.out.println("UnEven: " + queue.take());
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (flag.get()) {
                    aBoolean = queue.isEmpty();
                }
                lock.unlock();
            }
        }
    }
}
