package com.nixsolutions;

import java.util.ArrayList;
import java.util.List;

public class ThreadsTask {
    public static void main(String[] args){
	List<Integer> randomNumbersList = new ArrayList<Integer>();
	Producer prod = new Producer(randomNumbersList);
	Consumer consumerEven = new Consumer(randomNumbersList, true);
	Consumer consumerOdd = new Consumer(randomNumbersList, false);
	Thread threadProd = new Thread(prod);
	Thread threadConsEven = new Thread(consumerEven);
	Thread threadConsOdd = new Thread(consumerOdd);
	threadProd.start();
	threadConsEven.start();
	threadConsOdd.start();
    }
}
