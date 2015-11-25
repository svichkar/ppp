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
        for (Integer i : queue) {
            Integer temp = i;
            if (even) {
                if (temp % 2 == 0) {
                    System.out.println("Even: " + temp);
                }
            } else {
                if (temp % 2 == 1) {
                    System.out.println("UnEven: " + temp);
                }
            }
        }
    }
}

