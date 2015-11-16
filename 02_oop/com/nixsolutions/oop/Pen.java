package com.nixsolutions.oop;

import java.awt.Color;

public class Pen extends Writer {

	public Pen() {
		super();
	}

	@Override
	public void prepare() {
		// Remove the cap from the pen
		color = Color.BLUE;
		isPrepared = true;
		System.out.println("Pen has been prepared.");
	}

	@Override
	public StringBuilder write(StringBuilder stringBuilder) {
		percentage = percentage - (stringBuilder.length() * 0.85f);
		System.out.println("Pen writes: " + stringBuilder.toString() + ". remaining ink is: " + percentage + "% of ink");
		return stringBuilder;
	}

	@Override
	public void removeLastSymbol(StringBuilder stringBuilder) {
		System.out.println("Pen is not able to remove symbols. Text is: " + stringBuilder.toString());
	}

}
