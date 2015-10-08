package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Producer implements Runnable {
	protected ConcurrentLinkedQueue<Integer> queue;
	
	public Producer(ConcurrentLinkedQueue<Integer> theQueue) {
		this.queue = theQueue; 
	}
	 
	@Override
	public void run() {
		Random rand = new Random();
		int val = rand.nextInt();
		queue.add(val);
		System.out.println("Producer put following value into the queue. Value: " + val);
	}

}
