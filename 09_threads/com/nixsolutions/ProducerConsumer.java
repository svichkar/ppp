package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**Class for validating producer/consumer relationship
 * 
 * @author maxb
 *
 */
public class ProducerConsumer {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> ourQueue = new ArrayBlockingQueue<Integer>(100);
		Producer prod = new Producer(ourQueue);
		Consumer consumerEven = new Consumer(ourQueue, true);
		Consumer consumerOdd = new Consumer(ourQueue, false);

		Thread p1 = new Thread(prod);
		Thread t1 = new Thread(consumerEven);
		Thread t2 = new Thread(consumerOdd);

		p1.setPriority(Thread.MAX_PRIORITY);
		t1.setPriority(Thread.MIN_PRIORITY + 5);
		t2.setPriority(Thread.MIN_PRIORITY);

		p1.start();
		t1.start();
		t2.start();
		p1.join();

		while (ourQueue.peek() != null) {
			try {
				Thread.sleep(2000);
				System.out.printf(
						"Queue still has %1s items. Active threads  - %2s%n",
						ourQueue.size(), Thread.activeCount());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		consumerEven.stop();
		consumerOdd.stop();
	}

}
