package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Producer implements Runnable {
	protected BlockingQueue<Integer> queue;

	public Producer(BlockingQueue<Integer> theQueue) {
		this.queue = theQueue;
	}

	@Override
	public void run() {
		Random rand = new Random();
		int i = 0;
		while (i < 100) {
			int val = rand.nextInt();
			queue.add(val);
			System.out.println("Producer put following value into the queue. Value: " + val);
			i++;
			//Thread.yield();
		}
	}
}
