package com.nixsolutions;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sirotkin Mikhail
 * Main counter class that increments counter each 100 miliseconds and starts threads WaitingThread runnable class
 * when counter become equal 100, 300 and 500
 */
public class MainCounterThread implements Runnable{

    AtomicInteger counter;

    /**
     * Constructor
     * @param ai - atomic integer for counting iterations
     */
    public MainCounterThread(AtomicInteger ai) {
        this.counter = ai;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        WaitingThread thr1 = new WaitingThread("ThreadStarted_100");
        WaitingThread thr2 = new WaitingThread("ThreadStarted_300");
        WaitingThread thr3 = new WaitingThread("ThreadStarted_500");
        for(int i = 0; i < 1000; i++) {  //change to 1000
            try {
                Thread.sleep(100);
                counter.incrementAndGet();
                System.out.println("Counter value: " + counter);
                switch(counter.get()){
                    case 100:
                    new Thread(thr1).start();
                        break;
                    case 300:
                        new Thread(thr2).start();
                        break;
                    case 500:
                        new Thread(thr3).start();
                        break;
                    default:
                        break;
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        thr1.stop();
        thr2.stop();
        thr3.stop();
        //Print out time of general run execution
        long end = System.nanoTime();
        long traceTime = end - start;
        System.out.println("General time execution is : " + traceTime/1000000 + " miliseconds.");
    }


}
