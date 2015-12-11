package com.nixsolutions.third.task1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by svichkar on 11/23/2015.
 */
public class Main {

    public static final int N = 100;

    public static void main(String args[]) {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(N);

        Producer producer = new Producer(queue);
        EvenConsumer evenConsumer = new EvenConsumer(queue);
        OddConsumer oddConsumer = new OddConsumer(queue);

        ExecutorService service = Executors.newFixedThreadPool(3);

        service.execute(producer);
        service.execute(oddConsumer);
        service.execute(evenConsumer);

        service.shutdown();
    }

}
