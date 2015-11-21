package com.nixsolutions;

/**
 * Created by Rybkinrolla on 21.11.2015.
 */
public class CustomRunnableTest {
    public static void main(String[] args) {
        int i = 0;
        while (i <= 1000) {
            try {
                System.out.println(i);
                Thread.currentThread().sleep(100);
                i++;
                if (i == 1000) {
                    CustomRunnable.stopRun();
                }
                if (i == 100 || i == 300 || i == 500) {
                    new Thread(new CustomRunnable()).start();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}