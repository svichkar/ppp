package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by kozlovskij on 11/20/2015.
 */

class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    Random random = new Random();

    Producer(LinkedBlockingQueue q) {
        this.queue = q;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                queue.put(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final boolean even;

    Consumer(LinkedBlockingQueue<Integer> q, boolean even) {
        this.queue = q;
        this.even = even;
    }

    public void run() {
        while (true) {
            try {
                if (even) {
                    if (queue.element() % 2 == 0) {
                        System.out.println("Even: " + queue.take());
                    }
                } else {
                    if (queue.element() % 2 == 1) {
                        System.out.println("UnEven: " + queue.take());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadsFirstTask {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        Producer producer = new Producer(queue);
        Consumer consumer1 = new Consumer(queue, true);
        Consumer consumer2 = new Consumer(queue, false);

        Thread prod = new Thread(producer);
        Thread c1 = new Thread(consumer1);
        Thread c2 = new Thread(consumer2);

        prod.setName("prod");
        c1.setName("c1");
        c2.setName("c2");

        prod.start();
        c1.start();
        c2.start();
    }
}
