package com.nixsolutions;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kozlovskij on 11/20/2015.
 */

public class ThreadsFirstTask {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        ReentrantLock lock = new ReentrantLock();
        AtomicBoolean flag = new AtomicBoolean(false);

        Producer producer = new Producer(queue, lock, flag);
        Consumer consumer1 = new Consumer(queue, lock, true, flag);
        Consumer consumer2 = new Consumer(queue, lock, false, flag);

        Thread prod = new Thread(producer);
        Thread c1 = new Thread(consumer1);
        Thread c2 = new Thread(consumer2);

        prod.start();
        c1.start();
        c2.start();
    }
}
