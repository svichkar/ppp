package com.nixsolutions;

/**
 * The Class OverallThread.
 */
public class OverallThread implements Runnable {

	/** The name. */
	private String name;

	/** The main thread. */
	private Thread mainThread;

	/**
	 * Instantiates a new overall thread.
	 *
	 * @param name
	 *            the name
	 * @param mainThread
	 *            the main thread
	 */
	public OverallThread(String name, Thread mainThread) {
		this.name = name;
		this.mainThread = mainThread;
	}

	@Override
	public void run() {
		while (mainThread.isAlive()) {
			System.out.println("My name is " + name + ". And I am working");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
