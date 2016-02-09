package com.nixsolutions.QueueCounter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sobolenko on 2/9/2016.
 */
public class Main {

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);
        ExecutorService threadsExecutor = Executors.newFixedThreadPool(4);
        MainThread mainThread = new MainThread(counter);
        RegularThreads regularThreadA = new RegularThreads("A", counter, 100);
        RegularThreads regularThreadB = new RegularThreads("B", counter, 300);
        RegularThreads regularThreadC = new RegularThreads("C", counter, 500);
        threadsExecutor.submit(mainThread);
        threadsExecutor.submit(regularThreadA, counter);
        threadsExecutor.submit(regularThreadB, counter);
        threadsExecutor.submit(regularThreadC, counter);

    }
}
