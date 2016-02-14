package com.nixsolutions.QueueCounter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sobolenko on 2/9/2016.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        RegularThreads regularThreadA = new RegularThreads("A", counter);
        RegularThreads regularThreadB = new RegularThreads("B", counter);
        RegularThreads regularThreadC = new RegularThreads("C", counter);

        for (int i = 0; counter.intValue() < 1000; i++) {
            Thread.sleep(100);
            counter.addAndGet(1);
            switch (counter.intValue()) {
                case 100:
                    new Thread(regularThreadA).start();
                    break;
                case 300:
                    new Thread(regularThreadB).start();
                    break;
                case 500:
                    new Thread(regularThreadC).start();
                    break;
            }
        }
    }
}
