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

	public StringBuilder write(StringBuilder stringBuilder) {
		return stringBuilder;
	}

	public void removeLastSymbol(StringBuilder stringBuilder) {
	}

	public void prepare() {
	}

	@Override
	public int compareTo(Writer o) {
		// TODO Auto-generated method stub
		return ((int) this.percentage - (int) o.percentage);
		
	}
}
