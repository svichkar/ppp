package com.nixsolutions;

public class Worker implements Runnable {
	private Counter count;

	public Worker(Counter c) {
		this.count = c;
	}

	public int getConut() {
		return count.getCount();
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()
				+ " Has started its work at " + count.getCount());

		while (count.getCount() < 1000) {
			System.out.println(
					Thread.currentThread().getName() + " is working hard");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
