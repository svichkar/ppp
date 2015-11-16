package com.nixsolutions.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		ArrayList<Writer> arrWriter = new ArrayList<>();
		arrWriter.addAll(createRandomListOfWriters());

		for (Writer obj : arrWriter) {
			System.out.println("=================");
			stringBuilder = obj.write(createRandomStrBldr(stringBuilder));
			obj.removeLastSymbol(stringBuilder);
		}
		Collections.sort(arrWriter);
		System.out.println("=================\nArray sorted by percentage of remaining ink:\n");
		for (Writer obj : arrWriter) {
			System.out.println(obj.getClass().getSimpleName() + " - " + obj.percentage);

		}
	}

	/**
	 * Random stringbuilder generator
	 * 
	 * @param stringBuilder
	 * @return Returns stringBuilder with 3-5 random characters
	 */
	private static StringBuilder createRandomStrBldr(StringBuilder stringBuilder) {
		stringBuilder = new StringBuilder();
		Random r = new Random(System.nanoTime());
		int numberOfChars = 3 + (int) (Math.random() * ((5 - 3) + 1));
		for (int i = 0; i < numberOfChars; i++) {
			stringBuilder.append((char) (r.nextInt(26) + 'a'));
		}
		return stringBuilder;
	}

	/**
	 * Random writers collection generator
	 * 
	 * @return random list of writers (10 items)
	 */
	private static ArrayList<Writer> createRandomListOfWriters() {
		ArrayList<Writer> arrWriters = new ArrayList<Writer>();
		for (int i = 0; i < 10; i++) {
			int random = 1 + (int) (Math.random() * ((3 - 1) + 1));
			arrWriters.add(selectWriter(random));
		}
		return arrWriters;
	}

	/**
	 * Selects type of writer by number
	 * 
	 * @param index
	 *            used for choosing writer
	 * @return random type of writer
	 */
	private static Writer selectWriter(int index) {
		switch (index) {
		case 1:
			return new Pen();

		case 2:
			return new Pencil();

		case 3:
			return new Marker();
		default:
			return null;
		}

	}

}
