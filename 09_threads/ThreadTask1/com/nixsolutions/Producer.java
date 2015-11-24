package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private final BlockingQueue<Integer> queue;
	private int capacity = 0;

	public Producer(BlockingQueue<Integer> q) {
		queue = q;
		capacity = q.remainingCapacity();

	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < capacity; i++) {
				Random r = new Random();
				int value = r.nextInt(1000);
				queue.put(value);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
