package com.nixsolutions;

import java.util.concurrent.*;

public class ThreadsConsumers {

	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Producer(queue));
		exec.execute(new Consumer(queue, true));
		exec.execute(new Consumer(queue, false));
		exec.shutdown();
	}

}
