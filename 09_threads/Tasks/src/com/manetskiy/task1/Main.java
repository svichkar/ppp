package com.manetskiy.task1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        BlockingQueue<Number> queue = new ArrayBlockingQueue<Number>(100);
        NumbersProducer numberProducer = new NumbersProducer(queue);
        NumbersConsumer evenConsumer = new NumbersConsumer(queue, true);
        NumbersConsumer oddConsumer = new NumbersConsumer(queue, false);

        new Thread(numberProducer).start();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(evenConsumer).start();
        new Thread(oddConsumer).start();

    }
}
