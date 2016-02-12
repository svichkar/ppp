package com.oddeven;

import java.util.Iterator;
import java.util.Queue;

/**
 * Created by pantiukhin on 2/12/2016.
 */
public class ConsumerTwo implements Runnable {
    private Queue<Integer> queue;
    int num;

    public ConsumerTwo(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                synchronized (queue) {
                    while (queue.isEmpty())
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    Iterator iterator = queue.iterator();
                    if (iterator.hasNext()) {
                        num = (Integer) iterator.next();
                    }
                    if ((num % 2) == 0) {
                        System.out.println("Thread two has removed an even number: " + num);
                        queue.remove();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.notifyAll();
                }
            }
        }
    }
}
