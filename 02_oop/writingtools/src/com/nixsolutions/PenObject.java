/**
 * 
 */
package com.nixsolutions;

import java.util.Random;

/**
 * class for objects PEN
 * 
 */
public class PenObject extends WritingObjects implements WritingTools {
	public PenObject() {
		super();
	}

	@Override
	public void preparationForWriting() {
		System.out.print("rest of writing means before preparation: \"" + restOfWritingMeans + "\"%\n");
		color = "black";
		restOfWritingMeans = 100.00f;
		System.out.print("rest of writing means after preparation: \"" + restOfWritingMeans + "\"%\n");
	}

	@Override
	public char write() {
		System.out.println("Actual state of the pen:\n actual color: \"" + color + "\"\n rest of writing means: \""
				+ restOfWritingMeans + "\"\n");
		Random r = new Random(System.nanoTime());

		char randomChar = (char) (r.nextInt(26) + 'a');
		restOfWritingMeans -= 0.8;
		System.out.println("After you write character:\n" + randomChar + "\n");
		System.out.print("rest of writing means: \"" + restOfWritingMeans + "\"%\n");

		return randomChar;
	}

	@Override
	public StringBuilder erase(StringBuilder stringBuilder) {
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder;
	}

}
