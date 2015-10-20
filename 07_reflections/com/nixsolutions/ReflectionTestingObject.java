package com.nixsolutions;

public class ReflectionTestingObject {
	@Public
	public String name;
	@Public
	private int num;
	public long numT;

	public ReflectionTestingObject(String name, int num, long numT) {
		this.name = name;
		this.num = num;
		this.numT = numT;
	}
}
