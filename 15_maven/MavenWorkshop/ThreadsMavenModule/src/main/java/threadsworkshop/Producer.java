/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsworkshop;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author mednorcom
 */
public class Producer implements Runnable {

    private BlockingQueue<Integer> queue;
    private AtomicInteger remainingInQueue;

    public AtomicInteger getRemainingInQueue() {
        return remainingInQueue;
    }

    protected BlockingQueue<Integer> getQueue() {
        return queue;
    }

    protected void setQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public Producer(BlockingQueue<Integer> queue, AtomicInteger remainingInQueue) {
        this.queue = queue;
        this.remainingInQueue = remainingInQueue;
    }

    @Override
    public void run() {
        int i;
        while (this.getRemainingInQueue().getAndDecrement() > 0) {

            this.getQueue().add(new Random().nextInt());
        }

    }

}
