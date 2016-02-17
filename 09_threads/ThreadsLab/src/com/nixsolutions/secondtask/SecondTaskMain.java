package com.nixsolutions.secondtask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SecondTaskMain {

	public static void main(String[] args) throws InterruptedException {
		Runnable firstThread = new CounterThread("Mr. First Thread");
		Runnable secondThread = new CounterThread("Mr. Second Thread");
		Runnable thirdThread = new CounterThread("Mr. Third Thread");
		ScheduledExecutorService scheduledService = Executors.newSingleThreadScheduledExecutor();
		System.out.println("Starting the loop.");
		for (int i = 1; i <= 1000; i++) {
			Thread.sleep(100);
			if (i == 100) {
				System.out.printf("Point %s reached. Starting thread.%n", i);
				scheduledService.scheduleAtFixedRate(firstThread, 0, 1, TimeUnit.SECONDS);
			}
			if (i == 300) {
				System.out.printf("Point %s reached. Starting thread.%n", i);
				scheduledService.scheduleAtFixedRate(secondThread, 0, 1, TimeUnit.SECONDS);
			}
			if (i == 500) {
				System.out.printf("Point %s reached. Starting thread.%n", i);
				scheduledService.scheduleAtFixedRate(thirdThread, 0, 1, TimeUnit.SECONDS);
			}
		}
		scheduledService.shutdown();
		System.out.println("Loop is over. Stopping threads.");
	}
}
