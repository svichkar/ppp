/**
 * 
 */
package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author mixeyes
 *
 */
public class Producer implements Runnable {
	private ConcurrentLinkedQueue<Integer> values;

	public Producer(ConcurrentLinkedQueue<Integer> someObj) {
		this.values = someObj;
	}

	@Override
	public void run() {
		Random rand = new Random(System.nanoTime());
		for (int i = 10; i > 0; i--) {
			int val = rand.nextInt();
			values.add(val);
			System.out.println("Producer put rundom value " + val + " into the SynchronousQueue.");
}
	}

}
