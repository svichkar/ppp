package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Rybkinrolla on 20.11.2015.
 */
public class CustomConsumer implements Runnable {
    private BlockingQueue<Integer> arrOfIntegers;
    private boolean isEven;
    private static boolean threadIsUp = true;

    public CustomConsumer(BlockingQueue<Integer> queue, boolean isEven) {
        this.arrOfIntegers = queue;
        this.isEven = isEven;
    }

    public static void stopAllConsumers() {
        threadIsUp = false;
    }

    @Override
    public void run() {
        while (threadIsUp) {
            try {
                Integer num = arrOfIntegers.peek();
                if (num != null) {
                    if (isEven) {
                        if (num % 2 == 0) {
                            consume();
                        }
                    } else {
                        if (num % 2 != 0) {
                            consume();
                        }
                    }
                }
            } catch (InterruptedException e) {
                stopAllConsumers();
            }
        }
    }
    private void consume() throws InterruptedException {
        System.out.println("Consumer took:" + arrOfIntegers.take() + " with "
                + Thread.currentThread().getName());
    }
}