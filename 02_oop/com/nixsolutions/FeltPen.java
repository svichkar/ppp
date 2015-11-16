package com.nixsolutions;

import java.awt.Color;

/**
 * 
 * @author elena-z Represents a felt pen as writer.
 *
 */
public class FeltPen extends Writer {
	/**
	 * Creates a new felt pen with the green color and 100% balance of writing
	 * resource.
	 */
	public FeltPen() {
		color = Color.GREEN;
		remain = 100.0f;
	}

	/**
	 * This method is not used for felt pen.
	 */
	@Override
	public void erase(StringBuilder str) {

	}

	/**
	 * Prepare the felt pen for writhing.
	 * 
	 * @param enable
	 *            Enabled status.
	 */
	@Override
	public void prepareToWrite(boolean enable) {
		enable = true;
		System.out.println("Felt pen is ready");
	}

	/**
	 * Writes a string by the felt pen. Balance of writing resource reduces on
	 * 0.85% for each written symbol.
	 * 
	 * @param result
	 *            String to be written by the felt pen.
	 */
	@Override
	public void write(StringBuilder result) {
		remain = remain - 0.85f * result.length();
		System.out.println("Written: " + result);
		System.out.println(remain);
	}

}
