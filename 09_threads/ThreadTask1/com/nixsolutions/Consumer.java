package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private final BlockingQueue<Integer> queue;
	private Boolean even;

	public Consumer(BlockingQueue<Integer> q, boolean even) {
		this.queue = q;
		this.even = even;
	}

	@Override
	public void run() {
		while (!queue.isEmpty()) {
			Integer number = queue.peek();
			if (number != null) {
				if (even) {
					if (number % 2 == 0) {
						System.out.println("Thread name: " + Thread.currentThread().getName() + "; Even number is "
								+ queue.poll());
					}
				} else {
					if (number % 2 != 0) {
						System.out.println(
								"Thread name: " + Thread.currentThread().getName() + "; Odd number is " + queue.poll());

					}
				}
			}

		}

	}

}
