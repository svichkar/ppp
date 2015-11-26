package com.nixsolutions;


import java.util.concurrent.BlockingQueue;

/**
 * Created by Sergey on 20.11.2015.
 */
class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;
    private boolean even;

    Consumer(BlockingQueue<Integer> queue, boolean even) {
        this.queue = queue;
        this.even = even;
    }

    public void run() {
        while (queue.peek() == null) ;
        while (queue.peek() != null) {
            try {
                if (even) {
                    if (queue.peek() % 2 == 0) {
                        System.out.println("Even: " + queue.take());
                    }
                } else {
                    if (queue.peek() % 2 == 1) {
                        System.out.println("UnEven: " + queue.take());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

