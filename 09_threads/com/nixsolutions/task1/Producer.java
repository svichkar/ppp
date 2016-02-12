package com.nixsolutions.task1;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable {
    private static final int ITERATIONS = 100;
    private final LinkedBlockingQueue<Integer> queue;
    private static Random random = new Random();

    public Producer(LinkedBlockingQueue<Integer> q) {
        queue = q;
    }

    public void run() {
        try {
            for (int i = 1; i <= ITERATIONS; i++) {
                int number = random.nextInt(100);
                queue.put(number);
                System.out.println("Producer: added " + number
                        + " to the queue on " + "iteration " + i + ".");
            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}