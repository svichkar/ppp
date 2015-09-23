package com.nixsolutions;

import java.util.Random;

public class PencilObject extends WritingObjects implements WritingTools {

	public PencilObject() {
		super();// TODO Auto-generated constructor stub
	}

	@Override

		public void preparationForWriting() {
			System.out.print("rest of writing means before preparation: \"" + restOfWritingMeans + "\"%\n");
			restOfWritingMeans = 100.00f;
			System.out.print("rest of writing means after preparation: \"" + restOfWritingMeans + "\"%\n");
		}

	@Override
	public char write() {
		System.out.println("Actual state of the pencil:\n actual color: \"" + color
				+ "\"\n rest of writing means: \"" + restOfWritingMeans + "\"\n");
		Random r = new Random(System.nanoTime());

		char randomChar = (char) (r.nextInt(26) + 'a');
		restOfWritingMeans -= 0.9;
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
