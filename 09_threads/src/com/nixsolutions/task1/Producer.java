package com.nixsolutions.task1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by svichkar on 11/23/2015.
 */
public class Producer implements Runnable {

    public static final int N = 100;

    protected BlockingQueue<Integer> queue;
    protected AtomicInteger remainingCapacity;
    protected AtomicBoolean isFull;

    /**
     * Producer's constructor
     */
    public Producer() {
        this.queue = new ArrayBlockingQueue<>(N);
        this.remainingCapacity = new AtomicInteger(queue.remainingCapacity());
        this.isFull = new AtomicBoolean(false);
    }

    /**
     * getter for BlockingQueue queue
     * @return queue
     */
    public BlockingQueue<Integer> getQueue() {
        return this.queue;
    }

    /**
     * method which fills queue with 100 random Integer elements
     */
    @Override
    public void run() {

        try {
            while (remainingCapacity.get() > 0) {
                queue.put(ThreadLocalRandom.current().nextInt());
                remainingCapacity.decrementAndGet();
            }
            isFull.set(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for isFull which indicates filled queue
     *
     * @return true or false
     */
    public AtomicBoolean getReady() {
        return this.isFull;
    }
}
