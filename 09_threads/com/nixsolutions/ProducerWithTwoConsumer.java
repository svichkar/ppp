package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Describe task: "Разработать приложение, где producer помещает в очередь 100 случайных чисел,
 * а 2 consumer’а ждут появления в очереди чисел - один четных, другой нечетных - забирают их и выводят в консоль"
 *
 * @author Dmitry Zinovyi
 */
public class ProducerWithTwoConsumer {

    private static final int CAPACITY = 100;
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(CAPACITY);

    public static void main(String[] argc) {
        new Thread(new Producer()).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Consumer(true)).start();
        new Thread(new Consumer(false)).start();

    }

    private static class Producer implements Runnable {

        @Override
        public void run() {
            Random random = new Random();
            for (int i = 0; i < CAPACITY; i++) {
                int r = random.nextInt(10);
                queue.add(r);
                System.out.println("Add " + i + "th value: " + r);
            }
        }

    }

    private static class Consumer implements Runnable {

        private boolean even = false;

        Consumer(boolean even) {
            this.even = even;
        }

        @Override
        public void run() {
            while (queue.size() != 0) {
                Integer peek = queue.peek();
                if ((peek != null) && (peek % 2 == 0) && (even)) {
                    System.out.println("Even consumer processed value: " + queue.poll());
                } else if ((peek != null) && (peek % 2 == 1) && (!even)) {
                    System.out.println("Not even consumer processed value: " + queue.poll());
                }
            }
        }

    }

}