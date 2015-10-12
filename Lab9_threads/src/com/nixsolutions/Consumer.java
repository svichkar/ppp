package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private final BlockingQueue<Integer> queue;
	private Boolean isEvenConsumer;
	private Thread parrent;

	public Consumer(BlockingQueue<Integer> queue, Boolean isEvenConsumer, Thread parrent) {
		this.queue = queue;
		this.isEvenConsumer = isEvenConsumer;
		this.parrent = parrent;
	}

	@Override
	public void run() {
		while (parrent.isAlive()) {
			Integer i = queue.peek();
			if (i != null) {
				if (isEvenConsumer) {
					if (i % 2 == 0) {
						System.out.println("Theard even: " + queue.poll());
					}
				} else {
					if (i % 2 != 0) {
						System.out.println("Theard odd: " + queue.poll());
					}
				}
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
