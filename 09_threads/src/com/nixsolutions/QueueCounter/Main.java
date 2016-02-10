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
        Thread a = null;
        Thread b = null;
        Thread c = null;
        while (mainThread.getCounter().intValue() < 1000) {
            switch (mainThread.getCounter().intValue())
            {
                case 100:
                    if(a==null) {
                        a = new Thread(regularThreadA);
                        a.start();
                    }
                    break;
                case 300:
                    if(b==null) {
                        b = new Thread(regularThreadB);
                        b.start();
                    }
                    break;
                case 500:
                    if(c==null) {
                        c = new Thread(regularThreadC);
                        c.start();
                    }
                    break;
            }
        }
    }
}
