package com.nixsolutions.task2;

/**
 * Created by konstantin on 11/24/2015.
 */
public class Main {

    public static final int COUNTER_MAX = 1000;
    public static final int FIRST = 100;
    public static final int SECOND = 300;
    public static final int THIRD = 500;

    public static void main(String args[]) {
        Runnable firstThread = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        reportThreadWork(1);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable secondThread = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        reportThreadWork(2);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable thirdThread = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        reportThreadWork(3);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread mainThread = new Thread(new Runnable() {

            /**
             * Overrided run method
             * which starts 3 threads when counter reaches 100, 300, 500 respectively
             * and ends when counter reaches his max value - 1000
             */
            @Override
            public void run() {

                int counter = 0;
                System.out.println("Counter has started.");

                    while (counter != COUNTER_MAX) {
                        try {
                        Thread.sleep(100);
                        counter++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (counter == FIRST) {
                            System.out.println(String.format("Counter has reached %d", counter));
                            Thread t1 = new Thread(firstThread);
                            t1.start();
                        }
                        if (counter == SECOND) {
                            System.out.println(String.format("Counter has reached %d", counter));
                            Thread t2 = new Thread(secondThread);
                            t2.start();
                        }
                        if (counter == THIRD) {
                            System.out.println(String.format("Counter has reached %d", counter));
                            Thread t3 = new Thread(thirdThread);
                            t3.start();
                        }
                    }

                System.out.println(String.format("Counter has reached max value - %d and terminated", counter));
                System.exit(1);
            }
        });

        mainThread.start();
    }

    /**
     * method prints about specified thread work
     *
     * @param number - means serial number of thread which reports his work
     */
    private static void reportThreadWork(int number) {
        System.out.println(String.format("Thread #%d is reporting now.", number));
    }
}
