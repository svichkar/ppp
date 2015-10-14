package com.nixsolutions;

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
	int iter = 0;
	while (iter < 100) {
	    if (iter < randomNumbersList.size()) {
		int nextElement = randomNumbersList.get(iter);
		if (isEven) {
		    if (nextElement % 2 == 0) {
			System.out.println("Even: " + nextElement);
		    }
		} else {
		    if (nextElement % 2 == 1) {
			System.out.println("Odd: " + nextElement);
		    }
		}
		iter++;
	    }
	}
    }
}
