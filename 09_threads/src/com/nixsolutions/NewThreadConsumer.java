package com.nixsolutions;

public class NewThreadConsumer extends Thread {

    private boolean isGerade = false;

    public NewThreadConsumer(Boolean isGerade) {
	this.isGerade = isGerade;
    }

    public void run() {
	if (isGerade) {
	    if (NewThreadProducer.quene != null) {
		for (Integer intt : NewThreadProducer.quene) {
		    if (intt % 2 == 0) {
			System.out.println("Consumer for gerade values reports - " + intt  );
		    }
		}

	    } else {
		System.out.println("Consumer: List is empty!");
	    }
	} else {
	    if (NewThreadProducer.quene != null) {
		for (Integer intt : NewThreadProducer.quene) {
		    if (intt % 2 != 0) {
			System.out.println("Consumer for NON gerade values reports - " + intt);
		    }
		}
	    }
	}

    }

}
