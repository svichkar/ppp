package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

/**
 * Created by sobolenko on 2/8/2016.
 */
public class ConsumerOdd implements Runnable {
    // ConcurrentLinkedQueue<Integer> queue;
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
                consume(queue.take());
            } catch (NullPointerException ne) {
                //ne.printStackTrace();
                try {
                    //Thread.sleep(1000);
                    consume(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void consume(Object x) throws InterruptedException {
        int num = (Integer) x;
        if (num % 2 != 0) {
            System.out.println("Tread2 " + num);
        }
        else queue.put(num);
    }

    public void close()
    {
        isStart=false;
    }
}
