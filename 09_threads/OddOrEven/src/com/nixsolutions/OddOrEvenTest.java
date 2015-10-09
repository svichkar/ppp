/**
 * 
 */
package com.nixsolutions;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author mixeyes
 *
 */
public class OddOrEvenTest {

	public static void main(String[] args) {
		ConcurrentLinkedQueue<Integer> randomValues = new ConcurrentLinkedQueue<>();
		Producer prod = new Producer(randomValues);
		Consumer cons1 = new Consumer(randomValues, true, "First consumer for even numbers");
		Consumer cons2 = new Consumer(randomValues, false,"Second consumer for odd numbers");
		int i = 0;
		while (i < 10) {
			synchronized (randomValues) {
				Thread prodThread = new Thread(prod);
				Thread consThread1 = new Thread(cons1);
				Thread consThread2 = new Thread(cons2);
				prodThread.start();
				consThread1.start();
				consThread2.start();
				i++;
			}
		}
	}

}
