package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task1Runner {

	public static void main(String[] args) {
		BlockingQueue<Integer> sharedQ = new ArrayBlockingQueue<>(100);
		ExecutorService runner = Executors.newFixedThreadPool(3);
		
		runner.execute(new Producer(sharedQ));
		runner.execute(new Consumer(sharedQ, "even"));
		runner.execute(new Consumer(sharedQ, "odd"));
		runner.shutdown();
	}
}
