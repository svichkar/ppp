package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {

		BlockingQueue<Integer> q = new ArrayBlockingQueue<>(100);
	     Producer p = new Producer(q);
	     Consumer c1 = new Consumer(q, true);
	     Consumer c2 = new Consumer(q, false);
	     	     
	     ExecutorService executorService = Executors.newFixedThreadPool(3);
	     executorService.execute(p);
	     executorService.execute(c1);
	     executorService.execute(c2);
	     executorService.shutdown();
	}
}
