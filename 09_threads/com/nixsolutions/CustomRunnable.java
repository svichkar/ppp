package com.nixsolutions;

/**
 * Created by Rybkinrolla on 21.11.2015.
 */
public class CustomRunnable implements Runnable {
    private static volatile boolean flag;

    public CustomRunnable() {
        flag = true;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                System.out.println("I'm working " + Thread.currentThread().getName());
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                stopRun();
            }
        }
    }

    public static void stopRun() {
        flag = false;
    }
}
