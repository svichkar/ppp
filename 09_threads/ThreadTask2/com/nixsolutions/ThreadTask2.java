package com.nixsolutions;

public class ThreadTask2 {

	public static void main(String[] args) {
		Runnable action1 = new Runnable() {
			public void run() {
				while (true) {
					try {
						System.out.println("Thread #1 is running");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Runnable action2 = new Runnable() {
			public void run() {
				while (true) {
					try {
						System.out.println("Thread #2 is running");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Runnable action3 = new Runnable() {
			public void run() {
				while (true) {
					try {
						System.out.println("Thread #3 is running");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread mainThread = new Thread() {
			@Override
			public void run() {
				try {
					int c = 1;
					while (c < 1001) {
						Thread.sleep(100);
						c++;
						if (c == 100) {
							Thread myThread1 = new Thread(action1);
							myThread1.start();
						}
						if (c == 300) {
							Thread myThread2 = new Thread(action2);
							myThread2.start();
						}
						if (c == 500) {
							Thread myThread3 = new Thread(action3);
							myThread3.start();
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.exit(1);

			}
		};
		mainThread.start();
	}

}
