package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Integer> randomNumbersList;

    public Producer(BlockingQueue<Integer> rndnmbrs) {
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
