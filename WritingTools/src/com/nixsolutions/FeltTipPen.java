/**
 * 
 */
package com.nixsolutions;

import java.util.Random;
import java.util.Scanner;

/**
 * class for objects FELT-TIP PEN
 */
public class FeltTipPen extends WritingObjects implements WritingTools {

	/**
	 * 
	 */
	public FeltTipPen() {
		super();
	}

	@Override
	public void preparationForWriting() {
		System.out.print("rest of writing means before preparation: \"" + restOfWritingMeans + "\"%\n");
		restOfWritingMeans = 100.00f;
		System.out.print("rest of writing means after preparation: \"" + restOfWritingMeans + "\"%\n");
	}

	@Override
	public char write() {
		System.out.println("Actual state of the felt-tip pen:\n actual color: \"" + color
				+ "\"\n rest of writing means: \"" + restOfWritingMeans + "\"\n");
		Random r = new Random(System.nanoTime());

		char randomChar = (char) (r.nextInt(26) + 'a');
		restOfWritingMeans -= 0.85;
		System.out.println("After you write character:\n" + randomChar + "\n");
		System.out.print("rest of writing means: \"" + restOfWritingMeans + "\"%\n");

		return randomChar;
	}

	@Override
	public StringBuilder erase(StringBuilder stringBuilder) {
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		return stringBuilder;
	}

}
