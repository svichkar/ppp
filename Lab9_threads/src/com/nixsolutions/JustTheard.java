package com.nixsolutions;

public class JustTheard extends Thread {
	private String nameOfTheard;
	private Thread parent;

	public JustTheard(String nameOfTheard, Thread parent) {
		this.nameOfTheard = nameOfTheard;
		this.parent = parent;
	}

	@Override
	public void run() {
		while (parent.isAlive()) {
			System.out.println("I am theard " + nameOfTheard + ". OK!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
