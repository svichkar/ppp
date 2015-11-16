package com.nixsolutions.oop;

import java.awt.Color;

public class Pencil extends Writer {

	public Pencil() {
		super();	
		isPrepared = true;
	}

	@Override
	public StringBuilder write(StringBuilder stringBuilder) {		
		percentage = percentage - (stringBuilder.length() * 0.9f);		
		System.out.println("Pencil writes : "+stringBuilder.toString()+". remaining ink is: "+ percentage +"% of ink");
		return stringBuilder;
	}
	@Override
	public void removeLastSymbol(StringBuilder stringBuilder) {
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		System.out.println("Erased the last symbol with pencil. Now it looks: "+stringBuilder.toString());		
	}
	@Override
	public void prepare(){
		color = Color.GRAY;
		System.out.println("Pencil is always ready to use");
	}

	

}
