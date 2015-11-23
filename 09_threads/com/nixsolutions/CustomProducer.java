package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Rybkinrolla on 20.11.2015.
 */
public class CustomProducer implements Runnable {
    private BlockingQueue<Integer> arrOfIntegers;

    public CustomProducer(BlockingQueue<Integer> queue) {
        this.arrOfIntegers = queue;
    }

    private int randomInteger() {
        Random generator = new Random();
        return generator.nextInt(100);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int newInt = randomInteger();
            arrOfIntegers.add(newInt);
            System.out.println("Producer created: " + newInt);
        }
    }
}
