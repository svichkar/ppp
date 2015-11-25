package com.nixsolutions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Sergey on 20.11.2015.
 */
public class ThreadsSecondTask {
    public static void main(String[] args) {
        ThreadsForSecondTask thread1 = new ThreadsForSecondTask("first");
        ThreadsForSecondTask thread2 = new ThreadsForSecondTask("second");
        ThreadsForSecondTask thread3 = new ThreadsForSecondTask("third");

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();


        for (int i = 1; i <= 1000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 100) {
                service.scheduleAtFixedRate(thread1, 0, 1, TimeUnit.SECONDS);
            }
            if (i == 300) {
                service.scheduleAtFixedRate(thread2, 0, 1, TimeUnit.SECONDS);
            }
            if (i == 500) {
                service.scheduleAtFixedRate(thread3, 0, 1, TimeUnit.SECONDS);
            }
        }
        service.shutdownNow();
    }
}
