package com.nixsolutions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadConsumers {
	
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
		Producer prod = new Producer(queue);
		Consumer cons1 = new Consumer(queue, true);
		Consumer cons2 = new Consumer(queue, false);
	}
}
