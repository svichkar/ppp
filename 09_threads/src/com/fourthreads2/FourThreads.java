package com.fourthreads2;


/**
 * Created by pantiukhin on 2/11/2016.
 */
public class FourThreads {
    private static int counter = 1;
    private Thread thrOne = new Thread(new ThreadOne());
    private Thread thrTwo = new Thread(new ThreadTwo());
    private Thread thrThree = new Thread(new ThreadThree());

    public static void main(String[] args) {
        FourThreads fourThreads = new FourThreads();
        fourThreads.go();
    }

    public void go() {
        while (counter <= 1000) {
            try {

                counter++;
                if (counter == 100) {
                    thrOne.start();
                }
                if (counter == 300) {
                    thrTwo.start();
                }
                if (counter == 500) {
                    thrThree.start();
                }
                Thread.sleep(100);
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
        try {
            while (counter <= 1000) {
                System.out.println("I am thread " + threadNumber + ". I am working now");
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
