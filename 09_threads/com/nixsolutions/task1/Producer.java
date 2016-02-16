package com.nixsolutions.task1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Represents the producer: generates 100 random integers and adds them to the
 * queue, notifying consumers each update.
 */
public class Producer implements Runnable {
    private final int ITERATIONS = 100;
    private final int RAND_FROM_0_TO_MAX = 100;
    private final BlockingQueue<Object> queue;
    private final Random random = new Random();

    /**
     * Constructor for the producer.
     * 
     * @param sharedQueue
     *            Queue to work with.
     */
    public Producer(BlockingQueue<Object> sharedQueue) {
        queue = sharedQueue;
    }

    public void run() {
        try {
            for (int i = 1; i <= ITERATIONS; i++) {
                // nextInt's parameter itself never included in returned value.
                int number = random.nextInt(RAND_FROM_0_TO_MAX);
                synchronized (queue) {
                    queue.put(number);
                    System.out.println("Producer: added " + number
                            + " to the queue on " + "iteration " + i + ".");
                    queue.notifyAll();
                }
            }
            synchronized (queue) {
                queue.put("FINISHED"); // Indicating the job is done.
                System.out.println("Producer: finished the job and exiting.");
                queue.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}