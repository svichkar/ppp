package com.nixsolutions.task1;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represents the producer: generates 100 random integers and adds them to the
 * queue, notifying consumers each update.
 */
public class Producer implements Runnable {
    private final int ITERATIONS = 100;
    private final int RAND_FROM_0_TO_MAX = 100;
    private final LinkedBlockingQueue<Object> queue;
    private final Object lock;
    private final Random random = new Random();

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
        try {
            for (int i = 1; i <= ITERATIONS; i++) {
                // nextInt's parameter itself never included in returned value.
                int number = random.nextInt(RAND_FROM_0_TO_MAX);
                queue.put(number);
                System.out.println("Producer: added " + number
                        + " to the queue on " + "iteration " + i + ".");

                synchronized (lock) {
                    lock.notifyAll();
                }
            }
            queue.put("FINISHED"); // Indicating the job is done.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Producer: finished the job and exiting.");
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}