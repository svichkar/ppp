package com.manetskiy.task1;

import java.util.concurrent.BlockingQueue;

public class NumbersConsumer implements Runnable {
    private BlockingQueue<Number> queue;
    private boolean even = false;

    public NumbersConsumer(BlockingQueue<Number> queue, boolean even) {
        this.queue = queue;
        this.even = even;
    }

    public void run() {
        boolean done = false;
        while (!done) {
            try {
                /*
                The end of queue contains "dummy" object which invokes
                ClassCastException during casting to Integer.
                 */
                Integer numbr = (Integer) queue.peek();
                if (even) {
                    if ((numbr % 2) == 0)
                        System.out.println(Main.counter.incrementAndGet() + ". Even: " + queue.take());
                }
                if (!even) {
                    if ((numbr % 2) != 0)
                        System.out.println(Main.counter.incrementAndGet() + ". Odd: " + queue.take());
                }
            } catch (ClassCastException e) {
                done = true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
