package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	protected BlockingQueue<Integer> queue = null;

	public Producer(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			Random random = new Random();
			int randomNumber = 0;
			for (int i = 0; i < 100; i++) {
				randomNumber = random.nextInt(100);
				queue.put(randomNumber);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
