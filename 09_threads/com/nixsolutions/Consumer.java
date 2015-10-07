package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	protected BlockingQueue queue;
	protected boolean isOdd;
	
	public Consumer(BlockingQueue theQueue, boolean isOdd) {
		this.queue = theQueue; 
		this.isOdd = isOdd;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
