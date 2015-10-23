package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Integer> randomNumbersList;
    private boolean isEven;

    public Consumer(BlockingQueue<Integer> rndnmbr, boolean isEven) {
	this.randomNumbersList = rndnmbr;
	this.isEven = isEven;
    }

    @Override
    public void run() {
	while (randomNumbersList.peek() != null) {
	    if (isEven) {
		if (randomNumbersList.peek() % 2 == 0) {
		    System.out.println("Even: " + randomNumbersList.poll());
		}
	    } else {
		if (randomNumbersList.peek() % 2 == 1) {
		    System.out.println("Odd: " + randomNumbersList.poll());
		}
	    }
	}
    }
}
