package com.nixsolutions.firsttask;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private static final int END_FLAG = -1;
	private BlockingQueue<Integer> queue;
	private int capacity;

	public Producer(BlockingQueue<Integer> queue, int capacity) {
		this.queue = queue;
		this.capacity = capacity;
	}

	@Override
	public void run() {
		Random randNum = new Random();
		for (int i = 0; i < capacity; i++) {
			int randNumValue = randNum.nextInt(300);
			queue.offer(randNumValue);
			System.out.printf("Putting %s to queue.%n", randNumValue);
		}
		queue.offer(END_FLAG);
	}
}
