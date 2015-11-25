/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsworkshop;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mednorcom
 */
public class Consumer implements Runnable {

    private BlockingQueue<Integer> queue;
    private boolean even;

    protected BlockingQueue<Integer> getQueue() {
        return queue;
    }

    protected void setQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public Consumer(BlockingQueue<Integer> queue, boolean even) {
        this.queue = queue;
        this.even = even;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            Lock lock = new ReentrantLock();
            boolean locked = false;
            try {
                locked = lock.tryLock();
                if (!this.getQueue().isEmpty()) {
                    if (this.even == (this.getQueue().element() % 2 == 0)) {
                        System.out.println("Consumer '" + (this.even ? "even" : "odd") + "': " 
                                + this.getQueue().poll());
                    }
                }
            } catch (RuntimeException ex) {
                throw new RuntimeException(ex);
            } finally {
                if (locked) {
                    lock.unlock();
                }
            }
        }
    }

}
