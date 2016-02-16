package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * The Class Producer.
 */
public class Producer implements Runnable {

	/** The queue of numbers */
	private BlockingQueue<Integer> queue = null;

	/**
	 * Instantiates a new producer.
	 *
	 * @param queue
	 *            the queue
	 */
	public Producer(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			fillingQueueRandomNumbers();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Filling queue random numbers.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void fillingQueueRandomNumbers() throws InterruptedException {
		Random random = new Random();
		int randomNumber = 0;
		for (int i = 0; i < 100; i++) {
			randomNumber = random.nextInt(100);
			queue.put(randomNumber);
		}
	}

}
