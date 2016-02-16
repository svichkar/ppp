package com.fourthreads2;

/**
 * Created by pantiukhin on 2/11/2016.
 */
public class FourThreads {
    private int counter = 1;
    private Thread thrOne = new Thread(new ThreadToRun(), "one");
    private Thread thrTwo = new Thread(new ThreadToRun(), "two");
    private Thread thrThree = new Thread(new ThreadToRun(), "three");

    public static void main(String[] args) {
        FourThreads fourThreads = new FourThreads();
        fourThreads.go();
    }

    public void go() {
        while (counter < 1000) {
            try {
                counter++;
                if (counter == 100) {
                    System.out.println(counter);
                    thrOne.start();
                }
                if (counter == 300) {
                    System.out.println(counter);
                    thrTwo.start();
                }
                if (counter == 500) {
                    System.out.println(counter);
                    thrThree.start();
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(counter);
    }

    private class ThreadToRun implements Runnable {
        @Override
        public void run() {
            try {
                while (counter < 1000) {
                    System.out.println("I am thread " + Thread.currentThread().getName() + ". I am working now");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}