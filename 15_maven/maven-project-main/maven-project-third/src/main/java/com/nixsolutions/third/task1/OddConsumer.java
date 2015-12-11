package com.nixsolutions.third.task1;

import java.util.concurrent.BlockingQueue;

/**
 * Created by svichkar on 11/23/2015.
 */
public class OddConsumer implements Runnable {

    private BlockingQueue<Integer> queue;

    /**
     * OddConsumer's constructor
     * @param blockingQueue
     */
    public OddConsumer(BlockingQueue<Integer> blockingQueue) {
        this.queue = blockingQueue;
    }

    /**
     * overrided run method
     * which polls odd numbers from queue
     */
    @Override
    public void run() {
        while (true) {
            Integer element = queue.peek();
            if (element != null && element % 2 != 0) {
                System.out.println(String.format("odd element: %s", queue.poll()));
            }
        }
    }
}
