package com.nixsolutions;

public class OverallThread implements Runnable{

	private String name;
	private Thread mainThread;
	
	public OverallThread(String name, Thread mainThread)
	{
		this.name = name;
		this.mainThread = mainThread;
	}
	@Override
	public void run() {
		while (mainThread.isAlive()) {
			System.out.println("My name is "+ name +". And I am working");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}

}
