package com.nixsolutions;

public class Lab9Task2 {

	public static void main(String[] args) throws Exception {
		Thread theard1 = new Thread(new JustTheard("#1", Thread.currentThread()));
		Thread theard2 = new Thread(new JustTheard("#2", Thread.currentThread()));
		Thread theard3 = new Thread(new JustTheard("#3", Thread.currentThread()));
		int counter = 0;
		for (int i = 0; i < 1000; i++) {
			Thread.sleep(100);
			counter++;
			switch (counter) {
			case 100:
				theard1.start();
				break;
			case 300:
				theard2.start();
				break;
			case 500:
				theard3.start();
				break;
			}
		}
	}

}
