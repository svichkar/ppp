package com.nixsolutions.task1;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represents the producer: generates 100 random integers and adds them to the
 * LinkedBlockingQueue.
 */
public class Producer implements Runnable {
    private static final int ITERATIONS = 100;
    private static final int RAND_FROM_0_TO_MAX = 100;
    private final LinkedBlockingQueue<Object> queue;
    private Object lock;
    private static Random random = new Random();

    /**
     * Constructor for the producer.
     * 
     * @param q
     *            Queue to work with.
     * @param o
     *            Object lock to synchronize the work.
     */
    public Producer(LinkedBlockingQueue<Object> q, Object o) {
        queue = q;
        lock = o;
    }

    public void run() {
        for (int i = 1; i <= ITERATIONS; i++) {
            int number = random.nextInt(RAND_FROM_0_TO_MAX); // Value excluded. 
            try {
                queue.put(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producer added " + number + " to the queue on "
                    + "iteration " + i + ".");
            synchronized (lock) {
                lock.notifyAll();
            }
        }
        try {
            queue.put("FINISHED");
            System.out.println("Producer finished the job and exited.");
            synchronized (lock) {
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}