package com.nixsolutions;

public class MainExecutor {

    public static void main(String[] args) {
	Thread prod = new NewThreadProducer();
	Thread consumerOne = new NewThreadConsumer(true);
	Thread consumerTwo = new NewThreadConsumer(false);
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
