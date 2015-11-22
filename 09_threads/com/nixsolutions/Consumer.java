package com.nixsolutions;


import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sergey on 20.11.2015.
 */
class Consumer implements Runnable {
    private ConcurrentLinkedQueue<Integer> queue;
    private boolean even;
    private ReentrantLock lock;
    private AtomicBoolean flag;
    private boolean aBoolean = false;

    Consumer(ConcurrentLinkedQueue<Integer> queue, ReentrantLock lock, boolean even, AtomicBoolean flag) {
        this.queue = queue;
        this.lock = lock;
        this.even = even;
        this.flag = flag;
    }

    public void run() {
        while (!aBoolean) {
            try {
                lock.lock();
                if (!queue.isEmpty()) {
                    if (even) {
                        if (queue.peek() % 2 == 0) {
                            System.out.println("Even: " + queue.poll());
                        }
                    } else {
                        if (queue.peek() % 2 == 1) {
                            System.out.println("UnEven: " + queue.poll());
                        }
                    }
                }
                if (flag.get()) {
                    aBoolean = queue.isEmpty();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }
}

