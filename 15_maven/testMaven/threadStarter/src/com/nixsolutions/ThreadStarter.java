/**
 * 
 */
package com.nixsolutions;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mixeyes
 *
 */
public class ThreadStarter {
	private static final int MAX_COUNT = 1000;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread mainThread;
		AtomicInteger counter;
		counter = new AtomicInteger(0);
		mainThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (counter.intValue() < MAX_COUNT) {
					switch (counter.intValue()) {
					case 100: {
						System.out.println("mainThread is working. thread100 is starting");
						newThread("thread100",counter).start();
					}
						break;
					case 300: {
						System.out.println("mainThread is working. thread300 is starting");
						newThread("thread300",counter).start();
					}
						break;
					case 500: {
						System.out.println("mainThread is working. thread500 is starting");
						newThread("thread500",counter).start();
					}
						break;
					default:
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					} finally {
						counter.incrementAndGet();
					}

				}

			}
		});
		mainThread.start();
	}

	private static Thread newThread(String threadName, AtomicInteger counter) {
		Thread thread100 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (counter.intValue() < MAX_COUNT) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					System.out.println(threadName+" is working. counter = " + counter.intValue());
				}

			}
		});
		return thread100;
	}
}
