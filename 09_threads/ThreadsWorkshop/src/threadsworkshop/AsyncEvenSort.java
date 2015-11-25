/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsworkshop;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mednorcom
 */
public class AsyncEvenSort {

    private ExecutorService execService;
    private BlockingQueue<Integer> queue = null;
    private int queueLength;
    private int threadPool;
    private int elementsCount;

    public ExecutorService getExecService() {
        return execService;
    }

    public void setExecService(ExecutorService execService) {
        this.execService = execService;
    }

    protected BlockingQueue<Integer> getQueue() {
        return queue;
    }

    protected void setQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }

    public int getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(int threadPool) {
        this.threadPool = threadPool;
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public void setElementsCount(int elementsCount) {
        this.elementsCount = elementsCount;
    }

    public AsyncEvenSort() {
        this.queueLength = 1000;
        this.elementsCount = 100;
        this.queue = new ArrayBlockingQueue<>(queueLength);
        this.threadPool = 3;
        this.execService = Executors.newFixedThreadPool(this.threadPool);
    }

    public AsyncEvenSort(ExecutorService execService, int queueLength, int threadPool, int elementsCount) {
        this.execService = execService;
        this.queueLength = queueLength;
        this.threadPool = threadPool;
        this.elementsCount = elementsCount;
        this.execService = Executors.newFixedThreadPool(this.threadPool);
    }

    public void start() {
        this.getExecService().submit(new Consumer(queue, true));
        this.getExecService().submit(new Consumer(queue, false));
        Future producer = this.getExecService().submit(new Producer(queue, this.getElementsCount()));
        try {
            while ((Integer) producer.get() != this.getElementsCount());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(AsyncEvenSort.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void stop() {
        while (!this.getExecService().isTerminated()) {
            if (this.getQueue().isEmpty()) {
                this.getExecService().shutdown();
                try {
                    this.getExecService().awaitTermination(1, TimeUnit.SECONDS);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AsyncEvenSort.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.getExecService().shutdownNow();
            }
        }
    }

}
