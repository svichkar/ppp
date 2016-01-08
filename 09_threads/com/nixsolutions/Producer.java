package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * the class represents a producer. It adds 100 random Integer items to resource
 * (queue)
 * 
 * @author kryzhanovskiy
 *
 */
public class Producer implements Runnable {
	private BlockingQueue<Integer> sharedQ;

	/**
	 * 
	 * @param sharedQ the resource which will be filled with 100 Interger items 
	 */
	public Producer(BlockingQueue<Integer> sharedQ) {
		this.sharedQ = sharedQ;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				sharedQ.put(this.random(1, 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * generates a random number within the given range
	 * 
	 * @param min
	 *            minimal value of the range
	 * @param max
	 *            maximum value of the range
	 * @return random int value within the given range
	 */
	public int random(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}
