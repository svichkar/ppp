/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsworkshop;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mednorcom
 */
public class Consumer implements Runnable {

    private BlockingQueue<Integer> queue;
    private static final Lock LOCK = new ReentrantLock();
    private boolean even;
    private AtomicInteger remainingDequeue;

    public AtomicInteger getRemainingDequeue() {
        return remainingDequeue;
    }

    protected BlockingQueue<Integer> getQueue() {
        return queue;
    }

    protected void setQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public Consumer(BlockingQueue<Integer> queue, boolean even, AtomicInteger remainingDequeue) {
        this.queue = queue;
        this.even = even;
        this.remainingDequeue = remainingDequeue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && this.remainingDequeue.get() > 0) {
            boolean locked = false;
            try {
                locked = this.LOCK.tryLock();
                if (!this.getQueue().isEmpty() && locked) {
                    if (this.even == (this.getQueue().element() % 2 == 0)) {
                        System.out.println("Consumer '" + (this.even ? "even" : "odd") + "': "
                                + this.getQueue().poll());
                        this.remainingDequeue.getAndDecrement();
                    }
                }
            } catch (RuntimeException ex) {
                throw new RuntimeException(ex);
            } finally {
                if (locked) {
                    this.LOCK.unlock();
                }
            }
        }
        System.out.print("Consumer: Done\n");
    }

    /*@Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (!this.getQueue().isEmpty()) {
                Integer a;
                a = this.getQueue().element();
                if (a != null) {
                    try {
                        Thread.sleep(Long.valueOf(new Random().nextInt(1000)));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (this.even == (a % 2 == 0)) {
                        if (this.even != 
                                (this.getQueue().peek() % 2 == 0)||this.getQueue().peek()==null) {
                            System.out.println("WrongNumber: " + this.getQueue().peek());
                        }
                        System.out.println("Consumer '" + (this.even ? "even" : "odd") + "': "
                                + this.getQueue().poll());
                    }
                }
            }
        }

    }*/
}
