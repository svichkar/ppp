package com.oddeven;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by pantiukhin on 2/16/2016.
 */
public class ConsumerProducer {
    private static Random random = new Random();
    private static int counter = 0;

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(100, true);
        new Thread(new Producer(queue), "producer").start();
        new Thread(new Consumer(queue), "consumerOdd").start();
        new Thread(new Consumer(queue), "consumerEven").start();
    }

    private static class Producer implements Runnable {

        BlockingQueue<Integer> queue;

        public Producer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (counter < 100) {
                try {
                    queue.put(random.nextInt());
                    System.out.println(counter);
                    Thread.sleep(1000);
                    counter++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {

                while (counter < 100) {
                    if (!queue.isEmpty()) {
                        if (Thread.currentThread().getName().equals("consumerEven")) {
                            if (queue.element() % 2 == 0) {
                                System.out.println(Thread.currentThread().getName() + " removed a number: " + queue.take());
                            }
                        }
                        if (Thread.currentThread().getName().equals("consumerOdd")) {
                            if (queue.element() % 2 != 0) {
                                System.out.println(Thread.currentThread().getName() + " removed a number: " + queue.take());
                            }
                        }
                    }
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
