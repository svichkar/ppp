package com.nixsolutions;

import java.util.concurrent.*;

/**
 * Created by kozlovskij on 11/20/2015.
 */

public class ThreadsFirstTask {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        Producer producer = new Producer(queue);
        Consumer evenConsumer = new Consumer(queue, true);
        Consumer unEvenConsumer = new Consumer(queue, false);

        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(producer);
        service.submit(evenConsumer);
        service.submit(unEvenConsumer);
        service.shutdown();
    }
}
