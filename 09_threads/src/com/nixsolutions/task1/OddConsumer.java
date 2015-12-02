package com.nixsolutions.task1;

import java.util.concurrent.BlockingQueue;

/**
 * Created by svichkar on 11/23/2015.
 */
public class OddConsumer implements Runnable {

    private BlockingQueue<Integer> queue;

    public OddConsumer(BlockingQueue<Integer> blockingQueue) {
        this.queue = blockingQueue;
    }

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
