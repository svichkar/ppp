package com.manetskiy.task2;

public class Worker implements Runnable {

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " is working...");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " finished its work.");
                Thread.currentThread().interrupt();
            }
        }
    }
}