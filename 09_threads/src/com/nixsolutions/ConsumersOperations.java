package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * The Class Consumers Operations.
 */
public class ConsumersOperations {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(100);
		Thread producer = new Thread(new Producer(queue));
		Thread evenConsumer = new Thread(new Consumer(queue, true));
		Thread oddConsumer = new Thread(new Consumer(queue, false));
		producer.start();
		evenConsumer.start();
		oddConsumer.start();
	}
}
