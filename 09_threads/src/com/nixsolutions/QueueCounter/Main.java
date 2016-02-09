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
        //ExecutorService threadsExecutor = Executors.newSingleThreadExecutor();
        MainThread mainThread = new MainThread(counter);
        RegularThreads regularThreadA = new RegularThreads("A",counter);
        RegularThreads regularThreadB = new RegularThreads("B",counter);
        RegularThreads regularThreadC = new RegularThreads("C",counter);
        new Thread(mainThread).start();
        while (mainThread.getCounter().intValue() < 1000) {
            switch (mainThread.getCounter().intValue())
            {
                case 100:
                    Thread a = new Thread(regularThreadA);
                    a.start();
                    //counter.addAndGet(1);
                    break;
                case 300:
                    new Thread(regularThreadB).start();
                    //counter.addAndGet(1);
                    break;
                case 500:
                    new Thread(regularThreadC).start();
                    //counter.addAndGet(1);
                    break;
            }
        }
        //threadsExecutor.submit(regularThreadA, counter);
        //threadsExecutor.submit(regularThreadB, counter);
        //threadsExecutor.submit(regularThreadC, counter);

    }
}
