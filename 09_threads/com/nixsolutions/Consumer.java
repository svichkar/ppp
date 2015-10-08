package com.nixsolutions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Consumer implements Runnable {
	protected ConcurrentLinkedQueue<Integer> queue;
	protected boolean isOdd;
	
	public Consumer(ConcurrentLinkedQueue<Integer> theQueue, boolean isOdd) {
		this.queue = theQueue; 
		this.isOdd = isOdd;
	}
	
	@Override
	public void run() {
		if (queue.peek() != null) {
			int headVal = queue.peek();
			if ((headVal % 2 == 0 && isOdd) | (Math.abs(headVal % 2) == 1 && !isOdd)) {
				int val = queue.poll();
				System.out.println("Consumer took following value from the queue. Value: " + val);
			}
		}
	}
}
