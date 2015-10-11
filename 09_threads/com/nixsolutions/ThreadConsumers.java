package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadConsumers {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);
		Lock lock = new ReentrantLock(true);
		Producer prod = new Producer(queue);
		Consumer cons1 = new Consumer(queue, true);
		Consumer cons2 = new Consumer(queue, false);
		Thread prodThread = new Thread(prod);
		Thread consThread1 = new Thread(cons1);
		Thread consThread2 = new Thread(cons2);
		lock.lock();
		consThread1.start();		
		consThread2.start();
		prodThread.start();
		prodThread.join();
		while (queue.peek() != null) {}
		lock.unlock();
		cons1.stop();
		cons2.stop();
	}
}
