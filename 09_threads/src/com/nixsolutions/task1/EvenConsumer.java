package com.nixsolutions.task1;

import java.util.concurrent.BlockingQueue;

/**
 * Created by svichkar on 11/23/2015.
 */
public class EvenConsumer implements Runnable {

    private BlockingQueue<Integer> queue;

    public EvenConsumer(BlockingQueue<Integer> blockingQueue) {
        this.queue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            Integer element = queue.peek();
            if (element != null && element % 2 == 0) {
                System.out.println(String.format("even element: %s", queue.poll()));
            }
        }
    }
}
