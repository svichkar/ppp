package com.nixsolutions.task1;

import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable {
    private final LinkedBlockingQueue<Integer> queue;
    private final boolean parity;
    private String name;

    public Consumer(String s, boolean b, LinkedBlockingQueue<Integer> q) {
        name = s;
        parity = b;
        queue = q;
    }

    public void run() {
        try {
            while ((queue.peek() != null)) {
                if (((queue.peek() & 1) == 0) == parity) {
                    Integer i = queue.take();
                    System.out.println(name + " took " + i + " from queue.");
                }
            }
            System.out.println(name + " finished.");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}