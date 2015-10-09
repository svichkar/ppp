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
	private static Thread mainThread;
	private static AtomicInteger counter;
	private static final int MAX_COUNT = 1000;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		counter = new AtomicInteger(0);
		mainThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (counter.intValue() <= MAX_COUNT) {
					switch (counter.intValue()) {
					case 100: {
						System.out.println("mainThread is working. thread100 is starting");
						thread100().start();
					}
						break;
					case 300: {
						System.out.println("mainThread is working. thread300 is starting");
						thread300().start();
					}
						break;
					case 500: {
						System.out.println("mainThread is working. thread500 is starting");
						thread500().start();
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

	private static Thread thread100() {
		Thread thread100 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (counter.intValue() <= MAX_COUNT) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					System.out.println("thread100 is working. counter = " + counter.intValue());
				}

			}
		});
		return thread100;
	}

	private static Thread thread300() {
		Thread thread300 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (counter.intValue() <= MAX_COUNT) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					System.out.println("thread300 is working. counter = " + counter.intValue());
				}

			}
		});
		return thread300;
	}

	private static Thread thread500() {
		Thread thread500 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (counter.intValue() <= MAX_COUNT) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					System.out.println("thread500 is working. counter = " + counter.intValue());
				}

			}
		});
		return thread500;
	}

}
