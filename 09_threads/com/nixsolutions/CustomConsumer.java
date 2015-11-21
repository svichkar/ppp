package com.nixsolutions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Rybkinrolla on 20.11.2015.
 */
public class CustomConsumer implements Runnable {
    private BlockingQueue<Integer> arrOfIntegers = new LinkedBlockingQueue<>();
    private boolean isEven;
    private ReentrantLock lock;
    private volatile boolean threadIsUp = true;

    public CustomConsumer(BlockingQueue<Integer> queue, ReentrantLock lock, boolean isEven) {
        this.arrOfIntegers = queue;
        this.isEven = isEven;
        this.lock = lock;
    }

    public void stopConsume() {
        threadIsUp = false;
    }

    @Override
    public void run() {
        while (threadIsUp) {
            lock.lock();
            try {
                if (!arrOfIntegers.isEmpty()) {
                    if (isEven) {
                        if (arrOfIntegers.peek() % 2 == 0) {
                            this.consume();
                        }
                    } else {
                        if (arrOfIntegers.peek() % 2 != 0) {
                            this.consume();
                        }
                    }
                }
            } catch (InterruptedException e) {
                this.stopConsume();
            } finally {
                lock.unlock();
            }

        }
    }

    private void consume() throws InterruptedException{
        System.out.println("Consumer took:" + arrOfIntegers.take() + " with "
                + Thread.currentThread().getName());
    }
}

