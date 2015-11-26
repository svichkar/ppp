package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private BlockingQueue<Integer> sharedQ;
	String oddOrEven;
	boolean flag = true;

	public Consumer(BlockingQueue<Integer> sharedQ, String oe) {
		this.sharedQ = sharedQ;
		this.oddOrEven = oe;
	}

	@Override
	public void run() {
		try {
			while (this.flag) {
				Integer check = sharedQ.peek();
				if (check != null) {

					switch (oddOrEven) {
					case "even":
						if (check % 2 == 0) {
							System.out.println(Thread.currentThread().getName()
									+ " reporting that value is even: "
									+ sharedQ.take()
									+ "; Items left in the queue: "
									+ sharedQ.size());
						}
						break;
					case "odd":
						if (check % 2 != 0) {
							System.out.println(Thread.currentThread().getName()
									+ " reporting that value is odd: "
									+ sharedQ.take()
									+ "; Items left in the queue: "
									+ sharedQ.size());
						}
						break;
					default:
						break;
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void stop() {
		System.out.println("stop command for: "
				+ Thread.currentThread().getName() + this.getClass().getName());
		this.flag = false;
	}
}
