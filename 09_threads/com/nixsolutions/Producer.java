package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	protected BlockingQueue queue;
	
	public Producer(BlockingQueue theQueue) {
		this.queue = theQueue; 
	}
	 
	@Override
	public void run() {
		Random rand = new Random();
		try {
			queue.put(rand.nextInt());
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Producer interrupted.");
		}
	}

}
