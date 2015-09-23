package com.nixsolutions;

/**
 * interface for writing tools*/
public interface WritingTools {
	/**
	 * preparation tool for writing */
	public void preparationForWriting();
	/**
	 * write random character*/
	public char write();
	/**
	 * erase last character from StringBuilder object*/
	public StringBuilder erase(StringBuilder stringBuilder);

}
