/**
 * 
 */
package com.nixsolutions;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author mixeyes
 *
 */
public class Consumer extends Thread {
	protected ConcurrentLinkedQueue<Integer> nums;
	protected boolean isOdd;
	private String consumerName;
	private boolean isRun=false;

	public Consumer(ConcurrentLinkedQueue<Integer> values, boolean isOdd, String consumerName) {
		this.nums = values;
		this.isOdd = isOdd;
		this.consumerName = consumerName;
	}

	@Override
	public void run() {
		isRun=true;
		while (isRun) {
			if (nums.peek() != null) {
				if ((nums.peek() % 2 == 0 && isOdd) | (Math.abs(nums.peek() % 2) == 1 && !isOdd)) {
					System.out.println(consumerName + " took following value from the queue. Value: " + nums.poll());
				}
			}
		}
	}

	public void stopThread() {
		isRun=false;
	}
}
