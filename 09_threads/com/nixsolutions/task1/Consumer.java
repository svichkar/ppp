package com.nixsolutions.task1;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represents the consumer: takes either even or odd numbers from
 * LinkedBlockingQueue, based on value of private field.
 */
public class Consumer implements Runnable {
    private final LinkedBlockingQueue<Object> queue;
    private final boolean parity;
    private final String name;
    private final Object lock;
    private boolean finished;

    /**
     * Constructor for the consumer.
     * 
     * @param s
     *            Name of the object.
     * @param b
     *            true if consumes even numbers, false if odd ones.
     * @param q
     *            Queue to work with.
     * @param o
     *            Object lock to synchronize the work.
     */
    public Consumer(String s, boolean b, LinkedBlockingQueue<Object> q,
            Object o) {
        name = s;
        parity = b;
        queue = q;
        lock = o;
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
        synchronized (c.lock) {
            Object o = c.queue.peek();

            /*
             * Waiting if Producer either not started, or hasn't put a new
             * number yet.
             */
            if ((o == null)) {
                c.lock.wait();
            } else

            /*
             * Checking if the head of the queue is an Integer and is either
             * even or odd, depending on the parity field of the current
             * instance of a class.
             */
            if ((o.getClass().equals(Integer.class))
                    && (((Integer) o & 1) == 0) == c.parity) {
                System.out.println(c.name + ": took " + c.queue.take()
                        + " from the queue.");

            } else

            /*
             * Ending the work if it's a String which equals to the value
             * indicating the Producer has finished its work.
             */
            if ((o.getClass().equals(String.class))
                    && (((String) o).equals("FINISHED"))) {
                System.out.println(c.name + ": finished the job and exited.");
                c.finished = true;
            }
        }
    }
}