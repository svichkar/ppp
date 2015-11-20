package com.nixsolutions;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Sergey on 21.11.2015.
 */
public class ThreadsForSecondTask implements Runnable {
    private String name;
    private AtomicBoolean flag = new AtomicBoolean(false);

    public ThreadsForSecondTask(String name, AtomicBoolean flag) {
        this.name = name;
        this.flag = flag;
    }

    @Override
    public void run() {
        int i = 1;
        while (!flag.get()) {
            System.out.println(name + ": I'm working (" + i + " iteration)");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        System.out.println(name + ": I'm finished");
    }
}
