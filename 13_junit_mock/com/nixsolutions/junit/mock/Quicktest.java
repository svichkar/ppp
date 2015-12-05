package com.nixsolutions.junit.mock;

public class Quicktest {

	public static void main(String[] args) {
		Robot rob = new Robot();
		Program controller = new Program(rob);
		controller.executeCommands("lffrflfrrfff");
	}
}
