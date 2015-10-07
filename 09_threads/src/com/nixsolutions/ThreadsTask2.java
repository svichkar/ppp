package com.nixsolutions;

public class ThreadsTask2 {
    public static void main(String[] args) {
	for (int index = 0; index <= 1000; index++) {
	    if (index == 100) {
		CustThread customThread1 = new CustThread("Thread 1", Thread.currentThread());
		Thread t1 = new Thread(customThread1);
		t1.start();
	    }
	    if (index == 300) {
		CustThread customThread2 = new CustThread("Thread 2", Thread.currentThread());
		Thread t2 = new Thread(customThread2);
		t2.start();
	    }
	    if (index == 500) {
		CustThread customThread3 = new CustThread("Thread 3", Thread.currentThread());
		Thread t3 = new Thread(customThread3);
		t3.start();
	    }
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	System.out.println("Main thread is completed");
    }
}
