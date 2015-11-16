package com.nixsolutions;

import java.awt.Color;

/**
 * 
 * @author elena-z Represents a pencil as writer.
 *
 */
public class Pencil extends Writer {
	/**
	 * Creates a new pencil with the gray color and 100% balance of writing
	 * resource.
	 */
	public Pencil() {
		color = Color.GRAY;
		remain = 100.0f;
	}

	/**
	 * Erase the last symbol in the string written by the pencil.
	 * 
	 * @param str
	 *            Written string.
	 */
	@Override
	public void erase(StringBuilder str) {
		System.out.println("After erasing: " + str.deleteCharAt(str.length() - 1));
	}

	/**
	 * This method is not used for pencil.
	 */
	@Override
	protected void prepareToWrite(boolean enable) {
	}

	/**
	 * Writes a string by the pencil. Balance of writing resource reduces on
	 * 0.9% for each written symbol.
	 * 
	 * @param result
	 *            String to be written by the pencil.
	 */
	@Override
	public void write(StringBuilder result) {
		remain = remain - 0.9f * result.length();
		System.out.println("Written: " + result);
		System.out.println(remain);
	}

}
