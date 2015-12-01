package com.nixsolutions;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadsCounters {

	public static void main(String[] args) throws InterruptedException {
		Counters firstThread = new Counters("First");
		Counters secondThread = new Counters("Second");
		Counters thirdThread = new Counters("Third");
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		int i = 0;
		while (i <= 1000) {
			Thread.currentThread().sleep(100);
			i++;
			if (i == 100)
				exec.scheduleAtFixedRate(firstThread, 0, 1, TimeUnit.SECONDS);
			if (i == 300)
				exec.scheduleAtFixedRate(secondThread, 0, 1, TimeUnit.SECONDS);
			if (i == 500)
				exec.scheduleAtFixedRate(thirdThread, 0, 1, TimeUnit.SECONDS);
		}
		exec.shutdownNow();

	}

}
