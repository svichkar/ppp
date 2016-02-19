package com.nixsolutions;

/**
 * @author Sirotkin Mikhail
 * Class that execution  print out message about working each second
 */
public class WaitingThread implements Runnable {
    boolean stopFlag;
    String threadName;

    /**
     * WaitingThread class constructor
     * @param threadName
     */
    public WaitingThread(String threadName) {
        stopFlag = false;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println("Thread " + threadName + " started");
        while(!stopFlag) {
            try {
                System.out.printf("Thread " + threadName + " is working \n");
                Thread.sleep(1000); //wait a second before next cycle of printing
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * Method for stop  current execution
     */
    public void stop(){
        stopFlag = true;
        System.out.printf("Thread " + threadName + " was stopped. \n");
    }
}
