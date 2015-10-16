/**
 * 
 */
package com.nixsolutions;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mixeyes
 *
 */
public class OddOrEvenTest {

	public static void main(String[] args) throws InterruptedException {
		ConcurrentLinkedQueue<Integer> randomValues = new ConcurrentLinkedQueue<>();
		Producer prod = new Producer(randomValues);
		Consumer cons1 = new Consumer(randomValues, true, "First consumer for even numbers");
		Consumer cons2 = new Consumer(randomValues, false, "Second consumer for odd numbers");
		Lock lock = new ReentrantLock(true);

		synchronized (prod) {
			Thread prodThread = new Thread(prod);
			Thread consThread1 = new Thread(cons1);
			Thread consThread2 = new Thread(cons2);
			consThread1.start();
			consThread2.start();
			prodThread.start();
			prodThread.join();
			while (randomValues.peek() != null) {
			}
			cons1.stopThread();
			cons2.stopThread();
		}
		

	}

}
