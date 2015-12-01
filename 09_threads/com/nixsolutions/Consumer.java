package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private BlockingQueue<Integer> numbersQueue;
	private boolean isEven;

	Consumer(BlockingQueue<Integer> queue, boolean even) {
		this.numbersQueue = queue;
		this.isEven = even;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			if (numbersQueue.peek() != null) {
				int value = numbersQueue.peek();
				try {
					if (isEven) {
						if (value % 2 == 0)
							System.out.println("Consumer took even value: " + numbersQueue.take());

					} else {
						if (value % 2 == 1)
							System.out.println("Consumer took odd value: " + numbersQueue.take());
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}
