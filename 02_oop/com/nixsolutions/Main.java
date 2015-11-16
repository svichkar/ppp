package com.nixsolutions;

import java.util.Random;

/**
 * 
 * @author elena-z Represents realization of the program logic.
 *
 */
public class Main {

	/**
	 * Method for execution the program logic. Creates 10 random writers and
	 * prepare them for writing if needed (for pen and felt pen). Three times
	 * creates a random string with length of 3-5 symbols by each writer. Erases
	 * the last symbol of the written string by pencil. Displays the list of
	 * writers with the remaining balance of writing resource and the same list
	 * sorted by the balance.
	 * 
	 * @param args
	 *            Arguments from launch.
	 */
	public static void main(String[] args) {
		boolean enabled = false;
		StringBuilder s;
		Writer[] writersArray = new Writer[10];
		Random randomWriter = new Random();
		int writerCode;
		for (int i = 0; i < writersArray.length; i++) {
			writerCode = randomWriter.nextInt(3);
			if (writerCode == 0)
				writersArray[i] = new Pen();
			else if (writerCode == 1)
				writersArray[i] = new Pencil();
			else if (writerCode == 2)
				writersArray[i] = new FeltPen();
			writersArray[i].prepareToWrite(enabled);
		}

		for (int i = 0; i < writersArray.length; i++)
			for (int j = 0; j < 3; j++) {
				s = new StringBuilder(createRandomString());
				System.out.println(writersArray[i].getClass().getName());
				writersArray[i].write(s);
				writersArray[i].erase(s);
			}

		System.out.println("=============\nList of writers:");
		for (int i = 0; i < writersArray.length; i++) {
			System.out.println(writersArray[i].getClass().getName());
			System.out.println(writersArray[i].remain);
		}

		Writer[] resultArray = sort(writersArray);
		System.out.println("==============\nSorted writers:");
		for (int i = 0; i < writersArray.length; i++) {
			System.out.println(resultArray[i].getClass().getName());
			System.out.println(resultArray[i].remain);
		}

	}

	/**
	 * Method for generating a random string of 3-5 symbols.
	 * 
	 * @return Generated string.
	 */
	private static StringBuilder createRandomString() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < random.nextInt(3) + 3; i++) {
			int number = random.nextInt(chars.length());
			char ch = chars.charAt(number);
			builder.append(ch);
		}
		return builder;
	}

	/**
	 * Method for sorting writers by the remaining balance of writing resource.
	 * 
	 * @param array
	 *            Array of writers.
	 * @return Sorted array of writers.
	 */
	private static Writer[] sort(Writer[] array) {
		Writer[] sortArray = array.clone();
		Writer tmp;
		for (int i = 0; i < sortArray.length; i++)
			for (int j = 0; j < sortArray.length - i - 1; j++) {
				if (sortArray[j].remain > sortArray[j + 1].remain) {
					tmp = sortArray[j];
					sortArray[j] = sortArray[j + 1];
					sortArray[j + 1] = tmp;
				}
			}
		return sortArray;
	}

}
