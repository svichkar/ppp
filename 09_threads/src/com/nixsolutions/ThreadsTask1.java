package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadsTask1 {
    public static void main(String[] args) throws InterruptedException {
	BlockingQueue<Integer> randomNumbersList = new ArrayBlockingQueue<Integer>(100);
	Producer prod = new Producer(randomNumbersList);
	Consumer consumerEven = new Consumer(randomNumbersList, true);
	Consumer consumerOdd = new Consumer(randomNumbersList, false);
	Thread threadProd = new Thread(prod);
	Thread threadConsEven = new Thread(consumerEven);
	Thread threadConsOdd = new Thread(consumerOdd);
	threadProd.start();
	Thread.sleep(100);
	threadConsEven.start();
	threadConsOdd.start();
    }
}
