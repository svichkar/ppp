package com.manetskiy.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        int counter = 0;
        while (counter < 1000) {
            counter++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter == 100)
                service.submit(new Worker());
            if (counter == 300)
                service.submit(new Worker());
            if (counter == 500)
                service.submit(new Worker());
        }
        service.shutdownNow();
    }
}