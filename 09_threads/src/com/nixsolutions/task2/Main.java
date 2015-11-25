package com.nixsolutions.task2;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by konstantin on 11/24/2015.
 */
public class Main {

    public static final int COUNTER_MAX = 1000;
    public static final int FIRST = 100;
    public static final int SECOND = 300;
    public static final int THIRD = 500;

    public static void main(String args[]) {

        //declare flags variables which indicates start state for time tasks
        AtomicBoolean isFirstStarted = new AtomicBoolean(false);
        AtomicBoolean isSecondStarted = new AtomicBoolean(false);
        AtomicBoolean isThirdStarted = new AtomicBoolean(false);

        // declare and initialization counter
        AtomicInteger counter = new AtomicInteger(1);

        //Declare timer task for main
        TimerTask mainTask = new TimerTask() {
            @Override
            public void run() {

                counter.getAndIncrement();
            }
        };

        /**
         * timer task with overrided run method
         * which reports about first thread work
         */
        TimerTask reportTaskFirst = new TimerTask() {
            @Override
            public void run() {
                reportThreadWork(1);
            }
        };

        /**
         * timer task with overrided run method
         * which reports about second thread work
         */
        TimerTask reportTaskSecond = new TimerTask() {
            @Override
            public void run() {
                reportThreadWork(2);
            }
        };

        /**
         * timer task with overrided run method
         * which reports about third thread work
         */
        TimerTask reportTaskThird = new TimerTask() {
            @Override
            public void run() {
                reportThreadWork(3);
            }
        };

        // declare timers for timer tasks
        Timer mainTimer = new Timer("MainTimer");
        Timer firstTimer = new Timer("FirstTimer");
        Timer secondTimer = new Timer("SecondTimer");
        Timer thirdTimer = new Timer("ThirdTimer");

        Thread mainThread = new Thread(new Runnable() {

            /**
             * Overrided run method
             * which starts 3 timer tasks when counter reaches 100, 300, 500 respectively
             * and ends when counter reaches his max value - 1000
             */
            @Override
            public void run() {

                // start main timer task with period 100 ms
                mainTimer.scheduleAtFixedRate(mainTask, 0, 100);
                System.out.println("Counter has started");

                while (counter.get() != COUNTER_MAX) {
                    if (counter.get() == FIRST && isFirstStarted.get() == false) {

                        System.out.println(String.format("Counter has reached %d", counter.get()));
                        // start first timer task with period 1 sec
                        firstTimer.scheduleAtFixedRate(reportTaskFirst, 0, 1000);
                        isFirstStarted.set(true);
                    }
                    if (counter.get() == SECOND && isSecondStarted.get() == false) {

                        System.out.println(String.format("Counter has reached %d", counter.get()));
                        // start second timer task with period 1 sec
                        secondTimer.scheduleAtFixedRate(reportTaskSecond, 0, 1000);
                        isSecondStarted.set(true);
                    }
                    if (counter.get() == THIRD && isThirdStarted.get() == false) {

                        System.out.println(String.format("Counter has reached %d", counter.get()));
                        // start third timer task with period 1 sec
                        thirdTimer.scheduleAtFixedRate(reportTaskThird, 0, 1000);
                        isThirdStarted.set(true);
                    }
                }

                // Cancel all timers after counter reaches his max value
                mainTimer.cancel();
                firstTimer.cancel();
                secondTimer.cancel();
                thirdTimer.cancel();

                System.out.println(String.format("Counter has reached max value - %d and terminated", counter.get()));
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
