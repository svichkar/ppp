package com.nixsolutions;

public class CustThread implements Runnable {
    private String threadName;
    private Thread mainThread;

    public CustThread(String tName, Thread mThread) {
	this.threadName = tName;
	this.mainThread = mThread;
    }

    @Override
    public void run() {
	while (mainThread.isAlive()) {
	    System.out.println(threadName + " is working");
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	System.out.println(threadName + " is completed, because main thread is completed");
    }
}
