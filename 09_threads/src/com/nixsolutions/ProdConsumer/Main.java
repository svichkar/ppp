package com.nixsolutions.ProdConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sobolenko on 2/8/2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(100);
        int isStarted = 2;
        ExecutorService producerConsumerExecutor = Executors.newCachedThreadPool();
        Producer prod = new Producer(queue);
        ConsumerEven even = new ConsumerEven(queue);
        ConsumerOdd odd = new ConsumerOdd(queue);
        producerConsumerExecutor.submit(prod);
        producerConsumerExecutor.submit(even);
        producerConsumerExecutor.submit(odd);
        while (isStarted > 0) {
            if (queue.size() == 0 && prod.ready) {
                isStarted--;
                Thread.sleep(1000);
                if (isStarted == 0) {
                    even.close();
                    odd.close();
                }
            }
        }
        producerConsumerExecutor.shutdown();
    }
}
