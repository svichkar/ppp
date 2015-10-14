package com.nixsolutions;

public class SecondTask {

    private static int counter = 1;
    private final static int ARRAY_SIZE = 1000;

    public static void main(String[] args) {

	ThreadClassForSecondTask threadOne = null, threadTwo = null, threadThree = null;
	System.out.println("Main thread was started sucessfully.");
	for (int i = 0; i < ARRAY_SIZE; i++) {
	    try {
		Thread.sleep(100);

		System.out.println("Iteration in main thread #" + i + " in progress");
		counter++;
		switch (counter) {
		case 100:
		    threadOne = new ThreadClassForSecondTask(100, Thread.currentThread());
		    threadOne.start();
		    break;
		case 300:
		    threadTwo = new ThreadClassForSecondTask(300, Thread.currentThread());
		    threadTwo.start();
		    break;
		case 500:
		    threadThree = new ThreadClassForSecondTask(500, Thread.currentThread());
		    threadThree.start();
		    break;
		}
	    } catch (InterruptedException e) {
		e.printStackTrace();

	    }
	}

    }
}
