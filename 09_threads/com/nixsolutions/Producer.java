package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	private BlockingQueue<Integer> numbersQueue;

	Producer(BlockingQueue<Integer> queue) {
		this.numbersQueue = queue;
	}

	@Override
	public void run() {
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			int value = random.nextInt(100);
			numbersQueue.add(value);
			System.out.println("Producer generated value: " + value);
		}

	}

}
