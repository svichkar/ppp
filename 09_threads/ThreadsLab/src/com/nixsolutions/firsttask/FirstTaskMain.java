package com.nixsolutions.firsttask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FirstTaskMain {

	public static final int CAPACITY = 100;

	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(CAPACITY + 1);
		Thread producerThread = new Thread(new Producer(queue, CAPACITY));
		Thread firstConsumerThread = new Thread(new Consumer(queue, true));
		Thread seconCconsumerThread = new Thread(new Consumer(queue, false));
		producerThread.start();
		firstConsumerThread.start();
		seconCconsumerThread.start();
	}
}
