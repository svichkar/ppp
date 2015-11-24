package com.nixsolutions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by svichkar on 11/23/2015.
 */
public class EvenConsumer implements Runnable {

    protected BlockingQueue<Integer> queue;
    protected AtomicBoolean isFull;

    public EvenConsumer(BlockingQueue<Integer> blockingQueue, AtomicBoolean isFull) {
        queue = blockingQueue;
        this.isFull = isFull;
    }

    @Override
    public void run() {

        while (isFull.get() == false || queue.isEmpty() == false) {

            Integer element = queue.peek();

            if (element != null && element % 2 == 0) {
                System.out.println(String.format("even element: %s", queue.poll()));
            }
        }
    }
}
