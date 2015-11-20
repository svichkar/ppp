package com.nixsolutions;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kozlovskij on 11/20/2015.
 */

public class ThreadsFirstTask {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        ReentrantLock lock = new ReentrantLock();
        AtomicBoolean flag = new AtomicBoolean(false);

        Producer producer = new Producer(queue, lock, flag);
        Consumer evenConsumer = new Consumer(queue, lock, true, flag);
        Consumer unEvenConsumer = new Consumer(queue, lock, false, flag);

        new Thread(producer).start();
        new Thread(evenConsumer).start();
        new Thread(unEvenConsumer).start();
    }
}
