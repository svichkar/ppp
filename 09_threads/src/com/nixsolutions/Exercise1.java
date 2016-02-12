package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise1 {
	public static void main(String[] args) {
		BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(100);
		Producer producer = new Producer(bq);
		OddConsumer oddConsumer = new OddConsumer(bq, "even");
		EvenConsumer evenConsumer = new EvenConsumer(bq, "odd");
		new Thread(producer).start();
		new Thread(oddConsumer).start();
		new Thread(evenConsumer).start();
	}
}
