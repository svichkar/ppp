package com.nixsolutions.oop;

import java.awt.Color;

public class Marker extends Writer {
	public Marker() {
		super();
	}
	
	@Override	
	public void prepare() {
		// Remove the cap from the marker
		color = Color.RED;
		isPrepared = true;
		System.out.println("Marker has been prepared");
	}
	
	@Override
	public StringBuilder write(StringBuilder stringBuilder) {
		percentage = percentage - ((float)stringBuilder.length() * 0.85f);
		System.out.println("Marker writes " + stringBuilder.toString() + " remaining ink is: " + percentage + "%");
		return stringBuilder;
	}
	
	@Override
	public void removeLastSymbol(StringBuilder stringBuilder) {
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		System.out.println("Erased the last symbol with marker. Now it looks: "+stringBuilder.toString());
	}
}
