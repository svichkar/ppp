package com.nixsolutions;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kozlovskij on 11/20/2015.
 */

public class ThreadsFirstTask {
    public static void main(String[] args) {
        int capacity = 100;
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(capacity);

        Producer producer = new Producer(queue);
        Consumer evenConsumer = new Consumer(queue, true);
        Consumer unEvenConsumer = new Consumer(queue, false);

        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(producer);
        service.submit(evenConsumer);
        service.submit(unEvenConsumer);
        service.shutdownNow();
    }
}
