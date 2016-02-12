package com.nixsolutions.task2;

public class StatusReporter implements Runnable {
    
public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ": working.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
