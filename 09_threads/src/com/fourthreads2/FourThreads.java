package com.fourthreads2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by pantiukhin on 2/11/2016.
 */
public class FourThreads {
    private static int counter = 1;
    private ExecutorService executor = Executors.newCachedThreadPool();
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        FourThreads fourThreads = new FourThreads();
        fourThreads.go();
    }

    public void go() {
        while (true) {
            while (counter <= 1000) {
                counter++;
                if (counter == 100) {
                    executor.execute(new ThreadOne());
                }
                if (counter == 300) {
                    executor.execute(new ThreadTwo());
                }
                if (counter == 500) {
                    executor.execute(new ThreadThree());
                }
            }
            try {
                Thread.sleep(100);
                counter = 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ThreadOne implements Runnable {

        @Override
        public void run() {
            runThread("one");
        }
    }

    private static class ThreadTwo implements Runnable {

        @Override
        public void run() {
            runThread("two");
        }
    }

    private static class ThreadThree implements Runnable {

        @Override
        public void run() {
            runThread("three");
        }
    }

    public static void runThread(String threadNumber) {
        lock.lock();
        try {
            while (counter <= 1000) {
                System.out.println("I am thread " + threadNumber + ". I am working now");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }
}
