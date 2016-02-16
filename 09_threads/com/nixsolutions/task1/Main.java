package com.nixsolutions.task1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/** Starts threads for the Producer and two Consumer runnables in sequence */
public class Main {
    public static void main(String[] args) {
        BlockingQueue<Object> queue = new LinkedBlockingQueue<Object>();
        Producer p = new Producer(queue);
        Consumer c1 = new Consumer("ConsumerEven", true, queue);
        Consumer c2 = new Consumer("ConsumerOdd", false, queue);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}