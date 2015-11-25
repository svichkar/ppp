/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsworkshop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mednor
 */
public class DelayedStart {
    private AtomicInteger counter;
    private int countTo;
    private ScheduledExecutorService  execService;
    private int threadPool;

    public AtomicInteger getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter.set(counter);
    }

    public int getCountTo() {
        return countTo;
    }

    public void setCountTo(int countTo) {
        this.countTo = countTo;
    }

    public ScheduledExecutorService getExecService() {
        return execService;
    }

    public void setExecService(ScheduledExecutorService  execService) {
        this.execService = execService;
    }

    public int getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(int threadPool) {
        this.threadPool = threadPool;
    }

    public DelayedStart() {
        this.countTo = 1000;
        this.counter = new AtomicInteger(0);
        this.threadPool = 3;
        this.execService = Executors.newScheduledThreadPool(this.threadPool);
    }
    
    public void start ()
    {
        this.getExecService().scheduleAtFixedRate(new DelayedWorker(this.getCounter(), 100), 0, 1,
                TimeUnit.SECONDS);
        this.getExecService().scheduleAtFixedRate(new DelayedWorker(this.getCounter(), 300), 0, 1,
                TimeUnit.SECONDS);
        this.getExecService().scheduleAtFixedRate(new DelayedWorker(this.getCounter(), 500), 0, 1,
                TimeUnit.SECONDS);
        for (int i = 1; i <= this.getCountTo(); i++)
        {
            this.setCounter(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(DelayedStart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.getExecService().shutdownNow();
    }
}
