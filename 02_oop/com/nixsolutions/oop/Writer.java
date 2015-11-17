package com.nixsolutions.oop;

import java.awt.Color;

public abstract class Writer implements Comparable<Writer> {
	Writer() {
		prepare();
		percentage = 100.00f;
	}

	public boolean isPrepared;

	public Color color;
	public float percentage;

	/**
	 * Writes to console stringbuilder
	 * @param stringBuilder
	 * @return stringbuilder that had been written to console
	 */
	public StringBuilder write(StringBuilder stringBuilder) {
		return stringBuilder;
	}
/**
 * Removes the last symbol in streambuilder
 * @param stringBuilder
 */
	public void removeLastSymbol(StringBuilder stringBuilder) {
	}
/**
 * Uses for "preparation" of writer
 */
	public void prepare() {
	}
/**
 * Uses for sorting by percentage field
 */
	@Override
	public int compareTo(Writer o) {
		// TODO Auto-generated method stub
		return (int)(this.percentage*100 - o.percentage*100);
		
	}
}
