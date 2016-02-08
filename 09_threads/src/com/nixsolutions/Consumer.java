package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

/**
 * Created by sobolenko on 2/8/2016.
 */
public class Consumer implements Runnable {
    //ConcurrentLinkedQueue<Integer> queue;
    BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10 && queue.peek() != null; i++) {
            Integer num = null;
            try {
                consume(queue.peek());
            } catch (NullPointerException ne) {
                ne.printStackTrace();
            }
        }
    }

    void consume(Object x) {
        int num = (Integer) x;
        if (num % 2 == 0) {
            System.out.println("Tread1 " + num);
            queue.poll();
        } else queue.poll();
    }
}
