package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task1Runner {

	public static void main(String[] args) {
		BlockingQueue<Integer> sharedQ = new ArrayBlockingQueue<>(100);
		ExecutorService runner = Executors.newFixedThreadPool(3);
		int attempts = 0;
		
		Consumer cons1 = new Consumer(sharedQ, "even");
		Consumer cons2 = new Consumer(sharedQ, "odd");
		Producer prod = new Producer(sharedQ);
		
		runner.execute(prod);
		runner.execute(cons1);
		runner.execute(cons2);
		
		while (sharedQ.isEmpty()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("waiting for an item");
			attempts++;
			if (attempts == 2) {
				cons1.stop();
				cons2.stop();
				break;
			}
		}
		runner.shutdown();
	}
}
