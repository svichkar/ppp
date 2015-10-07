package com.nixsolutions;

public class ThreadCounters {

	public static void main(String[] args) throws InterruptedException {
		int i = 0;
		while (i < 1001) {
			if (i == 100) {
				Thread fCounter = new Thread(new Runnable() {
					
					@Override
					public void run() {
						int j = 0;
						while (j < 90) {
							System.out.println("Thread 1 is working. Counter: " + j);
							try {
								Thread.sleep(1000);
							} catch (InterruptedException ex) {
								ex.printStackTrace();
								System.out.println(
										"Thread 1 has thrown the interrupted exception. Counter: " + j);
							}
							j++;
						}
					}
				});
				fCounter.start();
			} else if (i == 300) {
				Thread sCounter = new Thread(new Runnable() {
					
					@Override
					public void run() {
						int j = 0;
						while (j < 70) {
							System.out.println("Thread 2 is working. Counter: " + j);
							try {
								Thread.sleep(1000);
							} catch (InterruptedException ex) {
								ex.printStackTrace();
								System.out.println(
										"Thread 2 has thrown the interrupted exception. Counter: " + j);
							}
							j++;
						}
					}
				});
				sCounter.start();
			} else if (i == 500) {
				Thread tCounter = new Thread(new Runnable() {

					@Override
					public void run() {
						int j = 0;
						while (j < 50) {
							System.out.println("Thread 3 is working. Counter: " + j);
							try {
								Thread.sleep(1000);
							} catch (InterruptedException ex) {
								ex.printStackTrace();
								System.out.println(
										"Thread 3 has thrown the interrupted exception. Counter: " + j);
							}
							j++;
						}
					}
				});
				tCounter.start();
			}
			Thread.sleep(100);
			i++;
		}
		System.out.println("Threads have finished.");
	}
}
