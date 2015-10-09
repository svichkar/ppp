package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadConsumers {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);
		Thread prodThread = new Thread(new Producer(queue));
		Thread consThread1 = new Thread(new Consumer(queue, true));
		Thread consThread2 = new Thread(new Consumer(queue, false));
		Lock lock = new ReentrantLock();		
		prodThread.start();
		lock.lock();
		consThread1.start();		
		consThread2.start();
		prodThread.join();
		lock.unlock();
	}
}
