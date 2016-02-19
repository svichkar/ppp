package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sirotkin Mikhail
 * Class for checking Producer-Consumer task
 * */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue queue = new ArrayBlockingQueue(100);

        //add executor for 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Producer prod = new Producer(queue);
        Consumer even = new Consumer(queue, true);
        Consumer odd = new Consumer(queue, false);
        executor.submit(prod);
        executor.submit(even);
        executor.submit(odd);

        //Waiting until producer finished adding numbers to queue
        //During this time all 3 threads are working
        while (!prod.finished){
            Thread.sleep(1000);
        }
        //Waiting until queue become empty (until consumers took all numbers)
        //During this time Producer has already finished adding numbers to queue and consumers threads are still working
        while(queue.size()>0) {
            Thread.sleep(1000);
        }

        //just print out count of cought number for each consumer (even and odd)
        even.printCountOfCoghtNumbers();
        odd.printCountOfCoghtNumbers();

        //we could close our threads together just using executor method shutdown()
        executor.shutdown();
    }
}
