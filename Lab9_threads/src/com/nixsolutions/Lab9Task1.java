package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Lab9Task1 {

	public static void main(String[] args) throws Exception {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);
		Thread producer = new Thread(new Producer(queue));
		Thread consumerEven = new Thread(new Consumer(queue, true, producer));
		Thread consumerOdd = new Thread(new Consumer(queue, false, producer));
		producer.start();
		consumerEven.start();
		consumerOdd.start();
	}
}
