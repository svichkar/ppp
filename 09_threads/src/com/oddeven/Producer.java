package com.oddeven;

import java.util.Queue;
import java.util.Random;

/**
 * Created by pantiukhin on 2/12/2016.
 */
public class Producer extends Thread implements Runnable {
    private Queue<Integer> queue;
    private Random random = new Random();

    public Producer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (queue) {
                while (!queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.add(random.nextInt());
                System.out.println(i);
                queue.notifyAll();
            }
        }
    }
}

