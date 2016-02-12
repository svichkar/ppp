package com.nixsolutions.task2;

/**
 * Increments the counter from 1 to 1000 every 100ms; if counter equals to
 * specified constant values, runs a new thread.
 */
public class Main {
    private static final int[] MARK = { 100, 300, 500 };

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 1000; i++) {
            Thread.sleep(100);
            System.out.println("Main: counter is " + i);
            for (int j = 0; j < MARK.length; j++) {
                if (MARK[j] == i) {
                    (new Thread(new StatusReporter(), "Thread" + j)).start();
                }
            }
        }
    }

}
