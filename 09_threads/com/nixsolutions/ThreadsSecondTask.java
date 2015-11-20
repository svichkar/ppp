package com.nixsolutions;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Sergey on 20.11.2015.
 */
public class ThreadsSecondTask {
    public static void main(String[] args) {
        AtomicBoolean flag = new AtomicBoolean(false);
        ThreadsForSecondTask thread1 = new ThreadsForSecondTask("first", flag);
        ThreadsForSecondTask thread2 = new ThreadsForSecondTask("second", flag);
        ThreadsForSecondTask thread3 = new ThreadsForSecondTask("third", flag);

        for (int i = 1; i <= 1000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 100) {
                new Thread(thread1).start();
            }
            if (i == 300) {
                new Thread(thread2).start();
            }
            if (i == 500) {
                new Thread(thread3).start();
            }
        }
        flag.set(true);
    }
}
