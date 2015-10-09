package com.nixsolutions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable {
	protected BlockingQueue<Integer> queue;
	protected boolean isOdd;

	public Consumer(BlockingQueue<Integer> theQueue, boolean isOdd) {
		this.queue = theQueue;
		this.isOdd = isOdd;
	}

	@Override
	public void run() {
		while (true) {
			if (queue.peek() != null) {
				int headVal = queue.peek();
				if ((headVal % 2 == 0 && isOdd) | (Math.abs(headVal % 2) == 1 && !isOdd)) {
					int val;
					try {
						val = queue.take();
						System.out.println("Consumer took following value from the queue. Value: " + val);
					} catch (InterruptedException e) {
						System.out.println("Consumer iterrupted.");
					}
				}
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Consumer iterrupted during sleep.");
				}
			}
		}
	}
}
