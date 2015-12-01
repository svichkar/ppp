package com.nixsolutions;

public class Counters implements Runnable {
	String threadName;

	Counters(String name) {
		this.threadName = name;
	}

	@Override
	public void run() {
		System.out.println("Working thread is " + threadName);
	}

}
