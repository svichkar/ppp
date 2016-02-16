package com.nixsolutions.task1;

import java.util.concurrent.BlockingQueue;

/**
 * Represents the consumer: takes either even or odd numbers from
 * LinkedBlockingQueue, based on value of private field.
 */
public class Consumer implements Runnable {
    private final BlockingQueue<Object> queue;
    private final boolean parity;
    private final String name;
    private boolean finished;

    /**
     * Constructor for the consumer.
     * 
     * @param consumerName
     *            Name of the object.
     * @param consumerParity
     *            true if consumes even numbers, false if odd ones.
     * @param sharedQueue
     *            Queue to work with.
     */
    public Consumer(String consumerName, boolean consumerParity,
            BlockingQueue<Object> sharedQueue) {
        name = consumerName;
        parity = consumerParity;
        queue = sharedQueue;
    }

    public void run() {
        while (finished == false) {
            try {
                tryConsumer(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static final void tryConsumer(Consumer c)
            throws InterruptedException {
        Object o = c.queue.peek();
        synchronized (c.queue) {
            if ((o == null)) {
                // Waiting if Producer hasn't put any number yet.
                c.queue.wait();
            } else if ((o.getClass().equals(Integer.class)) && (((Integer) o
                    & 1) == 0) == c.parity) {
                // Consuming only number corresponding to the instance parity.
                System.out.println(c.name + ": took " + c.queue.take()
                        + " from the queue.");
            } else if ((o.getClass().equals(String.class)) && (((String) o)
                    .equals("FINISHED"))) {
                // Stopping on flag that indicates the Producer has stopped.
                System.out.println(c.name + ": finished the job and exited.");
                c.finished = true;
            }
        }
    }
}