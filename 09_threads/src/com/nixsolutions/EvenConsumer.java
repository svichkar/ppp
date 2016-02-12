package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

public class EvenConsumer implements Runnable {

	protected BlockingQueue<Integer> queue = null;
	private String overOrOdd;

	public EvenConsumer(BlockingQueue<Integer> queue, String overOrOdd) {
		this.queue = queue;
		this.overOrOdd = overOrOdd;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Integer headNumber = (Integer) queue.peek();
				if(headNumber==null)
				{
					Thread.sleep(1000);
				}
				else
				{
				switch (overOrOdd) {
				case "odd":
					if (headNumber % 2 != 0) {
						int firstNumber = (int) queue.take();
						System.out.println("Odd Consumer: " + firstNumber);
					}
					break;

				}
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
