package com.nixsolutions;

import java.awt.Color;

/**
 * 
 * @author elena-z Abstract class for writer.
 *
 */
public abstract class Writer {
	/**
	 * Represents a color of the writer.
	 */
	protected Color color;

	/**
	 * Represents remaining balance of writing resource.
	 */
	protected float remain;

	/**
	 * Method for erasing the last character in the written string.
	 * 
	 * @param str
	 *            Written string.
	 */
	protected abstract void erase(StringBuilder str);

	/**
	 * Method for preparing writer for writing.
	 * 
	 * @param enable
	 *            Enabled status.
	 */
	protected abstract void prepareToWrite(boolean enable);

	/**
	 * Method for writing a string by the writer.
	 * 
	 * @param result
	 *            String to be written.
	 */
	protected abstract void write(StringBuilder result);
}
