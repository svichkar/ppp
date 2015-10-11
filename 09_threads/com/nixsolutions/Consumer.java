package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	protected BlockingQueue<Integer> queue;
	protected boolean isOdd;
	protected Object flag;

	public Consumer(BlockingQueue<Integer> theQueue, boolean isOdd) {
		this.queue = theQueue;
		this.isOdd = isOdd;
		this.flag = new Object();
	}

	@Override
	public void run() {
		while (flag != null) {
			if (queue.peek() != null) {
				int headVal = queue.peek();
				if ((headVal % 2 == 0 & isOdd) || (Math.abs(headVal % 2) == 1 & !isOdd)) {
					int val;
					try {
						val = queue.take();
						System.out.println("Consumer took following value from the queue. Value: " + val);
					} catch (InterruptedException e) {
						System.out.println("Consumer interrupted.");
					}
				} else {
					Thread.yield();
				}
			}
		}
	}

	public void stop() {
		flag = null;
	}
}
