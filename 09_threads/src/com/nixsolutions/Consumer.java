package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

/**
 * The Class Consumer.
 */
public class Consumer implements Runnable {

	/** The queue of numbers */
	private BlockingQueue<Integer> queue = null;

	/** The over or odd. */
	private boolean overOrOdd;

	/**
	 * Instantiates a new consumer.
	 *
	 * @param queue
	 *            the queue of numbers
	 * @param overOrOdd
	 *            the over or odd
	 */
	public Consumer(BlockingQueue<Integer> queue, boolean overOrOdd) {
		this.queue = queue;
		this.overOrOdd = overOrOdd;
	}

	@Override
	public void run() {
		try {
			determinationNumber(overOrOdd);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Determination over/Odd number .
	 *
	 * @param overOrOdd
	 *            the over or odd number
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void determinationNumber(boolean overOrOdd) throws InterruptedException {
		boolean stop = true;
		while (stop) {
			Integer headNumber = (Integer) queue.peek();
			if (headNumber == null) {
				Thread.sleep(1000);
				if (headNumber == null)
					stop = false;
			} else {
				if (overOrOdd == true) {
					if (headNumber % 2 == 0) {
						int firstNumber = (int) queue.take();
						System.out.println("Even Consumer: " + firstNumber);
					}
				} else if (overOrOdd == false) {
					if (headNumber % 2 != 0) {
						int firstNumber = (int) queue.take();
						System.out.println("Odd Consumer: " + firstNumber);
					}
				}
			}
		}
	}
}
