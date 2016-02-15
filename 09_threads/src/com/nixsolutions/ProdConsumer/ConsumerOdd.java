package com.nixsolutions.ProdConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * Created by sobolenko on 2/8/2016.
 */
public class ConsumerOdd implements Runnable {
    BlockingQueue queue;
    public boolean isStart = true;

    public ConsumerOdd(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        while (isStart) {
            Integer num = null;
            try {
                consume(queue.peek());
            } catch (NullPointerException ne) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                Thread.interrupted();
                e.printStackTrace();
            }
        }
    }

    void consume(Object x) throws InterruptedException {
        int num = (Integer) x;
        if (num % 2 != 0) {
            System.out.println("Consumer, number is odd:  " + queue.take());
        }
    }

    public void close() {
        isStart = false;
    }
}
