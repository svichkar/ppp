package com.nixsolutions;

import java.util.List;
import java.util.Random;

public class Producer implements Runnable {
    private List<Integer> randomNumbersList;

    public Producer(List<Integer> rndnmbrs) {
	this.randomNumbersList = rndnmbrs;
    }

    @Override
    public void run() {
	Random rnd = new Random();
	for (int i = 0; i < 100; i++) {
	    randomNumbersList.add(rnd.nextInt(100));
	}
    }
}
