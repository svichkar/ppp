package com.nixsolutions;

/**Class for triggering theards in scheduled time
 * 
 * @author maxb
 *
 */
public class Counter {

	/**
	 * Contant value for duration of counter
	 * 
	 * @param args
	 */
	final static int DURATION = 1000;
	static int mainCounter = 0;

	public static void main(String[] args) throws InterruptedException {

		while (mainCounter < DURATION) {
			switch (mainCounter) {
			case 100:
				Thread t100 = new Thread(new Runnable() {
					@Override
					public void run() {
						int localCounter = 1;
						while (mainCounter < DURATION) {
							System.out
									.printf("I am thread %1s and this my %2s attempt%n",
											Thread.currentThread().getName(),
											localCounter);
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							localCounter++;
						}
					}

				});
				t100.setName("t100");
				t100.start();
				break;
			case 300:
				Thread t300 = new Thread(new Runnable() {
					@Override
					public void run() {
						int localCounter = 1;
						while (mainCounter < DURATION) {
							System.out
									.printf("I am thread %1s and this my %2s attempt%n",
											Thread.currentThread().getName(),
											localCounter);
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							localCounter++;
						}
					}

				});
				t300.setName("t300");
				t300.start();
				break;
			case 500:
				Thread t500 = new Thread(new Runnable() {
					@Override
					public void run() {
						int localCounter = 1;
						while (mainCounter < DURATION) {
							System.out
									.printf("I am thread %1s and this my %2s attempt%n",
											Thread.currentThread().getName(),
											localCounter);
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							localCounter++;
						}
					}

				});
				t500.setName("t500");
				t500.start();
				break;
			default:
				// System.out.printf("Main counter %1ss out of %2ss%n",
				// mainCounter, DURATION);
				break;
			}

			mainCounter++;
			Thread.sleep(100);

		}

	}

}
