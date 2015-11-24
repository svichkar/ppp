package com.nixsolutions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * the class represents an instance of consumer who will check an item in
 * resource (queue) and will decide if the item will be taking from queue
 * 
 * @author kryzhanovskiy
 *
 */
public class Consumer implements Runnable {
	private BlockingQueue<Integer> sharedQ;
	String oddOrEven;
	final ReentrantLock lock = new ReentrantLock();
	final Condition notEmpty = lock.newCondition();

	/**
	 * 
	 * @param sharedQ
	 *            the resource from which items will be taken and processed
	 * @param oe
	 *            set the consumer to process odd items "odd" or even items
	 *            "even"
	 */
	public Consumer(BlockingQueue<Integer> sharedQ, String oe) {
		this.sharedQ = sharedQ;
		this.oddOrEven = oe;
	}

	@Override
	public void run() {
		try {
			Integer check;
			while ((check = this.tryToPeak(1000,
					TimeUnit.MILLISECONDS)) != null) {
				switch (oddOrEven) {
				case "even":
					if (check % 2 == 0) {
						System.out.println(Thread.currentThread().getName()
								+ " reporting that value is even: "
								+ sharedQ.take() + "; Items left in the queue: "
								+ sharedQ.size());
					}
					break;
				case "odd":
					if (check % 2 != 0) {
						System.out.println(Thread.currentThread().getName()
								+ " reporting that value is odd: "
								+ sharedQ.take() + "; Items left in the queue: "
								+ sharedQ.size());
					}
					break;
				default:
					break;
				}
			}
			System.out.println("the queue was empty for 1 second, the "
					+ Thread.currentThread().getName() + " was terminated");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * the method checks if the queue is not empty and waits for set amount of
	 * time for the queue to be not empty. Returns null if after set timeout
	 * time the queue remained empty
	 * 
	 * @param timeout
	 *            wait time for queue to be not empty
	 * @param unit
	 *            timeunit for timeout values
	 * @return an Integer item if queue is not empty or null if the queue was
	 *         empty during the timeout time
	 * @throws InterruptedException
	 */
	public Integer tryToPeak(long timeout, TimeUnit unit)
			throws InterruptedException {
		long nanos = unit.toNanos(timeout);
		final ReentrantLock lock = this.lock;
		lock.lockInterruptibly();
		try {
			while (sharedQ.isEmpty()) {
				if (nanos <= 0) {
					return null;
				} else {
					nanos = notEmpty.awaitNanos(nanos);
				}
			}
			return sharedQ.peek();
		} finally {
			lock.unlock();
		}
	}
}
