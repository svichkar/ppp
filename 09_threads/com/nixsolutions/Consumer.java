package com.nixsolutions;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/**Class for getting value from BlockingQueue
 * 
 * @author maxb
 *
 */
public class Consumer implements Runnable {

	private BlockingQueue<Integer> queue;
	private ArrayList<Integer> resultList = null;
	private boolean isEven;
	private boolean stopIt = false;

	/**Main construtor
	 * 
	 * @param q Define a queue for watching it 
	 * @param isEven Define type of consumer (Even|Odd)
	 */
	public Consumer(BlockingQueue<Integer> q, boolean isEven) {
		queue = q;
		this.isEven = isEven;
		resultList = new ArrayList<Integer>();
	}

	@Override
	public void run() {
		while (!stopIt) {
			while (queue.peek() != null) {
				try {
					int value = queue.peek();
					if (Auxiliary.isEven(value) & isEven) {
						resultList.add(queue.take());
					} else if (!Auxiliary.isEven(value) & !isEven) {
						resultList.add(queue.take());
					} else {
						Thread.yield();
					}
				} catch (InterruptedException ex) {
					System.out.printf("Cannot get element from queue. Ex - %s",
							ex.getMessage());
				}
			}
		}
		if (isEven) {
			System.out.printf("Thread Even has got %1s items %2s%n",
					resultList.size(), Auxiliary.joinStrings(";", resultList));
		} else {
			System.out.printf("Thread Odd has got %1s items %2s%n",
					resultList.size(), Auxiliary.joinStrings(";", resultList));
		}
	}

	public void stop() {
		this.stopIt = true;
	}

}
