package com.nixsolutions;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	private static final int AMOUNTOFELEMENTS = 100;

	private final BlockingQueue<Integer> queue;

	public Producer(BlockingQueue<Integer> q) {
		queue = q;
	}

	@Override
	public void run() {
		Integer addToQueue = null;
		try {
			int counter = 0;
			while (counter < AMOUNTOFELEMENTS) {
				if (queue.remainingCapacity() > 0) {
					addToQueue = nextInt(1, 101);
					queue.put(addToQueue);
					counter++;
					System.out.printf("Queued %1s out of %2s elements%n",
							counter, AMOUNTOFELEMENTS);
				} else {
					Thread.yield();
				}
			}
		} catch (ClassCastException | InterruptedException ex0) {
			System.out.printf("Cannot add element %1s to queue. Ex - %2s",
					addToQueue, ex0.getMessage());
		}
	}

	public static int nextInt(int min, int max) {
		Random rnd = new Random();
		int diff = max - min;
		if (diff >= 0 && diff != Integer.MAX_VALUE) {
			return (min + rnd.nextInt(diff + 1));
		}
		// if wrong range
		int i;
		do {
			i = rnd.nextInt();
		} while (i < min || i > max);
		return i;
	}

}
