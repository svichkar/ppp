package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Rybkinrolla on 20.11.2015.
 */
public class CustomConsumer implements Runnable {
    private BlockingQueue<Integer> arrOfIntegers;
    private boolean isEven;
    private static boolean threadIsUp = true;
    private static int iteration = 0;

    public CustomConsumer(BlockingQueue<Integer> queue, boolean isEven) {
        this.arrOfIntegers = queue;
        this.isEven = isEven;
    }

    private void stopConsume() {
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
                this.stopConsume();
            }
            if (iteration == 100) {
                this.stopConsume();
            }
        }
    }

    private void consume() throws InterruptedException {
        System.out.println("Consumer took:" + arrOfIntegers.take() + " with "
                + Thread.currentThread().getName());
        iteration++;
    }
}