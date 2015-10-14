package com.nixsolutions;

import java.util.concurrent.BlockingQueue;

public class NewThreadConsumer extends Thread {

    private boolean isGerade = false;

    private BlockingQueue<Integer> quene;

    public NewThreadConsumer(Boolean isGerade, BlockingQueue<Integer> quene) {
	this.isGerade = isGerade;
	this.quene = quene;
    }

    public void run() {

	while (!quene.isEmpty()) {

	    if (quene.peek() != null) {

		if (isGerade) {
		    if (quene.peek() % 2 == 0) {
			System.out.println("Consumer for gerade values reports - " + quene.poll());

		    }

		} else {
		    if (quene.peek() % 2 != 0) {
			System.out.println("Consumer for NON gerade values reports - " + quene.poll());
		    }

		}

	    }

	}

    }

}
