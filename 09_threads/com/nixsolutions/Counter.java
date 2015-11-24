package com.nixsolutions;

/**
 * the instance of the class represents a Counter which can be incremented by
 * particular size pattern
 * 
 * @author kryzhanovskiy
 *
 */
public class Counter {
	private int count = 0;
	private int incrementStep;

	/**
	 * creates instance of counter with given increment step. Counter will be
	 * incremented by the given increment step
	 * 
	 * @param step
	 *            increment step by which counter will be incremented
	 */
	public Counter(int step) {
		this.incrementStep = step;
	}

	/**
	 * 
	 * @return returns current state of the counter
	 */
	public int getCount() {
		return count;
	}

	/**
	 * increments the counter by set value
	 * 
	 * @param size
	 */
	public void incremnt() {
		this.count = count + incrementStep;
	}

}
