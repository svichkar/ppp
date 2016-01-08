package com.nixsolutions;

public class Task2Runner {

	public static void main(String[] args) throws InterruptedException {
		Counter c = new Counter(10);

		while (c.getCount() < 1000) {
			if (c.getCount() == 100 || c.getCount() == 300
					|| c.getCount() == 500) {
				new Thread(new Worker(c)).start();
			}
			Thread.sleep(100);
			c.incremnt();
			System.out.println(c.getCount());
		}
	}
}
