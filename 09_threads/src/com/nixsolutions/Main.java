package com.nixsolutions;

import java.util.concurrent.*;

/**
 * Created by sobolenko on 2/8/2016.
 */
public class Main {
    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(100);
        Boolean isStarted=true;
        Producer prod = new Producer(queue);
        ConsumerEven even = new ConsumerEven(queue);
        ConsumerOdd odd = new ConsumerOdd(queue);
        ExecutorService producer = Executors.newSingleThreadExecutor();
        ExecutorService consumer = Executors.newCachedThreadPool();
        producer.submit(prod);
        Runnable consumer1 = even;
        Runnable consumer2 = odd;
        consumer.submit(consumer1);
        consumer.submit(consumer2);

        while (isStarted)
        if(prod.getQueue().size()==0  && prod.ready){
            even.close();
            odd.close();
            producer.shutdown();
            consumer.shutdown();
            isStarted = false;
        }

    }
}
