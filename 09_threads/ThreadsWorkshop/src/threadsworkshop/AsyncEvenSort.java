/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsworkshop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author mednorcom
 */
public class AsyncEvenSort {

    private ExecutorService execService;
    private BlockingQueue<Integer> queue = null;
    private int queueLength;
    private int elementsCount;
    private int consumerCount;
    private int producerCount;
    private AtomicInteger remainingDequeue;
    private AtomicInteger remainingInqueue;
    private List<Future> taskList;

    public List<Future> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Future> taskList) {
        this.taskList = taskList;
    }

    public AtomicInteger getRemainingInqueue() {
        return remainingInqueue;
    }

    public void setRemainingInqueue(AtomicInteger remainingInqueue) {
        this.remainingInqueue = remainingInqueue;
    }

    public AtomicInteger getRemainingDequeue() {
        return remainingDequeue;
    }

    public void setRemainingDequeue(AtomicInteger remainingDequeue) {
        this.remainingDequeue = remainingDequeue;
    }

    public int getConsumerCount() {
        return consumerCount;
    }

    public void setConsumerCount(int consumerCount) {
        this.consumerCount = consumerCount;
    }

    public int getProducerCount() {
        return producerCount;
    }

    public void setProducerCount(int producerCount) {
        this.producerCount = producerCount;
    }

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

    public int getElementsCount() {
        return elementsCount;
    }

    public void setElementsCount(int elementsCount) {
        this.elementsCount = elementsCount;
    }

    public AsyncEvenSort(int queueLength,
            int elementsCount, int consumerCount, int producerCount) {
        this.queueLength = queueLength;
        this.queue = new ArrayBlockingQueue<>(queueLength);
        this.elementsCount = elementsCount;
        this.remainingDequeue = new AtomicInteger(this.elementsCount);
        this.consumerCount = consumerCount;
        this.producerCount = producerCount;
        this.execService = Executors.newFixedThreadPool(this.consumerCount + this.producerCount);
        this.taskList = new ArrayList();
        this.remainingInqueue = new AtomicInteger(this.elementsCount);
    }

    public void start() {
        boolean even = true;
        for (int i = 0; i < this.getConsumerCount(); i++) {
            this.getTaskList().add(this.getExecService()
                    .submit(new Consumer(this.getQueue(), even, this.getRemainingDequeue())));
            even = !even;
        }
        for (int i = 0; i < this.getProducerCount(); i++) {
            this.getTaskList().add(this.getExecService()
                    .submit(new Producer(this.getQueue(), this.getRemainingInqueue())));
        }
        while (this.getTaskList().size() > 0) {
            this.getTaskList().removeIf((Future t) -> {
                if (t.isDone() || t.isCancelled()) {
                    return true;
                }
                return false;
            });
        }
        this.getExecService().shutdown();
    }

}
