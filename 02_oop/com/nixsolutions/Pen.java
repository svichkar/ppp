package com.nixsolutions;

import java.awt.Color;

/**
 * 
 * @author elena-z Represents a pen as writer
 *
 */
public class Pen extends Writer {
	/**
	 * Creates a new pen with the blue color and 100% balance of writing
	 * resource.
	 */
	public Pen() {
		color = Color.BLUE;
		remain = 100.0f;
	}

	/**
	 * This method is not used for pen.
	 */
	@Override
	public void erase(StringBuilder str) {

	}

	/**
	 * Prepare the pen for writhing.
	 * 
	 * @param enable
	 *            Enabled status.
	 */
	@Override
	public void prepareToWrite(boolean enable) {
		enable = true;
		System.out.println("Pen is ready");
	}

	/**
	 * Writes a string by the pen. Balance of writing resource reduces on 0.8%
	 * for each written symbol.
	 * 
	 * @param result
	 *            String to be written by the pen.
	 */
	@Override
	public void write(StringBuilder result) {
		remain = remain - 0.8f * result.length();
		System.out.println("Written: " + result);
		System.out.println(remain);
	}

}
