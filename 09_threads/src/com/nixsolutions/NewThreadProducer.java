package com.nixsolutions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class NewThreadProducer extends Thread {
    BlockingQueue<Integer> quene;

    public NewThreadProducer(BlockingQueue<Integer> quene) {
	this.quene = quene;
    }

    public void run() {

	for (int i = 0; i < 100; i++) {
	    Random rn = new Random();
	    int temp = rn.nextInt(100);
	    quene.add(temp);

	    System.out.println("Producer thread creates value - " + temp);
	}

    }

}
