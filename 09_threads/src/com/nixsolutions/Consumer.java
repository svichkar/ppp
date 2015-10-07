package com.nixsolutions;

import java.util.Iterator;
import java.util.List;

public class Consumer implements Runnable {
    private List<Integer> randomNumbersList;
    private boolean isEven;

    public Consumer(List<Integer> rndnmbr, boolean isEven) {
	this.randomNumbersList = rndnmbr;
	this.isEven = isEven;
    }

    @Override
    public void run() {
	for (int i = 0; i < 5; i++) {
	    if (randomNumbersList.size() == 0) {
		try {
		    Thread.sleep(250);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    } else {
		break;
	    }
	    if (randomNumbersList.size() == 0) {
		throw new RuntimeException("No elements in queue for a long time");
	    }
	}
	Iterator<Integer> iter = randomNumbersList.iterator();
	while (iter.hasNext()) {
	    int nextElement = iter.next();
	    if (isEven) {
		if (nextElement % 2 == 0) {
		    System.out.println("Even: " + nextElement);
		}
	    } else {
		if (nextElement % 2 == 1) {
		    System.out.println("Odd: " + nextElement);
		}
	    }
	}
    }
}
