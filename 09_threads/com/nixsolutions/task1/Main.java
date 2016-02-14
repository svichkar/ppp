package com.nixsolutions.task1;

import java.util.concurrent.LinkedBlockingQueue;

/** Starts threads for the Producer and two Consumer runnables in sequence */
public class Main {
    public static void main(String[] args) {
        LinkedBlockingQueue<Object> q = new LinkedBlockingQueue<Object>();
        Object lock = new Object();
        Producer p = new Producer(q, lock);
        Consumer c1 = new Consumer("ConsumerEven", true, q, lock);
        Consumer c2 = new Consumer("ConsumerOdd", false, q, lock);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}