package com.nixsolutions.firsttask;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private static final int END_FLAG = -1;
	private BlockingQueue<Integer> queue;
	private boolean even;
	private boolean running;

	public Consumer(BlockingQueue<Integer> queue, boolean even) {
		this.queue = queue;
		this.even = even;
		running = true;
	}

	@Override
	public void run() {
		try {
			while (running) {
				if (queue.peek() != null) {
					int queueValue = queue.peek();
					if (queueValue == END_FLAG) {
						running = false;
					}
					if (even && queueValue % 2 == 0) {
						System.out.printf("Even value %d is consumed.%n", queue.take());
					}
					if (!even && queueValue % 2 == 1) {
						System.out.printf("Odd value %d is consumed.%n", queue.take());
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
