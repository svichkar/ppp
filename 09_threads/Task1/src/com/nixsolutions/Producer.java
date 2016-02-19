package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author Sirotkin Mikhail
 * Class describes Producer that put random integers to queue (100 times)
 * */
public class Producer implements Runnable {
    private BlockingQueue queue;
    private Random random = new Random();
    /**
     * This flag describes Producer condition (become 'true' if producer has already finished adding numbers to queue)
     */
    public boolean finished = false;
    /**
     * Constructor of class 'Producer'
     * @param queue
     */
    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    /**
     * Method run for executor
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                queue.put(produce());
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        finished = true;
    }

    /**
     * Produce method
     * @return random integer value
     */
    private int produce() {
        int rn = random.nextInt();
        System.out.println("Added to queue: " + rn);
        return rn;
    }
}