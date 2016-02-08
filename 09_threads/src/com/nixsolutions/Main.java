package com.nixsolutions;

import java.util.concurrent.*;

/**
 * Created by sobolenko on 2/8/2016.
 */
public class Main {
    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(100);
        ExecutorService producer = Executors.newSingleThreadExecutor();
        ExecutorService consumer = Executors.newCachedThreadPool();
        Runnable consumer1 = new Consumer(queue);
        Runnable consumer2 = new Consumer2(queue);
        consumer.submit(consumer1);
        consumer.submit(consumer2);
        producer.submit(new Producer(queue));

        //consumer.shutdown();
        /*Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        Thread consumer2 = new Thread(new Consumer2(queue));*/
        //producer.start();
        //consumer.start();
        //consumer2.start();

    }
}
