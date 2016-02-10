package com.nixsolutions.secondtask;

public class CounterThread implements Runnable {

	private String name;

	public CounterThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.printf("%s is running!%n", name);
	}
}
