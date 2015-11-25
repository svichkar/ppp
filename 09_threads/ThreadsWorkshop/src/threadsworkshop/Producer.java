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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mednorcom
 */
public class Producer implements Callable {

    private BlockingQueue<Integer> queue;
    private int elementsCount;
    private AtomicInteger generatedNumbers;

    protected BlockingQueue<Integer> getQueue() {
        return queue;
    }

    protected void setQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public void setElementsCount(int elementsCount) {
        this.elementsCount = elementsCount;
    }

    public Producer(BlockingQueue<Integer> queue, int elementsCount) {
        this.queue = queue;
        this.elementsCount = elementsCount;
    }


    @Override
    public Integer call() throws Exception {
        int i;
        for (i = 0; i < this.getElementsCount(); i++) {
            Lock lock = new ReentrantLock();
            boolean locked = false;
            try {
                this.getQueue().add(new Random().nextInt());
            } finally {
                if (locked) {
                    lock.unlock();
                }
            }
        }
        return i;
    }
    
}
