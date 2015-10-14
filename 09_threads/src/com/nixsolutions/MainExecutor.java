package com.nixsolutions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainExecutor {

    public static void main(String[] args) {
	BlockingQueue<Integer> quene = new ArrayBlockingQueue<>(100);
	Thread prod = new NewThreadProducer(quene);
	Thread consumerOne = new NewThreadConsumer(true, quene);
	Thread consumerTwo = new NewThreadConsumer(false, quene);
	prod.start();
	try {
	    Thread.sleep(1);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	consumerOne.start();
	consumerTwo.start();
    }

}
