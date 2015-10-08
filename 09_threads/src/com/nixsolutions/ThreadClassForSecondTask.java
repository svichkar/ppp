package com.nixsolutions;

public class ThreadClassForSecondTask extends Thread {
    private static int starttime;
    Thread main;

    public ThreadClassForSecondTask(int startTime, Thread main) {
	this.starttime = startTime;
	this.main = main;
    }

    public void run() {

	int startedIn = starttime;
	while (main.isAlive()) {
	    System.out.println("Thread which started in " + startedIn + " ms works fine.");
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	System.out.println("Thread which was started in "+startedIn + " ms is ENDED." );
    }
}
