package com.nixsolutions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadConsumers {
	
	public static void main(String[] args) {
		ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
		Producer prod = new Producer(queue);
		Consumer cons1 = new Consumer(queue, true);
		Consumer cons2 = new Consumer(queue, false);
		int i = 0;
		while (i < 10) {
			synchronized (queue) {
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
